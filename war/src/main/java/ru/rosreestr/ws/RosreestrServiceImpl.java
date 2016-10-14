package ru.rosreestr.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rosreestr.exception.DuplicateWebServiceParamException;
import ru.rosreestr.exception.NotFoundWebServiceParamException;
import ru.rosreestr.ws.model.GetInformationRequest;
import ru.rosreestr.ws.model.GetInformationResponse;

import javax.jws.HandlerChain;
import javax.jws.WebService;
import java.net.MalformedURLException;

/**
 * Created by Tatiana Chukina on 04.10.2016 23:52.
 */
@Service("rosreestrservice")
@WebService(serviceName = "rosreestrservice", targetNamespace = "http://aisercu.rosreestr.ru/",
        endpointInterface = "ru.rosreestr.ws.RosreestrService")
@HandlerChain(file="isur-proxy-handler-chain.xml")
public class RosreestrServiceImpl implements RosreestrService {

    @Autowired
    private RosreestrServiceProcessor processor;

    @Override
    public GetInformationResponse getInformation(GetInformationRequest request) throws NotFoundWebServiceParamException, DuplicateWebServiceParamException, MalformedURLException {
        return processor.getInformation(request);
    }
}
