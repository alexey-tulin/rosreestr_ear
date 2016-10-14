package ru.rosreestr.ws;

import ru.rosreestr.exception.DuplicateWebServiceParamException;
import ru.rosreestr.exception.NotFoundWebServiceParamException;
import ru.rosreestr.ws.model.GetInformationRequest;
import ru.rosreestr.ws.model.GetInformationResponse;

import java.net.MalformedURLException;

/**
 * Created by KatrinaBosh on 13.10.2016.
 */
public interface RosreestrServiceProcessor {

    GetInformationResponse getInformation(GetInformationRequest request) throws NotFoundWebServiceParamException, DuplicateWebServiceParamException, MalformedURLException;

    Integer getServiceId();
}
