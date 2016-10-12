package ru.rosreestr;


import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.rosreestr.client.isur.IService;
import ru.rosreestr.client.isur.ServiceClient;
import ru.rosreestr.client.isur.model.*;
import ru.rosreestr.config.AppConfig;
import ru.rosreestr.config.AppProperties;
import ru.rosreestr.utils.CommonUtils;
import ru.rosreestr.utils.SignatureUtils;
import ru.voskhod.crypto.DigitalSignatureFactory;
import ru.voskhod.crypto.KeyStoreWrapper;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class IsurSignatureTest {

    private static final Logger LOG = Logger.getLogger(IsurSignatureTest.class);
    private static final String FROM_ORG_CODE = "2033";
    private static final String TO_ORG_CODE = "1111";
    private static final String SERVICE_NUMBER_TEMPLATE = "2033-9000085-047202-%s/%s";

    @Autowired
    private ServiceClient serviceClient;

    @Autowired
    private AppProperties properties;

    @Test
    public void test() {

        Headers headers = new Headers();
        headers.setFromOrgCode(FROM_ORG_CODE);
        headers.setToOrgCode(TO_ORG_CODE);
        headers.setRequestDateTime(CommonUtils.getXmlGregorianCurrentDate());
        headers.setMessageId(UUID.randomUUID().toString());
        headers.setServiceNumber(String.format(SERVICE_NUMBER_TEMPLATE, "000000", CommonUtils.getCurrentYear()));//TODO -000000 NNNNNN - порядковый номер обращения за указанной государственной услугой в организации, зарегистрировавшей обращение, в текущем году;

        CoordinateTaskData coordinateTaskData = new CoordinateTaskData();
        coordinateTaskData.setTask(new RequestTask());
        coordinateTaskData.getTask().setRequestId("320a1d7f-7e94-464c-aace-ae03af7ad248");
        coordinateTaskData.getTask().setValidityPeriod(new XMLGregorianCalendarImpl());
        coordinateTaskData.getTask().setResponsible(new Person());
        coordinateTaskData.getTask().getResponsible().setLastName("LastName");
        coordinateTaskData.getTask().getResponsible().setFirstName("FirstName");
        coordinateTaskData.getTask().getResponsible().setMiddleName("MiddleName");
        coordinateTaskData.getTask().getResponsible().setJobTitle("Tester");
        coordinateTaskData.getTask().getResponsible().setPhone("8-999-999-99-99");
        coordinateTaskData.getTask().getResponsible().setEmail("test@test.test");
        coordinateTaskData.getTask().setDepartment(new Department());
        coordinateTaskData.getTask().getDepartment().setName("Department IT");
        coordinateTaskData.getTask().getDepartment().setInn("7710878000");
        coordinateTaskData.getTask().getDepartment().setOgrn("1107746943347");
        coordinateTaskData.getTask().getDepartment().setRegDate(new XMLGregorianCalendarImpl());
        coordinateTaskData.getTask().setServiceNumber("9990-9999999-999998-093023/16");
        coordinateTaskData.getTask().setServiceTypeCode("999998");

        IService customBindingIService = serviceClient.getCustomBindingIService();
        customBindingIService.sendTask(coordinateTaskData, headers);
    }

    @Test
    public void testSignData() throws Exception {

        String data = "PFJlcXVlc3RHUlA+PGVEb2N1bWVudCBHVUlEPSI1MDk2MGNmOC01ZGM0LTQ2NjYtYmUzMy04ZGFhNzQ3OGQwMmUiIFZlcnNpb249IjEuMTYiLz48UmVxdWVzdD48UmVxdWlyZWREYXRhPjxSZXF1aXJlZERhdGFSZWFsdHk+PEV4dHJhY3RSZWFsdHk+PE9iamVjdHM+PE9iamVjdD48T2JqS2luZD48QnVpbGRpbmc+PElzTm9uZG9tZXN0aWM+dHJ1ZTwvSXNOb25kb21lc3RpYz48L0J1aWxkaW5nPjwvT2JqS2luZD48Q2FkYXN0cmFsTnVtYmVycz48Q2FkYXN0cmFsTnVtYmVyPjUwOjI2OjAxNzA1MDg6Njc2PC9DYWRhc3RyYWxOdW1iZXI+PC9DYWRhc3RyYWxOdW1iZXJzPjxMb2NhdGlvbj48UmVnaW9uPjc3PC9SZWdpb24+PC9Mb2NhdGlvbj48L09iamVjdD48L09iamVjdHM+PC9FeHRyYWN0UmVhbHR5PjwvUmVxdWlyZWREYXRhUmVhbHR5PjwvUmVxdWlyZWREYXRhPjxEZWNsYXJhbnQgZGVjbGFyYW50X2tpbmQ9IjM1NzAxMzAwMDAwMCIgc2lnbmF0dXJlZD0idHJ1ZSI+PEdvdmVybmFuY2U+PE5hbWU+0JrQvtC80LjRgtC10YIg0L/QviDQsNGA0YXQuNGC0LXQutGC0YPRgNC1INC4INCz0YDQsNC00L7RgdGC0YDQvtC40YLQtdC70YzRgdGC0LLRgyDQs9C+0YDQvtC00LAg0JzQvtGB0LrQstGLPC9OYW1lPjxHb3Zlcm5hbmNlX0NvZGU+MDA3MDAxMDAxMDAyPC9Hb3Zlcm5hbmNlX0NvZGU+PEUtbWFpbD5HYWxhdGVua29BU0Btb3MucnU8L0UtbWFpbD48QWdlbnQ+PEZJTz48U3VybmFtZT7DkCYjMTQ3O8OQwrDDkMK7w5DCsMORJiMxMzA7w5DCtcOQwr3DkMK6w5DCvjwvU3VybmFtZT48Rmlyc3Q+w5AmIzE0NDvDkMK7w5DCtcOQwrrDkSYjMTI5O8OQwrXDkMK5PC9GaXJzdD48UGF0cm9ueW1pYz7DkMKhw5DCtcORJiMxMjg7w5DCs8OQwrXDkMK1w5DCssOQwrjDkSYjMTM1OzwvUGF0cm9ueW1pYz48L0ZJTz48RG9jdW1lbnQ+PENvZGVfRG9jdW1lbnQ+MDA4MDAxMDAyMDAwPC9Db2RlX0RvY3VtZW50PjxTZXJpZXM+OTk5OTwvU2VyaWVzPjxOdW1iZXI+OTk5OTk5PC9OdW1iZXI+PERhdGU+MDAwMS0wMS0wMTwvRGF0ZT48SXNzdWVPcmdhbj7QntCS0JQg0JPQntCgLtCc0J7QodCa0JLQqzwvSXNzdWVPcmdhbj48L0RvY3VtZW50PjxFLW1haWw+R2FsYXRlbmtvQVNAbW9zLnJ1PC9FLW1haWw+PGFnZW50X2tpbmQ+MzU2MDA1MDAwMDAwPC9hZ2VudF9raW5kPjwvQWdlbnQ+PC9Hb3Zlcm5hbmNlPjwvRGVjbGFyYW50PjxQYXltZW50PjxGcmVlPnRydWU8L0ZyZWU+PC9QYXltZW50PjxEZWxpdmVyeT48V2ViU2VydmljZT50cnVlPC9XZWJTZXJ2aWNlPjwvRGVsaXZlcnk+PEFwcGxpZWRfRG9jdW1lbnRzPjxBcHBsaWVkX0RvY3VtZW50PjxDb2RlX0RvY3VtZW50PjU1ODEwMjEwMDAwMDwvQ29kZV9Eb2N1bWVudD48TmFtZT7Ql9Cw0L/RgNC+0YEg0L4g0L/RgNC10LTQvtGB0YLQsNCy0LvQtdC90LjQuCDRgdCy0LXQtNC10L3QuNC5LCDRgdC+0LTQtdGA0LbQsNGJ0LjRhdGB0Y8g0LIg0JXQtNC40L3QvtC8INCz0L7RgdGD0LTQsNGA0YHRgtCy0LXQvdC90L7QvCDRgNC10LXRgdGC0YDQtSDQv9GA0LDQsiDQvdCwINC90LXQtNCy0LjQttC40LzQvtC1INC40LzRg9GJ0LXRgdGC0LLQviDQuCDRgdC00LXQu9C+0Log0YEg0L3QuNC8PC9OYW1lPjxOdW1iZXI+MTg3MjU1MjUyMTwvTnVtYmVyPjxEYXRlPjIwMTYtMDktMjI8L0RhdGU+PFF1YW50aXR5PjxPcmlnaW5hbCBRdWFudGl0eT0iMSIgUXVhbnRpdHlfU2hlZXQ9IjEiLz48L1F1YW50aXR5PjwvQXBwbGllZF9Eb2N1bWVudD48L0FwcGxpZWRfRG9jdW1lbnRzPjwvUmVxdWVzdD48RklSX0luZm8+PE1vbml0b3Jpbmc+MDwvTW9uaXRvcmluZz48L0ZJUl9JbmZvPjwvUmVxdWVzdEdSUD4=\n";

        DigitalSignatureFactory.init("JCP");
        KeyStoreWrapper ksw = DigitalSignatureFactory.getKeyStoreWrapper();

        X509Certificate x509Certificate = ksw.getX509Certificate(properties.getSignatureAlias());
        PrivateKey privateKey = ksw.getPrivateKey(properties.getSignatureAlias(), properties.getSignaturePassword().toCharArray());

        //byte[] signature = PKCS7Tools.signPKCS7SunSecurity(data.getBytes(), privateKey, x509Certificate);
        //dsp.signPKCS7Detached(data.getBytes("UTF-8"), privateKey, x509Certificate);

        byte[] signature = SignatureUtils.sign(data.getBytes("UTF-8"), properties.getSignatureAlias(), properties.getSignaturePassword().toCharArray());

        System.out.println(new String(data.getBytes("UTF-8"), "UTF-8"));
        System.out.println("----");
        String ss = new String(signature, "UTF-8");
        System.out.println(ss);

        System.out.println(toHexString(signature));
        // Проверка подписи
        final boolean signELver = SignatureUtils.verify(SignatureUtils.ALGORITHM_NAME, x509Certificate.getPublicKey(),
                data.getBytes("UTF-8"), signature);
        System.out.println("Signature verifies (signEL) is: " + signELver);

    }

    public static String toHexString(byte[] array) {
        final char[] hex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
                'B', 'C', 'D', 'E', 'F'};
        StringBuffer ss = new StringBuffer(array.length * 3);
        for (int i = 0; i < array.length; i++) {
            ss.append(' ');
            ss.append(hex[(array[i] >>> 4) & 0xf]);
            ss.append(hex[array[i] & 0xf]);
        }
        return ss.toString();
    }

}
