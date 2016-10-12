package ru.rosreestr.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.rosreestr.persistence.model.WebService;

import java.util.Date;
import java.util.List;

/**
 * Created by KatrinaBosh on 28.09.2016.
 */
public interface WebServiceRepository extends JpaRepository<WebService, Long> {

    @Query("select ws from WebService ws inner join ws.params p where p.name = :paramName and p.stringValue = :paramValue")
    List<WebService> findByParam(@Param("paramName") String paramName, @Param("paramValue") String paramValue);

    @Query("select ws from WebService ws inner join ws.params p where p.name = :paramName and p.integerValue = :paramValue")
    List<WebService> findByParam(@Param("paramName") String paramName, @Param("paramValue") Integer paramValue);

    @Query("select ws from WebService ws inner join ws.params p where p.name = :paramName and p.dateValue = :paramValue")
    List<WebService> findByParam(@Param("paramName") String paramName, @Param("paramValue") Date paramValue);

}

