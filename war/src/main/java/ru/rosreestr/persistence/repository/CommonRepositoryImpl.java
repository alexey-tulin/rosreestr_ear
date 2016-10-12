package ru.rosreestr.persistence.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;

/**
 * Created by Tatiana Chukina on 10.10.2016 2:10.
 */
@Repository
public class CommonRepositoryImpl implements CommonRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public BigDecimal getNextMessageNum() {
        Query query = em.createNativeQuery("select MESSAGE_NUM_SEQUENCE.NEXTVAL from dual");
        return (BigDecimal)query.getSingleResult();
    }
}
