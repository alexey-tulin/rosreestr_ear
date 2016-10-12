package ru.rosreestr.ws;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rosreestr.client.isur.model.*;
import ru.rosreestr.client.isur.processor.ServiceImpl;
import ru.rosreestr.config.AppProperties;
import ru.rosreestr.persistence.repository.CommonRepositoryImpl;
import ru.rosreestr.utils.CommonUtils;
import ru.rosreestr.utils.SignatureUtils;
import ru.rosreestr.ws.model.GetInformationRequest;
import ru.rosreestr.ws.model.GetInformationResponse;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.UUID;

/**
 * Created by Tatiana Chukina on 04.10.2016 23:54.
 */
@Service("rosreestrServiceProcessor")
public class RosreestrServiceProcessor {
    private static final Logger LOG = Logger.getLogger(RosreestrServiceProcessor.class);
    @Autowired
    private ServiceImpl serviceClient;

    @Autowired
    private AppProperties properties;

    @Autowired
    CommonRepositoryImpl commonRepository;

    public GetInformationResponse getInformation(GetInformationRequest request) {
        LOG.info("ru.rosreestr.ws.RosreestrServiceProcessor.sendRequest");
        BigDecimal nextMessageNum = commonRepository.getNextMessageNum();
        DecimalFormat df = new DecimalFormat("#000000");
        String serviceNumber = String.format(properties.getRosreestrServiceNumberTemplate(),
                df.format(nextMessageNum), CommonUtils.getCurrentYear());

        serviceClient.sendTask(createTaskMessage(request, serviceNumber), createHeaders(serviceNumber));
        GetInformationResponse sendRequestResponse = new GetInformationResponse();
        sendRequestResponse.setServiceNumber(serviceNumber);
        return sendRequestResponse;
    }

    private Headers createHeaders(String serviceNumber) {
        Headers headers = new Headers();
        headers.setFromOrgCode(properties.getRosreestrFromOrgCode());
        headers.setToOrgCode(properties.getRosreestrToOrgCode());
        headers.setRequestDateTime(CommonUtils.getXmlGregorianCurrentDate());
        headers.setMessageId(UUID.randomUUID().toString());
        headers.setServiceNumber(serviceNumber);
        return headers;
    }

    private CoordinateTaskData createTaskMessage(GetInformationRequest request, String serviceNumber) {
        CoordinateTaskData coordinateTaskData = new CoordinateTaskData();
        RequestTask requestTask = createRequestTask(serviceNumber);
        DocumentsRequestData documentsRequestData = createDocumentsRequestData(request);

        coordinateTaskData.setTask(requestTask);
        coordinateTaskData.setData(documentsRequestData);

        return coordinateTaskData;
    }

    private RequestTask createRequestTask(String serviceNumber) {
        RequestTask requestTask = new RequestTask();
        requestTask.setRequestId(UUID.randomUUID().toString());
        requestTask.setServiceNumber(serviceNumber);
        requestTask.setServiceTypeCode(properties.getRosreestrServiceTypeCode());

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

    private DocumentsRequestData createDocumentsRequestData(GetInformationRequest request) {
        DocumentsRequestData documentsRequestData = new DocumentsRequestData();
        documentsRequestData.setDocumentTypeCode(properties.getRosreestrDocumentTypeCode());
        documentsRequestData.setIncludeXmlView(true);
        documentsRequestData.setIncludeBinaryView(true);
        documentsRequestData.setParameterTypeCode(properties.getRosreestrDocumentTypeCode());
        DocumentsRequestData.Parameter parameter = createParameter(request);
        documentsRequestData.setParameter(parameter);
        return documentsRequestData;
    }

    private DocumentsRequestData.Parameter createParameter(GetInformationRequest request) {
        DocumentsRequestData.Parameter parameter = new DocumentsRequestData.Parameter();
        ServiceProperties serviceProperties = new ServiceProperties();
        serviceProperties.setRegion(request.getRegion());
        serviceProperties.setCadastralnumber(request.getCadastralnumber());
        serviceProperties.setTypeobject(request.getTypeobject());
        serviceProperties.setTyperoom(request.getTyperoom());
        serviceProperties.setAddress1(request.getAddress1());
        serviceProperties.setAddress2(request.getAddress2());
        serviceProperties.setAddress3(request.getAddress3());
        serviceProperties.setAdressother(request.getAdressother());
        serviceProperties.setApartment(request.getApartment());
        serviceProperties.setAreasunit(request.getAreasunit());
        serviceProperties.setAreasvalue(request.getAreasvalue());
        serviceProperties.setCodekladr(request.getCodekladr());
        serviceProperties.setCodeokato(request.getCodeokato());
        serviceProperties.setConditionalcadatralnumber(request.getConditionalcadatralnumber());
        serviceProperties.setIddbegrp(request.getIddbegrp());
        serviceProperties.setDopinfoname(request.getDopinfoname());
        serviceProperties.setTypebuilding(request.getTypebuilding());
        serviceProperties.setPostalcode(request.getPostalcode());
        serviceProperties.setLocationdistrict(request.getLocationdistrict());
        serviceProperties.setTypedistrict(request.getTypedistrict());
        serviceProperties.setTown(request.getTown());
        serviceProperties.setTypetown(request.getTypetown());
        serviceProperties.setRayon(request.getRayon());
        serviceProperties.setTyperayon(request.getTyperayon());
        serviceProperties.setLocationsoviet(request.getLocationsoviet());
        serviceProperties.setTypesoviet(request.getTypesoviet());
        serviceProperties.setNaselpunkt(request.getNaselpunkt());
        serviceProperties.setTypenaselpunkt(request.getTypenaselpunkt());
        serviceProperties.setLocationstreet(request.getLocationstreet());
        serviceProperties.setTypestreet(request.getTypestreet());
        serviceProperties.setLocationhouse(request.getLocationhouse());
        serviceProperties.setLocationbuilding(request.getLocationbuilding());
        serviceProperties.setLocationstructure(request.getLocationstructure());
        serviceProperties.setLocationapartment(request.getLocationapartment());
        serviceProperties.setApartment(request.getApartment());
        serviceProperties.setLocationother(request.getLocationother());
        serviceProperties.setEmailSubscription(request.getEmailSubscription());
        serviceProperties.setEnableSubscription(request.getEnableSubscription());
        serviceProperties.setKindName(request.getKindName());

        byte[] base64Props = CommonUtils.encodeObjectToBase64(serviceProperties);
        ru.rosreestr.client.isur.model.base64.ServiceProperties servicePropertiesBase64 = new ru.rosreestr.client.isur.model.base64.ServiceProperties();
        servicePropertiesBase64.setData(base64Props);
        servicePropertiesBase64.setSignature(createSignature(base64Props));
        parameter.setAny(servicePropertiesBase64);

        return parameter;
    }

    private byte[] createSignature(byte[] data) {
        try {
            return SignatureUtils.sign(data, properties.getSignatureAlias(), properties.getSignaturePassword().toCharArray());
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }
}
