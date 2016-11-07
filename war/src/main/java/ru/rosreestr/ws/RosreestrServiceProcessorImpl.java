package ru.rosreestr.ws;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.rosreestr.client.isur.model.*;
import ru.rosreestr.client.isur.model.ServiceProperties;
import ru.rosreestr.client.isur.model.base64.*;
import ru.rosreestr.client.isur.processor.IsurClientProcessor;
import ru.rosreestr.exception.DuplicateWebServiceException;
import ru.rosreestr.exception.DuplicateWebServiceParamException;
import ru.rosreestr.exception.NotFoundWebServiceException;
import ru.rosreestr.exception.NotFoundWebServiceParamException;
import ru.rosreestr.persistence.model.*;
import ru.rosreestr.persistence.repository.CommonRepositoryImpl;
import ru.rosreestr.service.LoggerDbService;
import ru.rosreestr.service.WebServiceConfigService;
import ru.rosreestr.service.WebServiceService;
import ru.rosreestr.utils.CommonUtils;
import ru.rosreestr.utils.SignatureUtils;
import ru.rosreestr.ws.model.GetInformationRequest;
import ru.rosreestr.ws.model.GetInformationResponse;

import javax.annotation.PostConstruct;
import java.lang.Object;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Tatiana Chukina on 04.10.2016 23:54.
 */
@Service("rosreestrServiceProcessor")
public class RosreestrServiceProcessorImpl implements RosreestrServiceProcessor {

    private static final Logger LOG = Logger.getLogger(RosreestrServiceProcessorImpl.class);

    public static final WebServiceCode CODE = WebServiceCode.ISUR_PROXY;

    private Integer serviceId;

    private static final String FROM_ORG_CODE = "707";
    private static final String TO_ORG_CODE = "1111";
    private static final String SERVICE_NUMBER_TEMPLATE = "0707-9000095-047201-%s/%s";
    private static final String SERVICE_TYPE_CODE = "047201";
    private static final String DOCUMENT_TYPE_CODE = "77290";

    @Autowired
    private IsurClientProcessor serviceClient;

    @Autowired
    private WebServiceConfigService wsParamsService;

    @Autowired
    private LoggerDbService loggerDbService;

    @Autowired
    CommonRepositoryImpl commonRepository;

    @Autowired
    private WebServiceService wsService;


    @PostConstruct
    protected void init() throws NotFoundWebServiceException, DuplicateWebServiceException, NotFoundWebServiceParamException, DuplicateWebServiceParamException, MalformedURLException {

        WebService webService = wsService.findByCode(CODE);
        serviceId = webService.getServiceId();

    }

    @Override
    public GetInformationResponse getInformation(GetInformationRequest request) throws NotFoundWebServiceParamException, DuplicateWebServiceParamException, MalformedURLException {
        LOG.info("ru.rosreestr.ws.RosreestrServiceProcessor.sendRequest");
        BigDecimal nextMessageNum = commonRepository.getNextMessageNum();
        DecimalFormat df = new DecimalFormat("#000000");
        String serviceNumber = String.format(SERVICE_NUMBER_TEMPLATE,
                df.format(nextMessageNum), CommonUtils.getCurrentYear());

        serviceClient.sendTask(createTaskMessage(request, serviceNumber, nextMessageNum), createHeaders(serviceNumber));
        GetInformationResponse sendRequestResponse = new GetInformationResponse();
        sendRequestResponse.setServiceNumber(serviceNumber);

     //   serviceClient.getRequestsList(createRequestIn());
        return sendRequestResponse;
    }

    private GetRequestListInMessage createRequestIn() {
        GetRequestListInMessage requestListInMessage = new GetRequestListInMessage();
        requestListInMessage.setServiceCode(SERVICE_TYPE_CODE);
        requestListInMessage.setToDate(CommonUtils.getXmlGregorianCurrentDate());
        requestListInMessage.setFromDate(CommonUtils.getXmlGregorianCurrentDate());
        return requestListInMessage;
    }

