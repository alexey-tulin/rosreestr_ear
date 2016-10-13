package ru.rosreestr.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.rosreestr.client.isur.model.CoordinateStatusData;
import ru.rosreestr.client.isur.model.ErrorMessage;
import ru.rosreestr.exception.DuplicateWebServiceException;
import ru.rosreestr.exception.DuplicateWebServiceParamException;
import ru.rosreestr.exception.NotFoundWebServiceException;
import ru.rosreestr.exception.NotFoundWebServiceParamException;
import ru.rosreestr.handler.LoggerHandler;
import ru.rosreestr.handler.SignatureHandler;
import ru.rosreestr.persistence.model.WebServiceConfig;
import ru.rosreestr.persistence.model.WebServiceParam;
import ru.rosreestr.service.WebServiceConfigService;

import javax.annotation.PostConstruct;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.net.MalformedURLException;
import java.util.List;

/**
 * Created by Tatiana Chukina on 01.10.2016 15:35.
 * <p/>
 * Web service for external systems
 */
@Service("isurtestws")
@WebService(serviceName = "isurtestws", targetNamespace = "http://asguf.mos.ru/rkis_gu/coordinate/v5/",
        endpointInterface = "ru.rosreestr.ws.IsurService")
public class IsurServiceImpl implements IsurService {

    @Autowired
    @Qualifier("serviceWSProcessor")
    private IsurServiceProcessor processor;

    @Autowired
    private WebServiceConfigService wsParamsService;

    @Autowired
    SignatureHandler signatureHandler;

    @Autowired
    LoggerHandler loggerHandler;

    @PostConstruct
    protected void init() throws NotFoundWebServiceException, DuplicateWebServiceException, NotFoundWebServiceParamException, DuplicateWebServiceParamException, MalformedURLException {

        List<WebServiceConfig> loggingEnableParams = wsParamsService.findByServiceIdAndName(processor.getServiceId(), WebServiceParam.LOGGING_ENABLE);

        loggerHandler.setServiceId(processor.getServiceId());
        loggerHandler.setIsLogXmlEnable(!loggingEnableParams.isEmpty() &&
                Boolean.TRUE.equals(loggingEnableParams.get(0).getBooleanValue()));
        signatureHandler.setServiceId(processor.getServiceId());

    }

    public void setFilesAndStatus(@WebParam(name = "StatusMessage", targetNamespace = "http://asguf.mos.ru/rkis_gu/coordinate/v5/", partName = "StatusMessage")
                                  CoordinateStatusData statusMessage) {
        processor.setFilesAndStatus(statusMessage);
    }

    public void acknowledgement(@WebParam(name = "ErrorMessage", targetNamespace = "http://asguf.mos.ru/rkis_gu/coordinate/v5/", partName = "parameters")
                                ErrorMessage parameters) {
        processor.acknowledgement(parameters);
    }
}
