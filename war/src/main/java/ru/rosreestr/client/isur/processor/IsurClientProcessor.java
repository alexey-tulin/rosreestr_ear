package ru.rosreestr.client.isur.processor;

import ru.rosreestr.client.isur.model.CoordinateTaskData;
import ru.rosreestr.client.isur.model.ErrorMessage;
import ru.rosreestr.client.isur.model.GetRequestListInMessage;
import ru.rosreestr.client.isur.model.Headers;
import ru.rosreestr.exception.DuplicateWebServiceParamException;
import ru.rosreestr.exception.NotFoundWebServiceParamException;

import java.net.MalformedURLException;

/**
 *
 */
public interface IsurClientProcessor {

    void sendTask(CoordinateTaskData taskMessage, Headers serviceHeader) throws NotFoundWebServiceParamException, DuplicateWebServiceParamException, MalformedURLException;

    void getRequestsList(GetRequestListInMessage requestListInMessage) throws NotFoundWebServiceParamException, DuplicateWebServiceParamException, MalformedURLException;

    void acknowledgement(ErrorMessage parameters, Headers serviceHeader) throws NotFoundWebServiceParamException, DuplicateWebServiceParamException, MalformedURLException;

    Integer getServiceId();

}
