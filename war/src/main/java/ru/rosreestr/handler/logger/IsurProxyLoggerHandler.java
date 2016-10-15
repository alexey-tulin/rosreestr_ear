package ru.rosreestr.handler.logger;

import ru.rosreestr.handler.LoggerHandler;
import ru.rosreestr.persistence.model.WebServiceCode;

/**
 * Перехватчик SOAP сообщений.
 * Назначение: логирование входящих/исходящих сообщений.
 * Принадлежит сервису с кодом ISUR_PROXY.
 */
public class IsurProxyLoggerHandler extends LoggerHandler {

    public static final WebServiceCode CODE = WebServiceCode.ISUR_PROXY;

    @Override
    public WebServiceCode getWebServiceCode() {
        return CODE;
    }
}
