package ru.rosreestr.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rosreestr.ws.model.GetInformationRequest;
import ru.rosreestr.ws.model.GetInformationResponse;

import javax.jws.WebService;

/**
 * Created by Tatiana Chukina on 04.10.2016 23:52.
 */
@Service("rosreestrservice")
@WebService(serviceName = "rosreestrservice", targetNamespace = "http://aisercu.rosreestr.ru/",
        endpointInterface = "ru.rosreestr.ws.RosreestrService")
public class RosreestrServiceImpl implements RosreestrService {

    @Autowired
    private RosreestrServiceProcessor processor;


    @Override
    public GetInformationResponse getInformation(GetInformationRequest request) {
        return processor.getInformation(request);
    }
}
