package ru.rosreestr.ws;

import ru.rosreestr.client.isur.model.CoordinateStatusData;
import ru.rosreestr.client.isur.model.ErrorMessage;
import ru.rosreestr.client.isur.model.Headers;
/**
 * Created by KatrinaBosh on 13.10.2016.
 */
public interface IsurServiceProcessor {

    void acknowledgement(ErrorMessage parameters, Headers serviceHeader);

    void setFilesAndStatus(CoordinateStatusData statusMessage, Headers serviceHeader);

    Integer getServiceId();
}
