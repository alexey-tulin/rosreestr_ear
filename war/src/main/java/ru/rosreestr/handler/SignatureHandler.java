package ru.rosreestr.handler;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import ru.rosreestr.persistence.model.*;
import ru.rosreestr.service.LoggerDbService;
import ru.rosreestr.service.WebServiceConfigService;
import ru.rosreestr.utils.SignatureUtils;

import javax.annotation.Resource;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.Set;

@Component
@Scope("prototype")
public class SignatureHandler implements SOAPHandler<SOAPMessageContext> { //extends SpringBeanAutowiringSupport

    private static final Logger LOGGER = Logger.getLogger(SignatureHandler.class);

    @Resource
    private WebServiceConfigService configService;

    @Autowired
    private LoggerDbService loggerDbService;

    private Integer serviceId;

    public Set<QName> getHeaders() {
        // todo
        return null;
    }

    public boolean handleMessage(SOAPMessageContext context) {
        if (context != null) {
            try {
                final Boolean outbound = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

                if (outbound != null && outbound) {
                    LOGGER.info(SOAPMessage.class.getSimpleName());
                    SOAPMessage message = context.getMessage();

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    try {
                        message.writeTo(baos);
                        LOGGER.debug(baos.toString("UTF-8"));
                    } catch (Exception e) {
                        LOGGER.error("SOAPMessage err", e);
                    }

                    WebServiceConfig aliasParam = configService.findOneByServiceIdAndName(serviceId, WebServiceParam.SIGNATURE_ALIAS, WebServiceParamType.STRING);
                    WebServiceConfig passwordParam = configService.findOneByServiceIdAndName(serviceId, WebServiceParam.SIGNATURE_PASSWORD);
                    SignatureUtils.addSecurityBlock(message, aliasParam.getStringValue(), !StringUtils.isEmpty(passwordParam.getStringValue()) ? passwordParam.getStringValue().toCharArray() : null);

                    message.saveChanges();

                    baos = new ByteArrayOutputStream();
                    try {
                        message.writeTo(baos);
                        LOGGER.debug(baos.toString("UTF-8"));
                    } catch (Exception e) {
                        LOGGER.error("SOAPMessage err", e);
                    }

                    //SignatureUtils.verify(message.getSOAPPart());
                }
                return true;
            }  catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
                loggerDbService.log(new Date(), new Date(), 0L, serviceId, LogType.JAVA, LogLevel.ERROR, 0, e.getMessage(), ExceptionUtils.getStackTrace(e),"");
            }
        }
        return true;
    }

    public boolean handleFault(SOAPMessageContext context) {
        // todo
        return true;
    }

    public void close(MessageContext context) {
        // todo

    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }
}
