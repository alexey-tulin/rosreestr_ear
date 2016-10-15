package ru.rosreestr.handler.signature;

import ru.rosreestr.handler.SignatureHandler;
import ru.rosreestr.persistence.model.WebServiceCode;

/**
 * Перехватчик SOAP сообщений.
 * Назначение: подписывание исходящих сообщений, проверка подписи входящих сообщений.
 * Принадлежит сервису с кодом ISUR.
 */
public class IsurSignatureHandler extends SignatureHandler {

    public static final WebServiceCode CODE = WebServiceCode.ISUR;

    @Override
    public WebServiceCode getWebServiceCode() {
        return CODE;
    }
}
