package ru.rosreestr.ws;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rosreestr.client.isur.model.CoordinateStatusData;
import ru.rosreestr.client.isur.model.ErrorMessage;
import ru.rosreestr.client.isur.model.ErrorMessageData;
import ru.rosreestr.client.isur.model.Headers;
import ru.rosreestr.client.isur.processor.IsurClientProcessor;
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

    @Autowired
    private IsurClientProcessor serviceClient;

    @PostConstruct
    protected void init() throws NotFoundWebServiceException, DuplicateWebServiceException, NotFoundWebServiceParamException, DuplicateWebServiceParamException, MalformedURLException {

        WebService webService = wsService.findByCode(CODE);
        serviceId = webService.getServiceId();

    }

    @Override
    public void acknowledgement(ErrorMessage parameters, Headers serviceHeader) {
        LOG.info("ru.rosreestr.endpoints.ServiceWSProcessor.acknowledgement");
    }

    private void sendAcknolegment(Headers serviceHeader) {
        try {
            Headers headers = new Headers();
            headers.setFromOrgCode(serviceHeader.getFromOrgCode());
            headers.setToOrgCode(serviceHeader.getToOrgCode());
            headers.setServiceNumber(serviceHeader.getServiceNumber());
            headers.setRelatesTo(serviceHeader.getMessageId());
            headers.setMessageId(java.util.UUID.randomUUID().toString());
            serviceClient.acknowledgement(creareErrorMessage(), headers);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    private ErrorMessage creareErrorMessage() {
        ErrorMessage errorMessage = new ErrorMessage();
        ErrorMessageData value = new ErrorMessageData();
        value.setErrorCode("0");
        errorMessage.setError(value);
        return errorMessage;
    }

    @Override
    public void setFilesAndStatus(CoordinateStatusData statusMessage, Headers serviceHeader) {
        LOG.info("111"+serviceHeader.getId());
        LOG.info("222"+serviceHeader.getMessageId());

        sendAcknolegment(serviceHeader);
        LOG.info("ru.rosreestr.endpoints.ServiceWSProcessor.setFilesAndStatus");
    }

    @Override
    public Integer getServiceId() {
        return serviceId;
    }

}
