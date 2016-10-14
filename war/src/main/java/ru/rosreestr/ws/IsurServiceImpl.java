package ru.rosreestr.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.rosreestr.client.isur.model.CoordinateStatusData;
import ru.rosreestr.client.isur.model.ErrorMessage;

import javax.jws.HandlerChain;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Created by Tatiana Chukina on 01.10.2016 15:35.
 * <p/>
 * Web service for external systems
 */
@Service("isurtestws")
@WebService(serviceName = "isurtestws", targetNamespace = "http://asguf.mos.ru/rkis_gu/coordinate/v5/",
        endpointInterface = "ru.rosreestr.ws.IsurService")
@HandlerChain(file="isur-handler-chain.xml")
public class IsurServiceImpl implements IsurService {

    @Autowired
    @Qualifier("serviceWSProcessor")
    private IsurServiceProcessor processor;

    public void setFilesAndStatus(@WebParam(name = "StatusMessage", targetNamespace = "http://asguf.mos.ru/rkis_gu/coordinate/v5/", partName = "StatusMessage")
                                  CoordinateStatusData statusMessage) {
        processor.setFilesAndStatus(statusMessage);
    }

    public void acknowledgement(@WebParam(name = "ErrorMessage", targetNamespace = "http://asguf.mos.ru/rkis_gu/coordinate/v5/", partName = "parameters")
                                ErrorMessage parameters) {
        processor.acknowledgement(parameters);
    }
}
