package ru.rosreestr.handler;

import ru.rosreestr.exception.DuplicateWebServiceException;
import ru.rosreestr.exception.NotFoundWebServiceException;
import ru.rosreestr.persistence.model.WebServiceCode;

import javax.xml.ws.handler.soap.SOAPMessageContext;

/**
 * Created by KatrinaBosh on 15.10.2016.
 */
public interface HandlerProcessor {
    boolean handleMessage(SOAPMessageContext context, WebServiceCode code) throws DuplicateWebServiceException, NotFoundWebServiceException;
}
