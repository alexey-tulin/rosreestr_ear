package ru.rosreestr.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.rosreestr.client.isur.model.CoordinateStatusData;
import ru.rosreestr.client.isur.model.ErrorMessage;
import ru.rosreestr.client.isur.model.Headers;

import javax.jws.WebService;

/**
 * Created by Tatiana Chukina on 01.10.2016 15:35.
 * <p/>
 * Web service for external systems
 */
@Service("isurtestws")
@WebService(serviceName = "isurtestws", targetNamespace = "http://asguf.mos.ru/rkis_gu/coordinate/v5/",
        endpointInterface = "ru.rosreestr.ws.IsurService")
public class IsurServiceImpl implements IsurService {

    @Autowired
    @Qualifier("serviceWSProcessor")
    private IsurServiceProcessor processor;

    @Override
    public void setFilesAndStatus(CoordinateStatusData statusMessage, Headers serviceHeader) {
        processor.setFilesAndStatus(statusMessage, serviceHeader);
    }

    @Override
    public void acknowledgement(ErrorMessage parameters, Headers serviceHeader) {
        processor.acknowledgement(parameters, serviceHeader);
    }
}
