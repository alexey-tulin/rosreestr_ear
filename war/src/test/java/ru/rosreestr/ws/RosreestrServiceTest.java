package ru.rosreestr.ws;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import ru.rosreestr.client.isur.processor.ServiceImpl;
import ru.rosreestr.config.AppConfig;
import ru.rosreestr.ws.model.GetInformationRequest;
import ru.rosreestr.ws.model.GetInformationResponse;

import static org.junit.Assert.*;

/**
 * Created by Tatiana Chukina on 10.10.2016 2:48.
 * <p/>
 * Test to {@link RosreestrService}
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class RosreestrServiceTest {
    private static final Logger LOG = Logger.getLogger(ServiceImpl.class);

    @Qualifier("rosreestrservice")
    @Autowired
    private RosreestrServiceImpl rosreestrService;

    @Test
    public void testGetInformation() throws Exception {
        GetInformationRequest request = new GetInformationRequest();
        request.setRegion("77");
        request.setCadastralnumber("77:01:0004028:4359");
        request.setTypeobject("002001002000");
        request.setTyperoom("IsNondomestic");
        GetInformationResponse information = rosreestrService.getInformation(request);
        String serviceNumber = information.getServiceNumber();
        LOG.info(serviceNumber);
        Assert.notNull(serviceNumber);
    }
}