package ru.rosreestr.utils;

import org.apache.log4j.Logger;
import org.apache.ws.security.message.WSSecHeader;
import org.apache.ws.security.message.token.X509Security;
import org.apache.xml.security.transforms.Transforms;
import org.apache.xpath.XPathAPI;
import org.springframework.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import ru.voskhod.crypto.DigitalSignatureFactory;
import ru.voskhod.crypto.KeyStoreWrapper;

import javax.xml.crypto.*;
import javax.xml.crypto.dsig.*;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.*;
import java.io.ByteArrayInputStream;
import java.lang.reflect.InvocationTargetException;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Утилитный класс для подписания/проверки подписи SOAP сообщений
 */
public class SignatureUtils {

    private static final Logger LOGGER = Logger.getLogger(SignatureUtils.class);

    public static final String ALGORITHM_NAME = "GOST3411withGOST3410EL";

    public static final String ACTOR = "RSMEVAUTH";
    public static final String BODY_ID = "_body";

    public static final String XSD_WSSE = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";
    public static final String XSD_WSU = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd";
    public static final String XSD_DS = "http://www.w3.org/2000/09/xmldsig#";

    public static final String PREFIX_WSSE = "wsse";
    public static final String PREFIX_WSU = "wsu";
    public static final String PREFIX_DS = "ds";

