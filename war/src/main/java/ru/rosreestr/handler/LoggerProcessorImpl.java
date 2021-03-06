package ru.rosreestr.handler;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.rosreestr.exception.DuplicateWebServiceException;
import ru.rosreestr.exception.NotFoundWebServiceException;
import ru.rosreestr.persistence.model.*;
import ru.rosreestr.service.LoggerDbService;
import ru.rosreestr.service.WebServiceConfigService;
import ru.rosreestr.service.WebServiceService;

import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.List;

/**
 * Обработчик логирования в БД входящих/исходящих SOAP сообщений
 */
@Component("loggerProcessorImpl")
public class LoggerProcessorImpl implements HandlerProcessor {

    private static final Logger LOG = Logger.getLogger(LoggerProcessorImpl.class);

    @Autowired
    private WebServiceService wsService;

    @Autowired
    private WebServiceConfigService wsParamsService;

    @Autowired
    private LoggerDbService loggerDbService;

    @Override
    public boolean handleMessage(SOAPMessageContext context, WebServiceCode code) throws DuplicateWebServiceException, NotFoundWebServiceException {
        Integer serviceId;
        boolean isLogXmlEnable;
        WebService webService = wsService.findByCode(code);
        serviceId = webService.getServiceId();

        try {

            List<WebServiceConfig> loggingEnableParams = wsParamsService.findByServiceIdAndName(serviceId, WebServiceParam.LOGGING_ENABLE);
            isLogXmlEnable = !loggingEnableParams.isEmpty() &&
                    Boolean.TRUE.equals(loggingEnableParams.get(0).getBooleanValue());

            SOAPMessage msg = context.getMessage();
            Boolean outboundProperty = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            msg.writeTo(baos);

            String descriptionStep0 =  Boolean.TRUE.equals(outboundProperty) ? " SOAP REQUEST: " : " SOAP RESPONSE: ";
            LOG.info(descriptionStep0);
            LOG.info(baos);

            if (isLogXmlEnable) {
                loggerDbService.logXml(serviceId, baos.toString(), Boolean.TRUE.equals(outboundProperty) ? 1 : 0);
            }

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            try {
                loggerDbService.log(new Date(), new Date(), 0L, serviceId, LogType.JAVA, LogLevel.ERROR, 0, e.getMessage(), ExceptionUtils.getStackTrace(e));
            } catch (Exception dbEx) {
                LOG.error("Error logging Exception to DB", dbEx);
        }
        }
        return true;
    }

}
