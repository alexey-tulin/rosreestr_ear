package ru.rosreestr.handler.logger;

import ru.rosreestr.handler.LoggerHandler;
import ru.rosreestr.persistence.model.WebServiceCode;

/**
 * Перехватчик SOAP сообщений.
 * Назначение: логирование входящих/исходящих сообщений.
 * Принадлежит сервису с кодом ISUR.
 */
public class IsurLoggerHandler extends LoggerHandler {

    public static final WebServiceCode CODE = WebServiceCode.ISUR;

    @Override
    public WebServiceCode getWebServiceCode() {
        return CODE;
    }
}
