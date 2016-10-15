package ru.rosreestr.handler;

import ru.rosreestr.exception.DuplicateWebServiceException;
import ru.rosreestr.exception.NotFoundWebServiceException;
import ru.rosreestr.persistence.model.WebServiceCode;

import javax.xml.ws.handler.soap.SOAPMessageContext;

/**
 * Общий интерфейс для обработчиков SOAP сообщений
 */
public interface HandlerProcessor {

    /**
     * Обработка входящего/исходящего сообщения
     *
     * @param context контекст сообщения
     * @param code код сервиса отправляющего/получающего сообщение
     * @return
     * @throws DuplicateWebServiceException
     * @throws NotFoundWebServiceException
     */
    boolean handleMessage(SOAPMessageContext context, WebServiceCode code) throws DuplicateWebServiceException, NotFoundWebServiceException;
}