    private Headers createHeaders(String serviceNumber) {
        Headers headers = new Headers();
        headers.setFromOrgCode(FROM_ORG_CODE);
        headers.setToOrgCode(TO_ORG_CODE);
        headers.setRequestDateTime(CommonUtils.getXmlGregorianCurrentDate());
        headers.setMessageId(UUID.randomUUID().toString());
        headers.setServiceNumber(serviceNumber);
        return headers;
    }

    private CoordinateTaskData createTaskMessage(GetInformationRequest request, String serviceNumber, BigDecimal nextMessageNum) {
        CoordinateTaskData coordinateTaskData = new CoordinateTaskData();
        RequestTask requestTask = createRequestTask(serviceNumber);
        DocumentsRequestData documentsRequestData = createDocumentsRequestData(request, nextMessageNum);

        coordinateTaskData.setTask(requestTask);
        coordinateTaskData.setData(documentsRequestData);

        return coordinateTaskData;
    }

    private RequestTask createRequestTask(String serviceNumber) {
        RequestTask requestTask = new RequestTask();
        requestTask.setRequestId(UUID.randomUUID().toString());
        requestTask.setServiceNumber(serviceNumber);
        requestTask.setServiceTypeCode(SERVICE_TYPE_CODE);

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
        department.setCode(FROM_ORG_CODE);
        department.setInn("7710878000");
        department.setOgrn("1107746943347");
        requestTask.setDepartment(department);

        return requestTask;
    }

    private DocumentsRequestData createDocumentsRequestData(GetInformationRequest request, BigDecimal nextMessageNum) {
        DocumentsRequestData documentsRequestData = new DocumentsRequestData();
        documentsRequestData.setDocumentTypeCode(DOCUMENT_TYPE_CODE);
        documentsRequestData.setIncludeXmlView(true);
        documentsRequestData.setIncludeBinaryView(true);
        documentsRequestData.setParameterTypeCode(DOCUMENT_TYPE_CODE);
        DocumentsRequestData.Parameter parameter = createRequestGRPParameter(request, nextMessageNum);
        documentsRequestData.setParameter(parameter);
        return documentsRequestData;
    }

