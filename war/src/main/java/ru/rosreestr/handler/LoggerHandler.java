package ru.rosreestr.handler;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.rosreestr.persistence.model.WebServiceCode;

import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.util.Set;

/**
 * Базовое описания перехватчика SOAP сообщений для логирования сообщений.
 * Обращается к контексту спринга и получает нужный бин-обработчик логирования
 */
public class LoggerHandler implements SOAPHandler<SOAPMessageContext> {

    private static final Logger LOG = Logger.getLogger(LoggerHandler.class);

    private final WebServiceCode code;
    @Autowired
    @Qualifier(value = "loggerProcessorImpl")
    private HandlerProcessor loggerProcessor;

    public LoggerHandler(WebServiceCode code) {
        this.code = code;
    }

    public boolean handleMessage(SOAPMessageContext context) {

        try {
            loggerProcessor.handleMessage(context, this.code);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
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
}
