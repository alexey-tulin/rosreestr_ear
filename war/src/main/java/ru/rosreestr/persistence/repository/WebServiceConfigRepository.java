package ru.rosreestr.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import ru.rosreestr.persistence.model.WebServiceConfig;
import ru.rosreestr.persistence.model.WebServiceConfigPk;

import java.util.List;

/**
 * Created by KatrinaBosh on 09.10.2016.
 */
public interface WebServiceConfigRepository extends JpaRepository<WebServiceConfig, WebServiceConfigPk> {

    //@Query(value = "select * from WS_CONFIG  where service_id = ?1 and name_param = ?2", nativeQuery = true)
    List<WebServiceConfig> findByServiceIdAndNameParam(@Param("serviceId") Integer serviceId, @Param("nameParam") String nameParam);

}
