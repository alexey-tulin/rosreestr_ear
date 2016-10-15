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
 * Базовое описания перехватчика SOAP сообщений для подписания/проверки подписи сообщений.
 * Обращается к контексту спринга и получает нужный бин-обработчик логирования
 */
public abstract class SignatureHandler implements SOAPHandler<SOAPMessageContext> { //extends SpringBeanAutowiringSupport

    private static final Logger LOGGER = Logger.getLogger(SignatureHandler.class);

    public Set<QName> getHeaders() {
        // todo
        return null;
    }

    public boolean handleMessage(SOAPMessageContext context) {

            try {
                HandlerProcessor loggerProcessor = ApplicationContextProvider.getApplicationContext().getBean(SignatureProcessorImpl.class);
                loggerProcessor.handleMessage(context, getWebServiceCode());
            }  catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
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

    public abstract WebServiceCode getWebServiceCode();
}
