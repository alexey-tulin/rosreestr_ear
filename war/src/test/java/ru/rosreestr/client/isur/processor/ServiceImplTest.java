package ru.rosreestr.client.isur.processor;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StringUtils;
import ru.rosreestr.client.isur.model.*;
import ru.rosreestr.config.AppConfig;
import ru.rosreestr.config.AppProperties;
import ru.rosreestr.exception.*;
import ru.rosreestr.persistence.model.*;
import ru.rosreestr.service.WebServiceConfigService;
import ru.rosreestr.service.WebServiceService;
import ru.rosreestr.utils.CommonUtils;
import ru.rosreestr.utils.SignatureUtils;

import java.util.List;
import java.util.UUID;

/**
 * Created by Tatiana Chukina on 09.10.2016 23:40.
 * <p/>
 * Test to {@link IsurClientProcessorImpl}
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class ServiceImplTest {
    private static final Logger LOG = Logger.getLogger(IsurClientProcessorImpl.class);

    private static final String FROM_ORG_CODE = "2033";
    private static final String TO_ORG_CODE = "111";
    private static final String SERVICE_NUMBER_TEMPLATE = "2033-9000085-047202-%s/%s";
    private static final String SERVICE_TYPE_CODE = "047202";
    private static final String DOCUMENT_TYPE_CODE = "77290";

    @Autowired
    private IsurClientProcessorImpl serviceClient;

    @Autowired
    private AppProperties properties;

    @Autowired
    private WebServiceConfigService wsParamsService;

    @Autowired
    private WebServiceService wsService;

    private String alias;

    private char[] password;

    @Before
    public void init() throws NotFoundWebServiceException, DuplicateWebServiceException, NotFoundWebServiceParamException, DuplicateWebServiceParamException, WebServiceParamTypeException {
        WebServiceCode targetServiceCode = WebServiceCode.ISUR;
        List<WebService> webServices = wsService.findByParam(WebServiceParam.CODE, targetServiceCode.name());

        if (webServices.isEmpty()) {
            throw new NotFoundWebServiceException(targetServiceCode);
        } else if (webServices.size() > 1) {
            throw new DuplicateWebServiceException(webServices, targetServiceCode);
        }
        WebServiceConfig aliasParam = wsParamsService.findOneByServiceIdAndName(webServices.get(0).getServiceId(), WebServiceParam.SIGNATURE_ALIAS, WebServiceParamType.STRING);
        WebServiceConfig passwordParam = wsParamsService.findOneByServiceIdAndName(webServices.get(0).getServiceId(), WebServiceParam.SIGNATURE_PASSWORD);

        alias = aliasParam.getStringValue();
        password = !StringUtils.isEmpty(passwordParam.getStringValue()) ? passwordParam.getStringValue().toCharArray() : null;
    }

    @Test
    public void testSendTask() throws Exception {
        LOG.info("start test service sendTask");
        String serviceNumber = String.format(SERVICE_NUMBER_TEMPLATE, getNewMessageNum(), CommonUtils.getCurrentYear());
        CoordinateTaskData coordinateTaskData = createCoordinateTaskData(serviceNumber);
        Headers sendTaskHeaders = createSendTaskHeaders(serviceNumber);
        serviceClient.sendTask(coordinateTaskData, sendTaskHeaders);
        LOG.info("end test service sendTask");
    }

    private CoordinateTaskData createCoordinateTaskData(String serviceNumber) {
        CoordinateTaskData coordinateTaskData = new CoordinateTaskData();
        RequestTask requestTask = createRequestTask(serviceNumber);
        DocumentsRequestData documentsRequestData = createDocumentsRequestData();

        coordinateTaskData.setTask(requestTask);
        coordinateTaskData.setData(documentsRequestData);
        return coordinateTaskData;
    }


    private RequestTask createRequestTask(String serviceNumber) {
        RequestTask requestTask = new RequestTask();
        requestTask.setRequestId(UUID.randomUUID().toString());
        requestTask.setServiceNumber(serviceNumber);
        requestTask.setServiceTypeCode("047202");

        Person responsible = new Person();
        responsible.setLastName("Столыпин");
        responsible.setFirstName("Петр");
        responsible.setMiddleName("Аркадьевич");
        responsible.setJobTitle("АСУ ЕИРЦ");
        responsible.setPhone("8-999-999-99-99");
        responsible.setEmail("test@test.test");
        requestTask.setResponsible(responsible);

        Department department = new Department();
        department.setName("Департамент информационных технологий города Москвы");
        department.setCode("707");
        department.setInn("7710878000");
        department.setOgrn("1107746943347");
        requestTask.setDepartment(department);

        return requestTask;
    }

    private DocumentsRequestData createDocumentsRequestData() {
        DocumentsRequestData documentsRequestData = new DocumentsRequestData();
        documentsRequestData.setDocumentTypeCode("77290");
        documentsRequestData.setIncludeXmlView(true);
        documentsRequestData.setIncludeBinaryView(true);
        documentsRequestData.setParameterTypeCode("77290");
        DocumentsRequestData.Parameter parameter = createParameter();
        documentsRequestData.setParameter(parameter);
        return documentsRequestData;
    }

    private DocumentsRequestData.Parameter createParameter() {
        DocumentsRequestData.Parameter parameter = new DocumentsRequestData.Parameter();
        ServiceProperties serviceProperties = new ServiceProperties();
        serviceProperties.setRegion("77");
        serviceProperties.setCadastralnumber("77:01:0004028:4359");
        serviceProperties.setTypeobject("002001002000");
        serviceProperties.setTyperoom("IsNondomestic");

        byte[] base64Props = CommonUtils.marshalObjectToByte(serviceProperties);
        ru.rosreestr.client.isur.model.base64.ServiceProperties servicePropertiesBase64 = new ru.rosreestr.client.isur.model.base64.ServiceProperties();
        servicePropertiesBase64.setData(base64Props);
        servicePropertiesBase64.setSignature(createSignature(base64Props));
        parameter.setAny(servicePropertiesBase64);

        return parameter;
    }

    private byte[] createSignature(byte[] data) {
        try {
            return SignatureUtils.sign(data, alias, password);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    private String getNewMessageNum() {
        return "000000";
    }

    private Headers createSendTaskHeaders(String serviceNumber) {
        Headers headers = new Headers();
        headers.setFromOrgCode(FROM_ORG_CODE);
        headers.setToOrgCode(TO_ORG_CODE);
        headers.setRequestDateTime(CommonUtils.getXmlGregorianCurrentDate());
        headers.setMessageId(UUID.randomUUID().toString());
        headers.setServiceNumber(serviceNumber);
        return headers;
    }
}