    /**
     * Подписание элементов сообщения
     *
     * @param message сообщение (полное)
     * @param idsOfElementsForSignature перечисление идентификаторов элементов, которые необходимо подписать. К Body автоматически добавляется идентификатор "_body". Если Body надо подписать, то в списке необходимо указать иддентификатор "_body".
     * @param certificateAlias алиас сертификата
     * @param password пароль от контейнера с закрытым ключом
     * @throws SOAPException
     * @throws CertificateException
     * @throws KeyStoreException
     * @throws UnrecoverableKeyException
     * @throws NoSuchAlgorithmException
     * @throws InvalidAlgorithmParameterException
     * @throws MarshalException
     * @throws XMLSignatureException
     * @throws TransformerException
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    public static void addSecurityBlock(SOAPMessage message, List<String> idsOfElementsForSignature, String certificateAlias, char[] password) throws SOAPException, CertificateException, KeyStoreException, UnrecoverableKeyException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, MarshalException, XMLSignatureException, TransformerException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        if (message == null || StringUtils.isEmpty(idsOfElementsForSignature)) {
            return;
        }

        idsOfElementsForSignature = existingElementsIdsForSignature(message.getSOAPPart(), idsOfElementsForSignature);
        if (StringUtils.isEmpty(idsOfElementsForSignature)) {
            LOGGER.info("skip signing");
            return;
        }

        // Prepare secured header
        message.getSOAPPart().getEnvelope().addNamespaceDeclaration("wsse", XSD_WSSE);
        message.getSOAPPart().getEnvelope().addNamespaceDeclaration("wsu", XSD_WSU);
        message.getSOAPPart().getEnvelope().addNamespaceDeclaration("ds", XSD_DS);
        message.getSOAPBody().setAttributeNS(XSD_WSU, "wsu:Id", BODY_ID);

        WSSecHeader header = new WSSecHeader();
        header.setActor(ACTOR);
        header.setMustUnderstand(false);

        Element sec = header.insertSecurityHeader(message.getSOAPPart());
        Document doc = message.getSOAPPart().getEnvelope().getOwnerDocument();

        Element token = (Element) sec.appendChild(
                doc.createElementNS(XSD_WSSE, "wsse:BinarySecurityToken"));
        token.setAttribute("EncodingType", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-soap-message-security-1.0#Base64Binary");
        token.setAttribute("ValueType", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-x509-token-profile-1.0#X509v3");
        String certIdGUID = java.util.UUID.randomUUID().toString();
        token.setAttribute("wsu:Id", certIdGUID);
        token.setAttribute("xmlns:wsu", XSD_WSU);
        token.setAttribute("xmlns:wsse", XSD_WSSE);
        header.getSecurityHeader().appendChild(token);

        //----------------------

        // init JCP
        DigitalSignatureFactory.init("JCP");
        KeyStoreWrapper ksw = DigitalSignatureFactory.getKeyStoreWrapper();

        X509Certificate x509Certificate = ksw.getX509Certificate(certificateAlias);
        PrivateKey privateKey = ksw.getPrivateKey(certificateAlias, password);

        Provider pxml = (Provider) Class.forName("ru.CryptoPro.JCPxml.dsig.internal.dom.XMLDSigRI").getConstructor().newInstance();
        XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM", pxml);

        List<Transform> transformList = new ArrayList<Transform>();
        Transform transformC14N = fac.newTransform(Transforms.TRANSFORM_C14N_EXCL_OMIT_COMMENTS, (XMLStructure) null);
        transformList.add(transformC14N);

        List<Reference> refs = new ArrayList<>();
        for (String elementId: idsOfElementsForSignature) {
            Reference ref = fac.newReference(
                    "#" + elementId.trim(),
                    fac.newDigestMethod("http://www.w3.org/2001/04/xmldsig-more#gostr3411", null),
                    transformList, null, null);
            refs.add(ref);
        }

        // Make link to signing element
        SignedInfo si = fac.newSignedInfo(
                fac.newCanonicalizationMethod(CanonicalizationMethod.EXCLUSIVE,
                        (C14NMethodParameterSpec) null),
                fac.newSignatureMethod("http://www.w3.org/2001/04/xmldsig-more#gostr34102001-gostr3411", null),
                refs);

        // Prepare key information to verify signature in future on other side
        KeyInfoFactory kif = fac.getKeyInfoFactory();
        // final Object[] obj = samData.clone();
        X509Data x509d = kif.newX509Data(Collections.singletonList(x509Certificate));
        KeyInfo ki = kif.newKeyInfo(Collections.singletonList(x509d), "Key-" + java.util.UUID.randomUUID().toString());

        // Create signature and sign by private key
        XMLSignature sig = fac.newXMLSignature(si, ki, null, "Signature-" + java.util.UUID.randomUUID().toString(), null);
        DOMSignContext signContext = new DOMSignContext(privateKey, token);
        signContext.putNamespacePrefix(XMLSignature.XMLNS, "ds");
        sig.sign(signContext);

        // Insert signature node in document
        Element sigE = (Element) XPathAPI.selectSingleNode(signContext.getParent(), "//ds:Signature");
        org.w3c.dom.Node keyE = XPathAPI.selectSingleNode(sigE, "//ds:KeyInfo", sigE);
        token.appendChild(doc.createTextNode(XPathAPI.selectSingleNode(keyE, "//ds:X509Certificate", keyE).getFirstChild().getNodeValue()));
        keyE.removeChild(XPathAPI.selectSingleNode(keyE, "//ds:X509Data", keyE));
        NodeList chl = keyE.getChildNodes();

        for (int i = 0; i < chl.getLength(); i++) {
            keyE.removeChild(chl.item(i));
        }

        Element secTokenRef = doc.createElementNS(XSD_WSSE, "wsse:SecurityTokenReference");
        secTokenRef.setAttribute("wsu:Id", "StrId-" + java.util.UUID.randomUUID().toString());
        secTokenRef.setAttribute("xmlns:wsu", XSD_WSU);
        secTokenRef.setAttribute("xmlns:wsse", XSD_WSSE);
        org.w3c.dom.Node str = keyE.appendChild(secTokenRef);

        Element reference = doc.createElementNS(XSD_WSSE, "wsse:Reference");

        reference.setAttribute("xmlns:wsse", XSD_WSSE);
        Element strRef = (Element) str.appendChild(reference);

        strRef.setAttribute("ValueType", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-x509-token-profile-1.0#X509v3");
        strRef.setAttribute("URI", "#" + certIdGUID);
        header.getSecurityHeader().appendChild(sigE);

    }

    /**
     * Проверка электронной подписи
     *
     * @param doc сообщение (полное)
     * @throws Exception
     */
    public static void verify(Document doc) throws Exception {
        // Получение узла, содержащего сертификат.
        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();
        XPathExpression expr = null;
        expr = xpath.compile("//*[local-name() = 'Security']");
        NodeList secnodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

        // Поиск элемента сертификата в блоке BinarySecurityToken.
        Element r = null;
        Element el = null;
        if (secnodeList != null && secnodeList.getLength() > 0) {
            String actorAttr = null;
            for (int i = 0; i < secnodeList.getLength(); i++) {
                el = (Element) secnodeList.item(i);
                //expr = xpath.compile("//*[contains(@*[local-name() = 'actor'],'" + ACTOR + "')]");
                expr = xpath.compile("//@*[local-name() = 'actor']");
                actorAttr = (String) expr.evaluate(el, XPathConstants.STRING);
                if (ACTOR.equals(actorAttr)) {
                    expr = xpath.compile("//*[local-name() = 'BinarySecurityToken']");
                    NodeList rList = (NodeList) expr.evaluate(el, XPathConstants.NODESET);
                    if (rList.getLength() > 0) {
                        r = (Element) rList.item(0);
                        break;
                    }
                }
            }
        }
        if (r == null) {
            throw new Exception("Не найден елемент BinarySecurityToken");
        }
        // Получение сертификата.
        final X509Security x509 = new X509Security(r);
        // Создаем сертификат.
        X509Certificate cert = (X509Certificate) CertificateFactory.getInstance("X.509")
                .generateCertificate(new ByteArrayInputStream(x509.getToken()));
        if (cert == null) {
            throw new Exception("Сертификат не найден.");
        }
        System.out.println("Verify by: " + cert.getSubjectDN());
        // Поиск элемента Signature.
        expr = xpath.compile("//*[local-name() = 'Signature']");
        NodeList signatureList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
        if (signatureList.getLength() == 0) {
            throw new Exception("Не найден элемент Signature.");
        }
        Element signatureElement = (Element) signatureList.item(0);
        // Задаем открытый ключ для проверки подписи.
        Provider xmlDSigProvider = (Provider) Class.forName("ru.CryptoPro.JCPxml.dsig.internal.dom.XMLDSigRI").getConstructor().newInstance();
        final XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM", xmlDSigProvider);
        DOMValidateContext valContext = new DOMValidateContext(KeySelector.singletonKeySelector(cert.getPublicKey()), signatureElement);
        valContext.setURIDereferencer(new URIDereferencer() {
            @Override
            public Data dereference(URIReference uriReference, XMLCryptoContext context) throws URIReferenceException {
//                    if (uriReference.getURI() == null) {
//                        Data data = new OctetStreamData(this.inputStream);
//                        return data;
//                    }
//                    else {
                URIDereferencer defaultDereferencer = XMLSignatureFactory.getInstance("DOM").
                        getURIDereferencer();
                return defaultDereferencer.dereference(uriReference, context);
                //}
            }
        });

//        NodeList idAttributeElements = (NodeList) xpath.evaluate("//*[@*[local-name() = 'Id']]", doc, XPathConstants.NODESET);
//        for (int i = 0; i < idAttributeElements.getLength(); i++) {
//            //
//            Attr id = (Attr) xpath.evaluate("//@*[local-name() = 'Id']", doc, XPathConstants.NODE);//((Element)idAttributeElements.item(i)).getAttributeNodeNS("*", "Id");
//            IdResolver.registerElementById((Element) idAttributeElements.item(i), id);
//            //
//            //valContext.setIdAttributeNS((Element) idAttributes.item(i), null, "ID");
//            //
//
//        }
//      valContext.setIdAttributeNS((Element) signatureElement.getParentNode(), XSD_WSU, "Id");
        XMLSignature signature = fac.unmarshalXMLSignature(valContext);

        // Проверяем подпись и выводим результат проверки.
        boolean isVerified = signature.validate(valContext);
        LOGGER.info("Verified: " + isVerified);

        if (!isVerified) {
            throw new Exception("Подпись не прошла проверку");
        }

    }

