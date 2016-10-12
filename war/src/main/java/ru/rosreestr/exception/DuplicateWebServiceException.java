package ru.rosreestr.exception;

import ru.rosreestr.persistence.model.WebService;
import ru.rosreestr.persistence.model.WebServiceCode;

import java.util.List;

/**
 * Created by KatrinaBosh on 09.10.2016.
 */
public class DuplicateWebServiceException  extends  Exception {

    public DuplicateWebServiceException(List<WebService> webServices, WebServiceCode code) {
        super("WebServices " + webServices.toString() + " have code=" + code.name());
    }
}
