package ru.rosreestr.service;

import org.springframework.stereotype.Service;
import ru.rosreestr.persistence.model.WebServiceConfig;
import ru.rosreestr.persistence.repository.WebServiceConfigRepository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by KatrinaBosh on 09.10.2016.
 */
@Service
public class WebServiceConfigServiceImpl implements WebServiceConfigService {

    @Resource
    private WebServiceConfigRepository configRepository;


    @Override
    public List<WebServiceConfig> findByServiceIdAndName(Integer serviceId, String name) {
        return configRepository.findByServiceIdAndName(serviceId, name);
    }
}
