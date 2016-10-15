package ru.rosreestr.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rosreestr.persistence.model.WebServiceConfig;
import ru.rosreestr.persistence.model.WebServiceConfigPk;

import java.util.List;

/**
 * Репозиторий для работы с данными сущности WebServiceConfig
 */
public interface WebServiceConfigRepository extends JpaRepository<WebServiceConfig, WebServiceConfigPk> {

    List<WebServiceConfig> findByServiceIdAndNameParam(Integer serviceId, String nameParam);

}
