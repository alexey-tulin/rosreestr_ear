package ru.rosreestr.exception;

import ru.rosreestr.persistence.model.WebServiceCode;

/**
 * Исключение: веб-сервис с заданным кодом не найден
 */
public class NotFoundWebServiceException extends  Exception {

    public NotFoundWebServiceException(WebServiceCode code) {
        super("Not found WebService code=" + (code == null ? "null" : code.name()));
    }
}
