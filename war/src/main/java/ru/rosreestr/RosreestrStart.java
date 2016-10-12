package ru.rosreestr;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.rosreestr.client.isur.processor.ServiceImpl;
import ru.rosreestr.config.AppConfig;
import ru.rosreestr.config.AppProperties;
import ru.rosreestr.config.PersistentConfig;

/**
 * Created by KatrinaBosh on 28.09.2016.
 */
public class RosreestrStart {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class, PersistentConfig.class);//, PersistentConfig.class);

        AppProperties properties = context.getBean(AppProperties.class);
        System.out.println(properties.getIsur());
        ServiceImpl processor = context.getBean(ServiceImpl.class);
//        processor.sendTask();

        System.out.println();

    }

}
