package ru.rosreestr.handler;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.rosreestr.persistence.model.LogLevel;
import ru.rosreestr.persistence.model.LogType;
import ru.rosreestr.service.LoggerDbService;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.Set;

/**
 * Created by KatrinaBosh on 28.09.2016.
 */
@Component
@Scope("prototype")
public class LoggerHandler implements SOAPHandler<SOAPMessageContext> {

    private static final Logger LOG = Logger.getLogger(LoggerHandler.class);

    private Integer serviceId;

    private boolean isLogXmlEnable;

    @Autowired
    private LoggerDbService loggerDbService;

    public boolean handleMessage(SOAPMessageContext context) {

        try {

            SOAPMessage msg = context.getMessage();
            Boolean outboundProperty = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
            Integer serviceId = getServiceId();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            msg.writeTo(baos);

            String descriptionStep0 =  Boolean.TRUE.equals(outboundProperty) ? " SOAP REQUEST: " : " SOAP RESPONSE: ";
            LOG.info(descriptionStep0);
            LOG.info(baos);

            if (isLogXmlEnable()) {
                loggerDbService.logXml(serviceId, baos.toString(), Boolean.TRUE.equals(outboundProperty) ? 1 : 0);
            }

            //throw new Exception();

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            loggerDbService.log(new Date(), new Date(), 0L, serviceId, LogType.JAVA, LogLevel.ERROR, 0, e.getMessage(), ExceptionUtils.getStackTrace(e),"");
        }
        return true;
    }

    public boolean handleFault(SOAPMessageContext context) {
        return true;
    }

    public void close(MessageContext context) {
        // --
    }

    public Set<QName> getHeaders() {
        // --
        return null;
    }

    public boolean isLogXmlEnable() {
        return isLogXmlEnable;
    }

    public void setIsLogXmlEnable(boolean isLogXmlEnable) {
        this.isLogXmlEnable = isLogXmlEnable;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }
}
