package ru.rosreestr.ws;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import ru.rosreestr.client.isur.model.CoordinateStatusData;
import ru.rosreestr.client.isur.model.ErrorMessage;

/**
 * Created by Tatiana Chukina on 01.10.2016 16:43.
 */
@Service("serviceWSProcessor")
public class IsurServiceProcessor {
    private static final Logger LOG = Logger.getLogger(IsurServiceProcessor.class);

    public void acknowledgement(ErrorMessage parameters) {
        LOG.info("ru.rosreestr.endpoints.ServiceWSProcessor.acknowledgement");
    }

    public void setFilesAndStatus(CoordinateStatusData statusMessage) {
        LOG.info("ru.rosreestr.endpoints.ServiceWSProcessor.setFilesAndStatus");
    }
}
