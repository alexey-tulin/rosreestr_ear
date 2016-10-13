package ru.rosreestr.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rosreestr.exception.DuplicateWebServiceException;
import ru.rosreestr.exception.DuplicateWebServiceParamException;
import ru.rosreestr.exception.NotFoundWebServiceException;
import ru.rosreestr.exception.NotFoundWebServiceParamException;
import ru.rosreestr.handler.LoggerHandler;
import ru.rosreestr.handler.SignatureHandler;
import ru.rosreestr.persistence.model.WebServiceConfig;
import ru.rosreestr.persistence.model.WebServiceParam;
import ru.rosreestr.service.WebServiceConfigService;
import ru.rosreestr.ws.model.GetInformationRequest;
import ru.rosreestr.ws.model.GetInformationResponse;

import javax.annotation.PostConstruct;
import javax.jws.WebService;
import java.net.MalformedURLException;
import java.util.List;

/**
 * Created by Tatiana Chukina on 04.10.2016 23:52.
 */
@Service("rosreestrservice")
@WebService(serviceName = "rosreestrservice", targetNamespace = "http://aisercu.rosreestr.ru/",
        endpointInterface = "ru.rosreestr.ws.RosreestrService")
public class RosreestrServiceImpl implements RosreestrService {

    @Autowired
    private WebServiceConfigService wsParamsService;

    @Autowired
    private RosreestrServiceProcessor processor;

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

    @Override
    public GetInformationResponse getInformation(GetInformationRequest request) {
        return processor.getInformation(request);
    }
}
