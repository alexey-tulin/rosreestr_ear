package ru.rosreestr.service;

import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.rosreestr.config.AppConfig;
import ru.rosreestr.config.PersistentConfig;

/**
 * Created by KatrinaBosh on 13.10.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, PersistentConfig.class})
public class WebServiceServiceTest extends TestCase {

    @Autowired
    private WebServiceConfigService wsParamsService;

    public void testDelete() throws Exception {
// todo
    }

    public void testSave() throws Exception {

    }

    public void testFindByParam() throws Exception {

    }

    public void testFindByCode() throws Exception {

    }
}