    /**
     * Creates {@link ru.rosreestr.client.isur.model.base64.RequestGRP} data
     * @param request
     * @param nextMessageNum
     * @return
     */
    private DocumentsRequestData.Parameter createRequestGRPParameter(GetInformationRequest request, BigDecimal nextMessageNum) {
        RequestGRP requestGRP = new RequestGRP();
        EDocument eDocument = new EDocument();
        eDocument.setGUID(UUID.randomUUID().toString());
        eDocument.setVersion(1.16);
        requestGRP.setEDocument(eDocument);

        FIRInfo firInfo = new FIRInfo();
        firInfo.setMonitoring(0);
        requestGRP.setFIRInfo(firInfo);

        Request internalReq = new Request();
        Payment payment = new Payment();
        payment.setFree("true");
        internalReq.setPayment(payment);

        Delivery delivery = new Delivery();
        delivery.setWebService("true");
        internalReq.setDelivery(delivery);

        AppliedDocuments appliedDocuments = new AppliedDocuments();
        AppliedDocument appliedDocument = new AppliedDocument();
        appliedDocument.setName("Запрос о предоставлении сведений, содержащихся в Едином государственном реестре прав на недвижимое имущество и сделок с ним");
        appliedDocument.setNumber(nextMessageNum.intValue());
        appliedDocument.setCodeDocument("558102100000");
        appliedDocument.setDate(CommonUtils.getXmlGregorianCurrentDate());
        Quantity quantity = new Quantity();
        Original original = new Original();
        original.setQuantity(1);
        original.setQuantitySheet(1);
        quantity.setOriginal(original);
        appliedDocument.setQuantity(quantity);
        appliedDocuments.setAppliedDocument(appliedDocument);
        internalReq.setAppliedDocuments(appliedDocuments);

        Declarant declarant = new Declarant();
        declarant.setDeclarantKind("357013000000");
        declarant.setSignatured("true");

        Governance governance = new Governance();
        governance.setName("Департамент информационных технологий города Москвы");
        governance.setGovernanceCode("007001001002");
        governance.setEMail("test@test.test");
        Agent agent = new Agent();
        agent.setEMail("test@test.test");
        agent.setAgentKind("356005000000");
        FIO fio = new FIO();
        fio.setFirst("Петр");
        fio.setPatronymic("Аркадьевич");
        fio.setSurname("Столыпин");
        agent.setFIO(fio);
        Document document = new Document();
        document.setCodeDocument("008001002000");
        document.setNumber("999999");
        document.setSeries("9999");
        document.setDate(CommonUtils.getXmlGregorianCurrentDate());
        document.setIssueOrgan("ОВД ГОР.МОСКВЫ");
        agent.setDocument(document);
        governance.setAgent(agent);
        declarant.setGovernance(governance);
        internalReq.setDeclarant(declarant);

        RequiredData requiredData = new RequiredData();
        RequiredDataRealty requiredDataRealty = new RequiredDataRealty();
        ExtractRealty extractRealty = new ExtractRealty();
        Objects objects = new Objects();
        ru.rosreestr.client.isur.model.base64.Object object = new ru.rosreestr.client.isur.model.base64.Object();
        CadastralNumbers cadastralNumbers = new CadastralNumbers();
        cadastralNumbers.setCadastralNumber(request.getCadastralnumber());
        object.setCadastralNumbers(cadastralNumbers);
        objects.setObject(object);
        extractRealty.setObjects(objects);
        requiredDataRealty.setExtractRealty(extractRealty);
        requiredData.setRequiredDataRealty(requiredDataRealty);
        internalReq.setRequiredData(requiredData);
        requestGRP.setRequest(internalReq);
        DocumentsRequestData.Parameter parameter = createParameter(requestGRP);
        return parameter;
    }

    /**
     * Creates {@link ServiceProperties} data
     * @param request
     * @return
     */
    private DocumentsRequestData.Parameter createServicePropertiesParameter(GetInformationRequest request) {
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

        DocumentsRequestData.Parameter parameter = createParameter(serviceProperties);

        return parameter;
    }

    private DocumentsRequestData.Parameter createParameter(Object data) {
        DocumentsRequestData.Parameter parameter = new DocumentsRequestData.Parameter();
        byte[] base64Props = CommonUtils.marshalObjectToByte(data);
        ru.rosreestr.client.isur.model.base64.ServiceProperties servicePropertiesBase64 = new ru.rosreestr.client.isur.model.base64.ServiceProperties();
        servicePropertiesBase64.setData(base64Props);
        servicePropertiesBase64.setSignature(createSignature(base64Props));
        parameter.setAny(servicePropertiesBase64);
        return parameter;
    }

    private byte[] createSignature(byte[] data) {
        try {

            WebServiceConfig aliasParam = wsParamsService.findOneByServiceIdAndName(serviceClient.getServiceId(), WebServiceParam.SIGNATURE_ALIAS, WebServiceParamType.STRING);
            WebServiceConfig passwordParam = wsParamsService.findOneByServiceIdAndName(serviceClient.getServiceId(), WebServiceParam.SIGNATURE_PASSWORD);

            return SignatureUtils.sign(data, aliasParam.getStringValue(), !StringUtils.isEmpty(passwordParam.getStringValue()) ? passwordParam.getStringValue().toCharArray() : null);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            loggerDbService.log(new Date(), new Date(), 0L, serviceId, LogType.JAVA, LogLevel.ERROR, 0, e.getMessage(), ExceptionUtils.getStackTrace(e),"");
        }
        return null;
    }

    @Override
    public Integer getServiceId() {
        return serviceId;
    }
}
