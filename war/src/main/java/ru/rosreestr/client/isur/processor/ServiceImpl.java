package ru.rosreestr.client.isur.processor;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import ru.rosreestr.client.isur.IService;
import ru.rosreestr.client.isur.ServiceClient;
import ru.rosreestr.client.isur.handler.IsurSignatureHandler;
import ru.rosreestr.client.isur.model.CoordinateTaskData;
import ru.rosreestr.client.isur.model.Headers;
import ru.rosreestr.exception.DuplicateWebServiceException;
import ru.rosreestr.exception.DuplicateWebServiceParamException;
import ru.rosreestr.exception.NotFoundWebServiceException;
import ru.rosreestr.exception.NotFoundWebServiceParamException;
import ru.rosreestr.handler.LoggerHandler;
import ru.rosreestr.persistence.model.WebService;
import ru.rosreestr.persistence.model.WebServiceCode;
import ru.rosreestr.persistence.model.WebServiceConfig;
import ru.rosreestr.persistence.model.WebServiceParam;
import ru.rosreestr.service.WebServiceConfigService;
import ru.rosreestr.service.WebServiceService;

import javax.annotation.PostConstruct;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by Tatiana Chukina on 28.09.2016 23:26.
 * <p/>
 * Client for invoking {@link IService}
 */
@org.springframework.stereotype.Service
public class ServiceImpl {
    private static final Logger LOG = Logger.getLogger(ServiceImpl.class);
    private static final WebServiceCode code = WebServiceCode.ISUR;

    private ServiceClient serviceClient;

    @Autowired
    private WebServiceService wsService;

    @Autowired
    private WebServiceConfigService wsParamsService;

    @Autowired
    ApplicationContext applicationContext;

    /**
     * Method for invoking {@link IService#sendTask} service
     */
    public void sendTask(CoordinateTaskData taskMessage, Headers serviceHeader) {
        LOG.info("start service sendTask");
        IService customBindingIService = serviceClient.getCustomBindingIService();
        customBindingIService.sendTask(taskMessage, serviceHeader);
        LOG.info("end service sendTask");
    }

    @PostConstruct
    protected void init() throws NotFoundWebServiceException, DuplicateWebServiceException, NotFoundWebServiceParamException, DuplicateWebServiceParamException, MalformedURLException {

        // todo возможно имеет смысл по другому определять/хранить сервис, который вызываем
        List<WebService> webServices = wsService.findByParam(WebServiceParam.CODE, code.name());

        if (webServices.isEmpty()) {
            throw new NotFoundWebServiceException(code);
        } else if (webServices.size() > 1) {
            throw new DuplicateWebServiceException(webServices, code);
        }

        Integer serviceId = webServices.get(0).getServiceId();

        WebServiceConfig wsdlParam = wsParamsService.findOneByServiceIdAndName(serviceId, WebServiceParam.WSDL);
        URL url = new URL(wsdlParam.getStringValue());

        List<WebServiceConfig> loggingEnableParams = wsParamsService.findByServiceIdAndName(serviceId, WebServiceParam.LOGGING_ENABLE);
        IsurSignatureHandler isurSignatureHandler = applicationContext.getBean(IsurSignatureHandler.class);
        LoggerHandler loggerHandler = applicationContext.getBean(LoggerHandler.class);

        serviceClient = new ServiceClient(url);
        serviceClient.setLoggerHandler(loggerHandler);
        serviceClient.setSignatureHandler(isurSignatureHandler);
        serviceClient.configureLogger(serviceId, !loggingEnableParams.isEmpty() &&
                Boolean.TRUE.equals(loggingEnableParams.get(0).getBooleanValue()));
    }
}
