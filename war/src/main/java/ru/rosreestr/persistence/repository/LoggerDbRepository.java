package ru.rosreestr.persistence.repository;

import java.util.Date;

/**
 * Используется для вызова процедур логирования
 */
public interface LoggerDbRepository {

    /**
     * Логирование ошибкок в БД
     *
     * @param sdTime
     * @param fdTime
     * @param dbId Ссылкана District_servers.id_d bили 0 - если не известно
     * @param serviceId Идентификатор сервиса 1-сервис уведомления
     * @param logType Тип лога 1-JAVA, 2-PLS
     * @param logLevel 1 - Информационное, 2 - Предупреждения, 3 - Ошибка, 4 - Критичекская ошибка
     * @param step Шаг операции лога
     * @param text Текстовая информация лога
     * @param textError Стек ошибки
     */
    void log(Date sdTime, Date fdTime, Long dbId, Integer serviceId, Integer logType, Integer logLevel, Integer step, String text, String textError);


    /**
     * Логирование ошибкок в БД
     *
     * @param sdTime
     * @param fdTime
     * @param dbId Ссылкана District_servers.id_d bили 0 - если не известно
     * @param serviceId Идентификатор сервиса 1-сервис уведомления
     * @param logType Тип лога 1-JAVA, 2-PLS
     * @param logLevel 1 - Информационное, 2 - Предупреждения, 3 - Ошибка, 4 - Критичекская ошибка
     * @param step Шаг операциилога
     * @param text Текстовая информация лога
     * @param textError Стек ошибки
     * @param messageId Иденификатор сообщения
     */
    void log(Date sdTime, Date fdTime, Long dbId, Integer serviceId, Integer logType, Integer logLevel, Integer step, String text, String textError, String messageId);

    /**
     * Логирование входящих и исходящих SOAP сообщений
     *
     * @param serviceId Идентификатор сервиса
     * @param message Сообщение
     * @param outbound if outbound = 1 then insert into ws_xml_out
     */
    void logXml(Integer serviceId, String message, Integer outbound);

}
