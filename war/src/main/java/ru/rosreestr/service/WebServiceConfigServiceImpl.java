package ru.rosreestr.service;

import org.springframework.stereotype.Service;
import ru.rosreestr.exception.DuplicateWebServiceParamException;
import ru.rosreestr.exception.NotFoundWebServiceParamException;
import ru.rosreestr.exception.WebServiceParamTypeException;
import ru.rosreestr.persistence.model.WebServiceConfig;
import ru.rosreestr.persistence.model.WebServiceParam;
import ru.rosreestr.persistence.model.WebServiceParamType;
import ru.rosreestr.persistence.repository.WebServiceConfigRepository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Реализация сервиса для работы с данными сущности WebServiceConfig
 */
@Service
public class WebServiceConfigServiceImpl implements WebServiceConfigService {

    @Resource
    private WebServiceConfigRepository configRepository;


    @Override
    public List<WebServiceConfig> findByServiceIdAndName(Integer serviceId, String name) {
        return configRepository.findByServiceIdAndNameParam(serviceId, name);
    }

    @Override
    public WebServiceConfig findOneByServiceIdAndName(Integer serviceId, String name) throws NotFoundWebServiceParamException, DuplicateWebServiceParamException {
        List<WebServiceConfig> params = configRepository.findByServiceIdAndNameParam(serviceId, name);
        if (params.isEmpty()) {
            throw new NotFoundWebServiceParamException(name);
        } else if (params.size() > 1) {
            throw new DuplicateWebServiceParamException(name);
        }
        return params.get(0);
    }

    @Override
    public WebServiceConfig findOneByServiceIdAndName(Integer serviceId, String name, WebServiceParamType typeParam) throws NotFoundWebServiceParamException, DuplicateWebServiceParamException, WebServiceParamTypeException {
        WebServiceConfig param = findOneByServiceIdAndName(serviceId, name);

        if (typeParam != null && !typeParam.equals(param.readType())) {
            throw new WebServiceParamTypeException(param, typeParam);
        }
        return param;
    }

}
