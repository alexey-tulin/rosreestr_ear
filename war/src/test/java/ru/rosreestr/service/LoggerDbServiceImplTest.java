package ru.rosreestr.service;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.rosreestr.config.AppConfig;
import ru.rosreestr.config.PersistentConfig;
import ru.rosreestr.persistence.model.LogLevel;
import ru.rosreestr.persistence.model.LogType;
import ru.rosreestr.persistence.model.WebService;
import ru.rosreestr.persistence.repository.WebServiceRepository;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by KatrinaBosh on 08.10.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, PersistentConfig.class})
public class LoggerDbServiceImplTest extends TestCase {

    private static final String SERVICE_NAME = "JUnit Test";

    @Resource
    private LoggerDbService loggerDbService;

    @Resource
    private WebServiceRepository wsRepository;

    private WebService webService;

    @Before
    public void init() {
        webService = new WebService();
        webService.setName(SERVICE_NAME);
        webService = wsRepository.saveAndFlush(webService);
    }

    @After
    public void destroy() {
        // TODO дописать очищение БД от тестовых сущностей
        //em.createQuery("delete ") if DB_ID = -1
        //wsRepository.delete(webService);
    }

    @Test
    public void testLog() throws Exception {

        loggerDbService.log(new Date(), new Date(), -1L, webService.getServiceId(), LogType.JAVA, LogLevel.INFO, 1, "test", "textError", "messageId");

        loggerDbService.log(new Date(), new Date(), -1L, webService.getServiceId(), LogType.JAVA, LogLevel.INFO, 1, "test", "textError");
    }

    @Test
    public void testLogXml() throws Exception {
        loggerDbService.logXml(webService.getServiceId(), "message", 0);
    }
}