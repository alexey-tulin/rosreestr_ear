package ru.rosreestr.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rosreestr.persistence.repository.LoggerDbRepository;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by KatrinaBosh on 08.10.2016.
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
}
