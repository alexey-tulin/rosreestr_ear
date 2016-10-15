package ru.rosreestr.exception;

import ru.rosreestr.persistence.model.WebService;
import ru.rosreestr.persistence.model.WebServiceCode;

import java.util.List;

/**
 * Исключение: существует более одного веб-сервиса с одинаковым кодом
 */
public class DuplicateWebServiceException  extends  Exception {

    public DuplicateWebServiceException(List<WebService> webServices, WebServiceCode code) {
        super("WebServices " + webServices.toString() + " have code=" + code.name());
    }
}
