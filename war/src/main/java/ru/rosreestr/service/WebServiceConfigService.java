package ru.rosreestr.service;

import ru.rosreestr.persistence.model.WebServiceConfig;

import java.util.List;

/**
 * Created by KatrinaBosh on 09.10.2016.
 */
public interface WebServiceConfigService {

    List<WebServiceConfig> findByServiceIdAndName(Integer serviceId, String name);
}
