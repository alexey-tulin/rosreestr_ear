package ru.rosreestr.exception;

import ru.rosreestr.persistence.model.WebServiceConfig;
import ru.rosreestr.persistence.model.WebServiceParamType;

/**
 * Created by KatrinaBosh on 12.10.2016.
 */
public class WebServiceParamTypeException extends Exception {

    public WebServiceParamTypeException(WebServiceConfig param, WebServiceParamType targetType) {
        super(String.format("Required type %d. Info %d", targetType.name(), param.toString()));
    }
}
