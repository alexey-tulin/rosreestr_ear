package ru.rosreestr.client.isur.processor;

import ru.rosreestr.client.isur.model.CoordinateTaskData;
import ru.rosreestr.client.isur.model.Headers;

/**
 * Created by KatrinaBosh on 13.10.2016.
 */
public interface IsurClientProcessor {

    void sendTask(CoordinateTaskData taskMessage, Headers serviceHeader);

    Integer getServiceId();

}
