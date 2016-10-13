package ru.rosreestr.ws;

import ru.rosreestr.client.isur.model.CoordinateStatusData;
import ru.rosreestr.client.isur.model.ErrorMessage;

/**
 * Created by KatrinaBosh on 13.10.2016.
 */
public interface IsurServiceProcessor {

    void acknowledgement(ErrorMessage parameters);

    void setFilesAndStatus(CoordinateStatusData statusMessage);

    Integer getServiceId();
}
