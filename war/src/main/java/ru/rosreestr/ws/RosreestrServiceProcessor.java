package ru.rosreestr.ws;

import ru.rosreestr.ws.model.GetInformationRequest;
import ru.rosreestr.ws.model.GetInformationResponse;

/**
 * Created by KatrinaBosh on 13.10.2016.
 */
public interface RosreestrServiceProcessor {

    GetInformationResponse getInformation(GetInformationRequest request);

    Integer getServiceId();
}
