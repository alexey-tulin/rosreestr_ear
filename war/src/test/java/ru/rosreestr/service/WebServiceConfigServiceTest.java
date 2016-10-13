package ru.rosreestr.service;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.rosreestr.config.AppConfig;
import ru.rosreestr.config.PersistentConfig;
import ru.rosreestr.persistence.model.WebServiceParam;
import ru.rosreestr.persistence.repository.WebServiceConfigRepository;

/**
 * Created by KatrinaBosh on 13.10.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, PersistentConfig.class})
public class WebServiceConfigServiceTest extends TestCase {

    @Autowired
    private WebServiceConfigRepository wsParamsService;

    public void testFindByServiceIdAndName() throws Exception {

    }

    @Test
    public void testFindOneByServiceIdAndName() throws Exception {
        System.out.println("----" + wsParamsService.findByServiceIdAndNameParam(89, WebServiceParam.CODE));
    }
}