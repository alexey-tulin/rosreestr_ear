package ru.rosreestr.utils;

import org.apache.log4j.Logger;
import org.apache.ws.security.message.WSSecHeader;
import org.apache.ws.security.message.token.X509Security;
import org.apache.xml.security.transforms.Transforms;
import org.apache.xpath.XPathAPI;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import ru.voskhod.crypto.DigitalSignatureFactory;
import ru.voskhod.crypto.KeyStoreWrapper;

import javax.xml.crypto.KeySelector;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.XMLStructure;
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
import java.io.ByteArrayInputStream;
import java.lang.reflect.InvocationTargetException;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by KatrinaBosh on 30.09.2016.
 */
public class SignatureUtils {

    private static final Logger LOGGER = Logger.getLogger(SignatureUtils.class);

    public static final String ALGORITHM_NAME = "GOST3411withGOST3410EL";

    public static final String XSD_WSSE = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";
    public static final String XSD_WSU = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd";

    public static void addSecurityBlock(SOAPMessage message, String certificateAlias, char[] password) throws SOAPException, CertificateException, KeyStoreException, UnrecoverableKeyException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, MarshalException, XMLSignatureException, TransformerException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        if (message == null) {
            return;
        }

        // Prepare secured header
        message.getSOAPPart().getEnvelope().addNamespaceDeclaration("wsse", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
        message.getSOAPPart().getEnvelope().addNamespaceDeclaration("wsu", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd");
        message.getSOAPPart().getEnvelope().addNamespaceDeclaration("ds", "http://www.w3.org/2000/09/xmldsig#");
        message.getSOAPBody().setAttributeNS("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd", "wsu:Id", "_1");

        WSSecHeader header = new WSSecHeader();
        header.setActor("RSMEVAUTH");
        header.setMustUnderstand(false);

        Element sec = header.insertSecurityHeader(message.getSOAPPart());
        Document doc = message.getSOAPPart().getEnvelope().getOwnerDocument();

        Element token = (Element) sec.appendChild(
                doc.createElementNS("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "wsse:BinarySecurityToken"));
        token.setAttribute("EncodingType", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-soap-message-security-1.0#Base64Binary");
        token.setAttribute("ValueType", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-x509-token-profile-1.0#X509v3");
        String certIdGUID = java.util.UUID.randomUUID().toString();
        token.setAttribute("wsu:Id", certIdGUID);
        token.setAttribute("xmlns:wsu", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd");
        token.setAttribute("xmlns:wsse", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
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

        Reference ref = fac.newReference(
                "#_1",
                fac.newDigestMethod("http://www.w3.org/2001/04/xmldsig-more#gostr3411", null),
                transformList, null, null);

        Reference ref2 = fac.newReference(
                "#_2",
                fac.newDigestMethod("http://www.w3.org/2001/04/xmldsig-more#gostr3411", null),
                transformList, null, null);

        // Make link to signing element
        SignedInfo si = fac.newSignedInfo(
                fac.newCanonicalizationMethod(CanonicalizationMethod.EXCLUSIVE,
                        (C14NMethodParameterSpec) null),
                fac.newSignatureMethod("http://www.w3.org/2001/04/xmldsig-more#gostr34102001-gostr3411", null),
                Arrays.asList(ref, ref2));

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

        Element secTokenRef = doc.createElementNS("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "wsse:SecurityTokenReference");
        secTokenRef.setAttribute("wsu:Id", "StrId-" + java.util.UUID.randomUUID().toString());
        secTokenRef.setAttribute("xmlns:wsu", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd");
        secTokenRef.setAttribute("xmlns:wsse", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
        org.w3c.dom.Node str = keyE.appendChild(secTokenRef);

        Element reference = doc.createElementNS("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "wsse:Reference");

        reference.setAttribute("xmlns:wsse", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
        Element strRef = (Element) str.appendChild(reference);

        strRef.setAttribute("ValueType", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-x509-token-profile-1.0#X509v3");
        strRef.setAttribute("URI", "#" + certIdGUID);
        header.getSecurityHeader().appendChild(sigE);


//        NodeList nodeList = message.getSOAPHeader().getElementsByTagNameNS("*", "ServiceHeader");
//        Element serviceHeader = null;
//        if (nodeList != null && nodeList.getLength() > 0) {
//            serviceHeader = (Element) nodeList.item(0);
//            message.getSOAPHeader().removeChild(serviceHeader);
//            message.getSOAPHeader().insertBefore(serviceHeader, message.getSOAPHeader().getFirstChild());
//        }
    }

    public static void verify(Document doc) throws Exception {
        // Получение узла, содержащего сертификат.
        final Element wssecontext = doc.createElementNS(null, "namespaceContext");
        wssecontext.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:wsse", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
        NodeList secnodeList = XPathAPI.selectNodeList(doc.getDocumentElement(), "//wsse:Security");

        // Поиск элемента сертификата в блоке BinarySecurityToken.
        Element r = null;
        Element el = null;
        if (secnodeList != null && secnodeList.getLength() > 0) {
            String actorAttr = null;
            for (int i = 0; i < secnodeList.getLength(); i++) {
                el = (Element) secnodeList.item(i);
                actorAttr = el.getAttributeNS("http://schemas.xmlsoap.org/soap/envelope/", "actor");
                if (actorAttr != null && actorAttr.equals("http://smev.gosuslugi.ru/actors/smev")) {
                    r = (Element) XPathAPI.selectSingleNode(el, "//wsse:BinarySecurityToken[1]", wssecontext);
                    break;
                }
            }
        }
        if (r == null) {
            return;
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
        NodeList nl = doc.getElementsByTagNameNS("http://www.w3.org/2000/09/xmldsig#", "Signature");
        if (nl.getLength() == 0) {
            throw new Exception("Не найден элемент Signature.");
        }
        // Задаем открытый ключ для проверки подписи.
        Provider xmlDSigProvider = (Provider) Class.forName("ru.CryptoPro.JCPxml.dsig.internal.dom.XMLDSigRI").getConstructor().newInstance();
        XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM", xmlDSigProvider);
        DOMValidateContext valContext = new DOMValidateContext(KeySelector.singletonKeySelector(cert.getPublicKey()), nl.item(0));
        XMLSignature signature = fac.unmarshalXMLSignature(valContext);

        // Проверяем подпись и выводим результат проверки.
        LOGGER.info("Verified: " + signature.validate(valContext));
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

    public static boolean verify(byte[] data,  byte[] signature, String certificateAlias) throws Exception {
        DigitalSignatureFactory.init("JCP");
        KeyStoreWrapper ksw = DigitalSignatureFactory.getKeyStoreWrapper();
        X509Certificate certificate = ksw.getX509Certificate(certificateAlias);
        return verify(ALGORITHM_NAME, certificate.getPublicKey(), data, signature);
    }


}
