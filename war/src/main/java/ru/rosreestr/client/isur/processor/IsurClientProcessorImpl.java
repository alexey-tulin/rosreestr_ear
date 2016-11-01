package ru.rosreestr.client.isur.processor;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import ru.rosreestr.client.isur.IService;
import ru.rosreestr.client.isur.ServiceClient;
import ru.rosreestr.client.isur.model.CoordinateTaskData;
import ru.rosreestr.client.isur.model.ErrorMessage;
import ru.rosreestr.client.isur.model.GetRequestListInMessage;
import ru.rosreestr.client.isur.model.Headers;
import ru.rosreestr.exception.DuplicateWebServiceException;
import ru.rosreestr.exception.DuplicateWebServiceParamException;
import ru.rosreestr.exception.NotFoundWebServiceException;
import ru.rosreestr.exception.NotFoundWebServiceParamException;
import ru.rosreestr.persistence.model.WebService;
import ru.rosreestr.persistence.model.WebServiceCode;
import ru.rosreestr.persistence.model.WebServiceConfig;
import ru.rosreestr.persistence.model.WebServiceParam;
import ru.rosreestr.service.WebServiceConfigService;
import ru.rosreestr.service.WebServiceService;

import javax.annotation.PostConstruct;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Tatiana Chukina on 28.09.2016 23:26.
 * <p/>
 * Client for invoking {@link IService}
 */
@org.springframework.stereotype.Service
public class IsurClientProcessorImpl implements IsurClientProcessor {

    private static final Logger LOG = Logger.getLogger(IsurClientProcessorImpl.class);

    public static final WebServiceCode CODE = WebServiceCode.ISUR;

    private Integer serviceId;

    @Autowired
    private WebServiceService wsService;

    @Autowired
    private WebServiceConfigService wsParamsService;

    @Autowired
    ApplicationContext applicationContext;

    /**
     * Method for invoking {@link IService#sendTask} service
     */
    @Override
    public void sendTask(CoordinateTaskData taskMessage, Headers serviceHeader) throws NotFoundWebServiceParamException, DuplicateWebServiceParamException, MalformedURLException {
        LOG.info("start service sendTask");
        IService customBindingIService = getiService();
        customBindingIService.sendTask(taskMessage, serviceHeader);
        LOG.info("end service sendTask");
    }

    @Override
    public void getRequestsList(GetRequestListInMessage requestListInMessage) throws NotFoundWebServiceParamException, DuplicateWebServiceParamException, MalformedURLException {
        LOG.info("start service getRequestsList");
        IService customBindingIService = getiService();
        customBindingIService.getRequestList(requestListInMessage);
        LOG.info("end service getRequestsList");
    }

    @Override
    public void acknowledgement(ErrorMessage parameters, Headers serviceHeader) throws NotFoundWebServiceParamException, DuplicateWebServiceParamException, MalformedURLException {
        LOG.info("start service acknowledgement");
        IService customBindingIService = getiService();
        customBindingIService.acknowledgement(parameters, serviceHeader);
        LOG.info("end service acknowledgement");
    }

    @PostConstruct
    protected void init() throws NotFoundWebServiceException, DuplicateWebServiceException, NotFoundWebServiceParamException, DuplicateWebServiceParamException, MalformedURLException {

        WebService webService = wsService.findByCode(CODE);
        serviceId = webService.getServiceId();

    }

    @Override
    public Integer getServiceId() {
        return serviceId;
    }

    private IService getiService() throws NotFoundWebServiceParamException, DuplicateWebServiceParamException, MalformedURLException {
        WebServiceConfig wsdlParam = wsParamsService.findOneByServiceIdAndName(serviceId, WebServiceParam.WSDL);
        URL url = new URL(wsdlParam.getStringValue());
        ServiceClient serviceClient = new ServiceClient(url);
        return serviceClient.getCustomBindingIService();
    }
}
