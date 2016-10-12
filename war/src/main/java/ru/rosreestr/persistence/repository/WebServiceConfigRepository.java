package ru.rosreestr.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rosreestr.persistence.model.WebServiceConfig;
import ru.rosreestr.persistence.model.WebServiceConfigPk;

import java.util.List;

/**
 * Created by KatrinaBosh on 09.10.2016.
 */
public interface WebServiceConfigRepository extends JpaRepository<WebServiceConfig, WebServiceConfigPk> {

    List<WebServiceConfig> findByServiceIdAndName(Integer serviceId, String name);
}
