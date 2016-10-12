package ru.rosreestr.service;

import ru.rosreestr.persistence.model.WebService;

import java.util.Date;
import java.util.List;

/**
 * Created by KatrinaBosh on 08.10.2016.
 */
public interface WebServiceService {

    void delete(Long id);

    WebService save(WebService webService);

    List<WebService> findByParam(String paramName, String paramValue);

    List<WebService> findByParam(String paramName, Integer paramValue);

    List<WebService> findByParam(String paramName, Date paramValue);
}
