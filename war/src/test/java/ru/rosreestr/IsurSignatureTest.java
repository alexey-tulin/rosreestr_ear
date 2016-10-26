package ru.rosreestr;


import org.apache.log4j.Logger;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import ru.rosreestr.utils.SignatureUtils;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {AppConfig.class})
public class IsurSignatureTest {
// TODO допилить
    private static final Logger LOG = Logger.getLogger(IsurSignatureTest.class);
    private static final String FROM_ORG_CODE = "2033";
    private static final String TO_ORG_CODE = "1111";
    private static final String SERVICE_NUMBER_TEMPLATE = "2033-9000085-047202-%s/%s";

    @Test
    public void testSignatureVerify() throws Exception {
        String response = " <s:Envelope xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:u=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\"><s:Header><h:ServiceHeader xmlns:h=\"http://asguf.mos.ru/rkis_gu/coordinate/v5/\" xmlns=\"http://asguf.mos.ru/rkis_gu/coordinate/v5/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" u:Id=\"_2\" xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:u=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\"><FromOrgCode>1111</FromOrgCode><ToOrgCode>707</ToOrgCode><MessageId>bdab73e8-ebe9-4f50-b05a-5017416b5514</MessageId><RelatesTo>b414750c-5705-4c53-b410-de5dca3d06bc</RelatesTo><RequestDateTime>2016-10-25T15:05:14.2976558Z</RequestDateTime></h:ServiceHeader><Security xmlns=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\" s:actor=\"RSMEVAUTH\" xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:u=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\"><o:BinarySecurityToken xmlns:o=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\" EncodingType=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-soap-message-security-1.0#Base64Binary\" ValueType=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-x509-token-profile-1.0#X509v3\" u:Id=\"uuid-b9ade157-6294-4cd2-9a85-0c0f249cf4ed-9441\">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</o:BinarySecurityToken><Signature xmlns=\"http://www.w3.org/2000/09/xmldsig#\"><SignedInfo><CanonicalizationMethod Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\"/><SignatureMethod Algorithm=\"http://www.w3.org/2001/04/xmldsig-more#gostr34102001-gostr3411\"/><Reference URI=\"#_1\"><Transforms><Transform Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\"/></Transforms><DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmldsig-more#gostr3411\"/><DigestValue>BXcCzrRlTMOJAc7l51wwQmZh7obBGLVxulRE1AfAMMM=</DigestValue></Reference><Reference URI=\"#_2\"><Transforms><Transform Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\"/></Transforms><DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmldsig-more#gostr3411\"/><DigestValue>sDIbRcABhLSQDpQSZoaktZ34pIoWt53mB7cTRqh/OB8=</DigestValue></Reference></SignedInfo><SignatureValue>wNPxyT2qhpjYiRYDiwgCDXmE6VJwCn4/u0v3xoycADSeiQToozyU33nBrjFVKW+eyJhgeaPC7BoXM6SKJwY+7Q==</SignatureValue><KeyInfo><o:SecurityTokenReference xmlns:o=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\"><o:Reference URI=\"#uuid-b9ade157-6294-4cd2-9a85-0c0f249cf4ed-9441\" ValueType=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-x509-token-profile-1.0#X509v3\"/></o:SecurityTokenReference></KeyInfo></Signature></Security></s:Header><s:Body xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" u:Id=\"_1\"><ErrorMessage xmlns=\"http://asguf.mos.ru/rkis_gu/coordinate/v5/\"><Error><ErrorCode>1</ErrorCode><ErrorText>Переданное значение поля \"ServiceNumber\" (707-9000095-047202-000205/16) не соответствует формату уникального номера обращения на оказание государственной услуги.</ErrorText></Error></ErrorMessage></s:Body></s:Envelope>";
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setNamespaceAware(true);
        DocumentBuilder db = documentBuilderFactory.newDocumentBuilder();

        Document doc = db.parse(
                new InputSource(new StringReader(response))
        );
        SignatureUtils.verify(doc);
    }

//    @Autowired
//    private ServiceClient serviceClient;
//
//    @Autowired
//    private AppProperties properties;
//
//    @Test
//    public void test() {
//
//        Headers headers = new Headers();
//        headers.setFromOrgCode(FROM_ORG_CODE);
//        headers.setToOrgCode(TO_ORG_CODE);
//        headers.setRequestDateTime(CommonUtils.getXmlGregorianCurrentDate());
//        headers.setMessageId(UUID.randomUUID().toString());
//        headers.setServiceNumber(String.format(SERVICE_NUMBER_TEMPLATE, "000000", CommonUtils.getCurrentYear()));//TODO -000000 NNNNNN - порядковый номер обращения за указанной государственной услугой в организации, зарегистрировавшей обращение, в текущем году;
//
//        CoordinateTaskData coordinateTaskData = new CoordinateTaskData();
//        coordinateTaskData.setTask(new RequestTask());
//        coordinateTaskData.getTask().setRequestId("320a1d7f-7e94-464c-aace-ae03af7ad248");
//        coordinateTaskData.getTask().setValidityPeriod(new XMLGregorianCalendarImpl());
//        coordinateTaskData.getTask().setResponsible(new Person());
//        coordinateTaskData.getTask().getResponsible().setLastName("LastName");
//        coordinateTaskData.getTask().getResponsible().setFirstName("FirstName");
//        coordinateTaskData.getTask().getResponsible().setMiddleName("MiddleName");
//        coordinateTaskData.getTask().getResponsible().setJobTitle("Tester");
//        coordinateTaskData.getTask().getResponsible().setPhone("8-999-999-99-99");
//        coordinateTaskData.getTask().getResponsible().setEmail("test@test.test");
//        coordinateTaskData.getTask().setDepartment(new Department());
//        coordinateTaskData.getTask().getDepartment().setName("Department IT");
//        coordinateTaskData.getTask().getDepartment().setInn("7710878000");
//        coordinateTaskData.getTask().getDepartment().setOgrn("1107746943347");
//        coordinateTaskData.getTask().getDepartment().setRegDate(new XMLGregorianCalendarImpl());
//        coordinateTaskData.getTask().setServiceNumber("9990-9999999-999998-093023/16");
//        coordinateTaskData.getTask().setServiceTypeCode("999998");
//
//        IService customBindingIService = serviceClient.getCustomBindingIService();
//        customBindingIService.sendTask(coordinateTaskData, headers);
//    }
//
//    @Test
//    public void testSignData() throws Exception {
//
//        String data = "PFJlcXVlc3RHUlA+PGVEb2N1bWVudCBHVUlEPSI1MDk2MGNmOC01ZGM0LTQ2NjYtYmUzMy04ZGFhNzQ3OGQwMmUiIFZlcnNpb249IjEuMTYiLz48UmVxdWVzdD48UmVxdWlyZWREYXRhPjxSZXF1aXJlZERhdGFSZWFsdHk+PEV4dHJhY3RSZWFsdHk+PE9iamVjdHM+PE9iamVjdD48T2JqS2luZD48QnVpbGRpbmc+PElzTm9uZG9tZXN0aWM+dHJ1ZTwvSXNOb25kb21lc3RpYz48L0J1aWxkaW5nPjwvT2JqS2luZD48Q2FkYXN0cmFsTnVtYmVycz48Q2FkYXN0cmFsTnVtYmVyPjUwOjI2OjAxNzA1MDg6Njc2PC9DYWRhc3RyYWxOdW1iZXI+PC9DYWRhc3RyYWxOdW1iZXJzPjxMb2NhdGlvbj48UmVnaW9uPjc3PC9SZWdpb24+PC9Mb2NhdGlvbj48L09iamVjdD48L09iamVjdHM+PC9FeHRyYWN0UmVhbHR5PjwvUmVxdWlyZWREYXRhUmVhbHR5PjwvUmVxdWlyZWREYXRhPjxEZWNsYXJhbnQgZGVjbGFyYW50X2tpbmQ9IjM1NzAxMzAwMDAwMCIgc2lnbmF0dXJlZD0idHJ1ZSI+PEdvdmVybmFuY2U+PE5hbWU+0JrQvtC80LjRgtC10YIg0L/QviDQsNGA0YXQuNGC0LXQutGC0YPRgNC1INC4INCz0YDQsNC00L7RgdGC0YDQvtC40YLQtdC70YzRgdGC0LLRgyDQs9C+0YDQvtC00LAg0JzQvtGB0LrQstGLPC9OYW1lPjxHb3Zlcm5hbmNlX0NvZGU+MDA3MDAxMDAxMDAyPC9Hb3Zlcm5hbmNlX0NvZGU+PEUtbWFpbD5HYWxhdGVua29BU0Btb3MucnU8L0UtbWFpbD48QWdlbnQ+PEZJTz48U3VybmFtZT7DkCYjMTQ3O8OQwrDDkMK7w5DCsMORJiMxMzA7w5DCtcOQwr3DkMK6w5DCvjwvU3VybmFtZT48Rmlyc3Q+w5AmIzE0NDvDkMK7w5DCtcOQwrrDkSYjMTI5O8OQwrXDkMK5PC9GaXJzdD48UGF0cm9ueW1pYz7DkMKhw5DCtcORJiMxMjg7w5DCs8OQwrXDkMK1w5DCssOQwrjDkSYjMTM1OzwvUGF0cm9ueW1pYz48L0ZJTz48RG9jdW1lbnQ+PENvZGVfRG9jdW1lbnQ+MDA4MDAxMDAyMDAwPC9Db2RlX0RvY3VtZW50PjxTZXJpZXM+OTk5OTwvU2VyaWVzPjxOdW1iZXI+OTk5OTk5PC9OdW1iZXI+PERhdGU+MDAwMS0wMS0wMTwvRGF0ZT48SXNzdWVPcmdhbj7QntCS0JQg0JPQntCgLtCc0J7QodCa0JLQqzwvSXNzdWVPcmdhbj48L0RvY3VtZW50PjxFLW1haWw+R2FsYXRlbmtvQVNAbW9zLnJ1PC9FLW1haWw+PGFnZW50X2tpbmQ+MzU2MDA1MDAwMDAwPC9hZ2VudF9raW5kPjwvQWdlbnQ+PC9Hb3Zlcm5hbmNlPjwvRGVjbGFyYW50PjxQYXltZW50PjxGcmVlPnRydWU8L0ZyZWU+PC9QYXltZW50PjxEZWxpdmVyeT48V2ViU2VydmljZT50cnVlPC9XZWJTZXJ2aWNlPjwvRGVsaXZlcnk+PEFwcGxpZWRfRG9jdW1lbnRzPjxBcHBsaWVkX0RvY3VtZW50PjxDb2RlX0RvY3VtZW50PjU1ODEwMjEwMDAwMDwvQ29kZV9Eb2N1bWVudD48TmFtZT7Ql9Cw0L/RgNC+0YEg0L4g0L/RgNC10LTQvtGB0YLQsNCy0LvQtdC90LjQuCDRgdCy0LXQtNC10L3QuNC5LCDRgdC+0LTQtdGA0LbQsNGJ0LjRhdGB0Y8g0LIg0JXQtNC40L3QvtC8INCz0L7RgdGD0LTQsNGA0YHRgtCy0LXQvdC90L7QvCDRgNC10LXRgdGC0YDQtSDQv9GA0LDQsiDQvdCwINC90LXQtNCy0LjQttC40LzQvtC1INC40LzRg9GJ0LXRgdGC0LLQviDQuCDRgdC00LXQu9C+0Log0YEg0L3QuNC8PC9OYW1lPjxOdW1iZXI+MTg3MjU1MjUyMTwvTnVtYmVyPjxEYXRlPjIwMTYtMDktMjI8L0RhdGU+PFF1YW50aXR5PjxPcmlnaW5hbCBRdWFudGl0eT0iMSIgUXVhbnRpdHlfU2hlZXQ9IjEiLz48L1F1YW50aXR5PjwvQXBwbGllZF9Eb2N1bWVudD48L0FwcGxpZWRfRG9jdW1lbnRzPjwvUmVxdWVzdD48RklSX0luZm8+PE1vbml0b3Jpbmc+MDwvTW9uaXRvcmluZz48L0ZJUl9JbmZvPjwvUmVxdWVzdEdSUD4=\n";
//
//        DigitalSignatureFactory.init("JCP");
//        KeyStoreWrapper ksw = DigitalSignatureFactory.getKeyStoreWrapper();
//
//        X509Certificate x509Certificate = ksw.getX509Certificate(properties.getSignatureAlias());
//        PrivateKey privateKey = ksw.getPrivateKey(properties.getSignatureAlias(), properties.getSignaturePassword().toCharArray());
//
//        //byte[] signature = PKCS7Tools.signPKCS7SunSecurity(data.getBytes(), privateKey, x509Certificate);
//        //dsp.signPKCS7Detached(data.getBytes("UTF-8"), privateKey, x509Certificate);
//
//        byte[] signature = SignatureUtils.sign(data.getBytes("UTF-8"), properties.getSignatureAlias(), properties.getSignaturePassword().toCharArray());
//
//        System.out.println(new String(data.getBytes("UTF-8"), "UTF-8"));
//        System.out.println("----");
//        String ss = new String(signature, "UTF-8");
//        System.out.println(ss);
//
//        System.out.println(toHexString(signature));
//        // Проверка подписи
//        final boolean signELver = SignatureUtils.verify(SignatureUtils.ALGORITHM_NAME, x509Certificate.getPublicKey(),
//                data.getBytes("UTF-8"), signature);
//        System.out.println("Signature verifies (signEL) is: " + signELver);
//
//    }
//
//    public static String toHexString(byte[] array) {
//        final char[] hex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
//                'B', 'C', 'D', 'E', 'F'};
//        StringBuffer ss = new StringBuffer(array.length * 3);
//        for (int i = 0; i < array.length; i++) {
//            ss.append(' ');
//            ss.append(hex[(array[i] >>> 4) & 0xf]);
//            ss.append(hex[array[i] & 0xf]);
//        }
//        return ss.toString();
//    }

}
