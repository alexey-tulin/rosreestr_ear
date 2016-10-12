package ru.rosreestr.persistence.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;

/**
 * Created by KatrinaBosh on 08.10.2016.
 */
@Repository
public class LoggerDbRepositoryImpl implements LoggerDbRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void log(Date sdTime, Date fdTime, Long dbId, Integer serviceId, Integer logType, Integer logLevel, Integer step, String text, String textError) {
        Query query = em.createNativeQuery("declare retvar number; begin retvar \\:= ws_logger.log(:sdTime, :fdTime, :dbId, :serviceId, :logType, :logLevel, :step, :text, :textError); end;")
                .setParameter("sdTime", sdTime)
                .setParameter("fdTime", fdTime)
                .setParameter("dbId", dbId)
                .setParameter("serviceId", serviceId)
                .setParameter("logType", logType)
                .setParameter("logLevel", logLevel)
                .setParameter("step", step)
                .setParameter("text", text)
                .setParameter("textError", textError);
        query.executeUpdate();
    }

    @Override
    public void log(Date sdTime, Date fdTime, Long dbId, Integer serviceId, Integer logType, Integer logLevel, Integer step, String text, String textError, String messageId) {
        Query query = em.createNativeQuery("{call ws_logger.log(:sdTime, :fdTime, :dbId, :serviceId, :logType, :logLevel, :step, :text, :textError, :messageId)}")
                .setParameter("sdTime", sdTime)
                .setParameter("fdTime", fdTime)
                .setParameter("dbId", dbId)
                .setParameter("serviceId", serviceId)
                .setParameter("logType", logType)
                .setParameter("logLevel", logLevel)
                .setParameter("step", step)
                .setParameter("text", text)
                .setParameter("textError", textError)
                .setParameter("messageId", messageId);
        query.executeUpdate();
    }

    @Override
    public void logXml(Integer serviceId, String message, Integer outbound) {
        Query query = em.createNativeQuery("declare retvar number; begin retvar \\:= ws_logger.log_xml(:serviceId, :message, :outbound); end;")
                .setParameter("serviceId", serviceId)
                .setParameter("message", message)
                .setParameter("outbound", outbound);
        query.executeUpdate();
    }
}
