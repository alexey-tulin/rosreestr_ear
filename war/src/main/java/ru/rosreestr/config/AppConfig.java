package ru.rosreestr.config;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created by KatrinaBosh on 28.09.2016.
 */
@Configuration
@ComponentScan("ru.rosreestr")
@PropertySource("classpath:application.properties")
@ImportResource(value = {"classpath:applicationContext.xml"})
public class AppConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }


}
