package ru.rosreestr.service;

import ru.rosreestr.exception.DuplicateWebServiceParamException;
import ru.rosreestr.exception.NotFoundWebServiceParamException;
import ru.rosreestr.exception.WebServiceParamTypeException;
import ru.rosreestr.persistence.model.WebServiceConfig;
import ru.rosreestr.persistence.model.WebServiceParamType;

import java.util.List;

/**
 * Created by KatrinaBosh on 09.10.2016.
 */
public interface WebServiceConfigService {

    List<WebServiceConfig> findByServiceIdAndName(Integer serviceId, String name);

    WebServiceConfig findOneByServiceIdAndName(Integer serviceId, String name) throws NotFoundWebServiceParamException, DuplicateWebServiceParamException;

    WebServiceConfig findOneByServiceIdAndName(Integer serviceId, String name, WebServiceParamType typeParam) throws NotFoundWebServiceParamException, DuplicateWebServiceParamException, WebServiceParamTypeException;
}
