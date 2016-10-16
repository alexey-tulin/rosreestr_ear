package ru.rosreestr.handler.logger;

import org.springframework.stereotype.Component;
import ru.rosreestr.handler.LoggerHandler;
import ru.rosreestr.persistence.model.WebServiceCode;

/**
 * Перехватчик SOAP сообщений.
 * Назначение: логирование входящих/исходящих сообщений.
 * Принадлежит сервису с кодом ISUR.
 */
@Component
public class IsurLoggerHandler extends LoggerHandler {

    public static final WebServiceCode CODE = WebServiceCode.ISUR;

    @Override
    public WebServiceCode getWebServiceCode() {
        return CODE;
    }
}
