package ru.rosreestr.service;

import ru.rosreestr.exception.DuplicateWebServiceException;
import ru.rosreestr.exception.NotFoundWebServiceException;
import ru.rosreestr.persistence.model.WebService;
import ru.rosreestr.persistence.model.WebServiceCode;

import java.util.Date;
import java.util.List;

/**
 * Сервис для работы с данными сущности WebService
 */
public interface WebServiceService {

    /**
     * Удалить запись по ID
     *
     * @param id
     */
    void delete(Long id);

    /**
     * Сохранить объект
     *
     * @param webService объект для сохранения
     * @return сохранённый объект
     */
    WebService save(WebService webService);

    /**
     * Найти веб-сервисы, имеющие параметр
     *
     * @param paramName имя параметра
     * @param paramValue значение параметра
     * @return список веб-сервисов
     */
    List<WebService> findByParam(String paramName, String paramValue);

    List<WebService> findByParam(String paramName, Integer paramValue);

    List<WebService> findByParam(String paramName, Date paramValue);

    /**
     * Найти веб-сервис по коду
     *
     * @param code код веб-сервиса
     * @return найденный веб-сервис
     * @throws NotFoundWebServiceException
     * @throws DuplicateWebServiceException
     */
    WebService findByCode(WebServiceCode code) throws NotFoundWebServiceException, DuplicateWebServiceException;
}
