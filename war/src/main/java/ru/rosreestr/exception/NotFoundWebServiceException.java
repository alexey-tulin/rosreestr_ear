package ru.rosreestr.exception;

import ru.rosreestr.persistence.model.WebServiceCode;

/**
 * Created by KatrinaBosh on 09.10.2016.
 */
public class NotFoundWebServiceException extends  Exception {

    public NotFoundWebServiceException(WebServiceCode code) {
        super("Not found WebService code=" + code.name());
    }
}
