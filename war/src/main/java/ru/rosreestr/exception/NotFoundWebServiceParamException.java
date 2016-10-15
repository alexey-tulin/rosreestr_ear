package ru.rosreestr.exception;

/**
 * Исключение: параметр с заданным кодом не найден
 */
public class NotFoundWebServiceParamException extends  Exception {

    public NotFoundWebServiceParamException(String name) {
        super("Not found WebServiceConfig name=" + name);
    }
}
