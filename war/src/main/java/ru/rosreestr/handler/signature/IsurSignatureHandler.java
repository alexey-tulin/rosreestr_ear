package ru.rosreestr.handler.signature;

import ru.rosreestr.handler.SignatureHandler;
import ru.rosreestr.persistence.model.WebServiceCode;

/**
 * Created by KatrinaBosh on 15.10.2016.
 */
public class IsurSignatureHandler extends SignatureHandler {

    public static final WebServiceCode CODE = WebServiceCode.ISUR;

    @Override
    public WebServiceCode getWebServiceCode() {
        return CODE;
    }
}
