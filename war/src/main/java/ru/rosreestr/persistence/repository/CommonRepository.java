package ru.rosreestr.persistence.repository;

import java.math.BigDecimal;

/**
 * Created by Tatiana Chukina on 10.10.2016 2:08.
 * <p/>
 */
public interface CommonRepository {
    BigDecimal getNextMessageNum();
}
