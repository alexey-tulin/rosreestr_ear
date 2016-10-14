package ru.rosreestr.handler;

import org.apache.log4j.Logger;
import ru.rosreestr.context.ApplicationContextProvider;
import ru.rosreestr.persistence.model.WebServiceCode;

import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.util.Set;

/**
 * Created by KatrinaBosh on 28.09.2016.
 */
public abstract class LoggerHandler implements SOAPHandler<SOAPMessageContext> {

    private static final Logger LOG = Logger.getLogger(LoggerHandler.class);

    public static final WebServiceCode CODE = WebServiceCode.ISUR;

    public boolean handleMessage(SOAPMessageContext context) {

        try {
            HandlerProcessor loggerProcessor = ApplicationContextProvider.getApplicationContext().getBean(LoggerProcessorImpl.class);
            loggerProcessor.handleMessage(context, getWebServiceCode());
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

    public abstract WebServiceCode getWebServiceCode();
}
