package ru.rosreestr.exception;

/**
 * Created by KatrinaBosh on 09.10.2016.
 */
public class NotFoundWebServiceParamException extends  Exception {

    public NotFoundWebServiceParamException(String name) {
        super("Not found WebServiceConfig name=" + name);
    }
}
