package ru.rosreestr.exception;

/**
 * Исключение: существует более одного параметром с заданным наименованием
 */
public class DuplicateWebServiceParamException extends  Exception {

    public DuplicateWebServiceParamException(String paramName) {
        super("Duplicate WebServiceParam name=" + paramName);
    }
}
