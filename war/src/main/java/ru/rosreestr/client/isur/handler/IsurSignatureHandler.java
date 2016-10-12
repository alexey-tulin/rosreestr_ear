package ru.rosreestr.client.isur.handler;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import ru.rosreestr.config.AppProperties;
import ru.rosreestr.utils.SignatureUtils;

import javax.annotation.Resource;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.io.ByteArrayOutputStream;
import java.util.Set;
@Component
public class IsurSignatureHandler implements SOAPHandler<SOAPMessageContext> { //extends SpringBeanAutowiringSupport

    private static final Logger LOGGER = Logger.getLogger(IsurSignatureHandler.class);

    @Resource
    private AppProperties properties;

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

                    SignatureUtils.addSecurityBlock(message, properties.getSignatureAlias(), properties.getSignaturePassword().toCharArray());

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
            }  catch (Exception ex) {
                LOGGER.error(ex.getMessage(), ex);
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
}
