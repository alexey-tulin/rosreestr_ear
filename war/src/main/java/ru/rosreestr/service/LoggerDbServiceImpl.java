package ru.rosreestr.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rosreestr.persistence.repository.LoggerDbRepository;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Зеализация сервиса логирования в БД входящих/исходящих SOAP сообщений и ошибок
 */
@Service
public class LoggerDbServiceImpl implements LoggerDbService {

    @Resource
    private LoggerDbRepository loggerDbRepository;

    @Override
    @Transactional
    public void log(Date sdTime, Date fdTime, Long dbId, Integer serviceId, Integer logType, Integer logLevel, Integer step, String text, String textError) {
        loggerDbRepository.log(sdTime, fdTime, dbId, serviceId, logType, logLevel, step, text, textError);
    }

    @Override
    @Transactional
    public void log(Date sdTime, Date fdTime, Long dbId, Integer serviceId, Integer logType, Integer logLevel, Integer step, String text, String textError, String messageId) {
        loggerDbRepository.log(sdTime, fdTime, dbId, serviceId, logType, logLevel, step, text, textError, messageId);
    }

    @Override
    @Transactional
    public void logXml(Integer serviceId, String message, Integer outbound) {
        loggerDbRepository.logXml(serviceId, message, outbound);
    }

    @Override
    @Transactional
    public void delete(String nativeQueryText) {
        loggerDbRepository.delete(nativeQueryText);
    }

}