    /**
     * Создание подписи
     *
     * @param privateKey закрытый ключ
     * @param data подписываемые данные
     * @return подпись
     * @throws Exception /
     */
    public static byte[] sign(PrivateKey privateKey,
                              byte[] data) throws Exception {
        // ALGORITHM_NAME алгоритм подписи
        final Signature sig = Signature.getInstance(ALGORITHM_NAME);
        sig.initSign(privateKey);
        sig.update(data);
        return sig.sign();
    }

    public static byte[] sign(byte[] data, String certificateAlias, char[] password) throws Exception {
        DigitalSignatureFactory.init("JCP");
        KeyStoreWrapper ksw = DigitalSignatureFactory.getKeyStoreWrapper();
        PrivateKey privateKey = ksw.getPrivateKey(certificateAlias,  password);
        return sign(privateKey, data);
    }


    /**
     * Проверка подписи на открытом ключе
     *
     * @param alghorithmName алгоритм подписи
     * @param publicKey открытый ключ
     * @param data подписываемые данные
     * @param signature подпись
     * @return true - верна, false - не верна
     * @throws Exception /
     */
    public static boolean verify(String alghorithmName, PublicKey publicKey,
                                 byte[] data, byte[] signature) throws Exception {
        final Signature sig = Signature.getInstance(alghorithmName);
        sig.initVerify(publicKey);
        sig.update(data);
        return sig.verify(signature);
    }

    /**
     * Проверить нуждается ли документ в подписании
     *
     * @param doc документ
     * @param idsOfElementsForSignature идентификаторы элементов, которые надо подписывать
     * @return true если найден элемент содержащий идентификатор или нужно подписать Body.
     */
    public static List<String> existingElementsIdsForSignature(Document doc, List<String> idsOfElementsForSignature) {

        List<String> existsElementsIdForSignature = new ArrayList<>();
        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();
        XPathExpression expr = null;
        try {
            for (String id: idsOfElementsForSignature) {
                if (BODY_ID.equals(id)) {
                    existsElementsIdForSignature.add(id);
                } else {
                    expr = xpath.compile("//*[contains(@*[local-name() = \"Id\"],'" + id + "')]");
                    NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
                    if (nodeList.getLength() > 0) {
                        existsElementsIdForSignature.add(id);
                    }
                }
            }
        } catch (XPathExpressionException e) {
            LOGGER.warn(e.getMessage(), e);
        }

        return existsElementsIdForSignature;
    }

}
