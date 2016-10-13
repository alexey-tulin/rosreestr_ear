package ru.rosreestr.ws;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rosreestr.client.isur.model.CoordinateStatusData;
import ru.rosreestr.client.isur.model.ErrorMessage;
import ru.rosreestr.exception.DuplicateWebServiceException;
import ru.rosreestr.exception.DuplicateWebServiceParamException;
import ru.rosreestr.exception.NotFoundWebServiceException;
import ru.rosreestr.exception.NotFoundWebServiceParamException;
import ru.rosreestr.persistence.model.WebService;
import ru.rosreestr.persistence.model.WebServiceCode;
import ru.rosreestr.service.WebServiceService;

import javax.annotation.PostConstruct;
import java.net.MalformedURLException;

/**
 * Created by Tatiana Chukina on 01.10.2016 16:43.
 */
@Service("serviceWSProcessor")
public class IsurServiceProcessorImpl implements IsurServiceProcessor {
    private static final Logger LOG = Logger.getLogger(IsurServiceProcessorImpl.class);

    public static final WebServiceCode CODE = WebServiceCode.ISUR;

    private Integer serviceId;

    @Autowired
    private WebServiceService wsService;

    @PostConstruct
    protected void init() throws NotFoundWebServiceException, DuplicateWebServiceException, NotFoundWebServiceParamException, DuplicateWebServiceParamException, MalformedURLException {

        WebService webService = wsService.findByCode(CODE);
        serviceId = webService.getServiceId();

    }

    @Override
    public void acknowledgement(ErrorMessage parameters) {
        LOG.info("ru.rosreestr.endpoints.ServiceWSProcessor.acknowledgement");
    }

    @Override
    public void setFilesAndStatus(CoordinateStatusData statusMessage) {
        LOG.info("ru.rosreestr.endpoints.ServiceWSProcessor.setFilesAndStatus");
    }

    @Override
    public Integer getServiceId() {
        return serviceId;
    }

}
