package ru.rosreestr.handler.signature;

import ru.rosreestr.handler.SignatureHandler;
import ru.rosreestr.persistence.model.WebServiceCode;

/**
 * Created by KatrinaBosh on 15.10.2016.
 */
public class IsurProxySignatureHandler extends SignatureHandler {

    public static final WebServiceCode CODE = WebServiceCode.ISUR_PROXY;

    @Override
    public WebServiceCode getWebServiceCode() {
        return CODE;
    }
}
