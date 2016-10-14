package ru.rosreestr.handler.logger;

import ru.rosreestr.handler.LoggerHandler;
import ru.rosreestr.persistence.model.WebServiceCode;

/**
 * Created by KatrinaBosh on 28.09.2016.
 */
public class IsurLoggerHandler extends LoggerHandler {

    public static final WebServiceCode CODE = WebServiceCode.ISUR;

    @Override
    public WebServiceCode getWebServiceCode() {
        return CODE;
    }
}
