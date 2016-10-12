package ru.rosreestr.exception;

/**
 * Created by KatrinaBosh on 09.10.2016.
 */
public class DuplicateWebServiceParamException extends  Exception {

    public DuplicateWebServiceParamException(String paramName) {
        super("Duplicate WebServiceParam name=" + paramName);
    }
}
