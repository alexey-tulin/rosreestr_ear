package ru.rosreestr.exception;

import ru.rosreestr.persistence.model.WebServiceConfig;
import ru.rosreestr.persistence.model.WebServiceParamType;

/**
 * Исключение: тип параметра не сообветствует заданному
 */
public class WebServiceParamTypeException extends Exception {

    public WebServiceParamTypeException(WebServiceConfig param, WebServiceParamType targetType) {
        super(String.format("Required type %d. Info %d", targetType.name(), param.toString()));
    }
}
