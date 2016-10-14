package ru.rosreestr.ws;

import ru.rosreestr.exception.DuplicateWebServiceParamException;
import ru.rosreestr.exception.NotFoundWebServiceParamException;
import ru.rosreestr.ws.model.GetInformationRequest;
import ru.rosreestr.ws.model.GetInformationResponse;
import ru.rosreestr.ws.model.ObjectFactory;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import java.net.MalformedURLException;

/**
 * Created by Tatiana Chukina on 04.10.2016 22:43.
 * <p/>
 */
@WebService(name = "rosreestrservice", targetNamespace = "http://aisercu.rosreestr.ru/")
@XmlSeeAlso({
        ObjectFactory.class
})
public interface RosreestrService {
    @WebMethod(operationName = "GetInformation", action = "http://aisercu.rosreestr.ru/GetInformation")
    @WebResult(name = "GetInformationResponse", targetNamespace = "http://aisercu.rosreestr.ru/")
    @RequestWrapper(localName = "GetInformation", targetNamespace = "http://aisercu.rosreestr.ru/")
    @ResponseWrapper(localName = "GetInformationResponse", targetNamespace = "http://aisercu.rosreestr.ru/")
    GetInformationResponse getInformation(
            @WebParam(name = "request", targetNamespace = "http://aisercu.rosreestr.ru/")
            GetInformationRequest request) throws NotFoundWebServiceParamException, DuplicateWebServiceParamException, MalformedURLException;
}
