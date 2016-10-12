package ru.rosreestr.persistence.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by KatrinaBosh on 28.09.2016.
 */
@Entity
@Table(name = "WS_SERVICES")
public class WebService implements Serializable {

    private static final long serialVersionUID = 101L;

    /**
     * Идентификатор сервиса
     */
    @Id
    @GeneratedValue
    @Column(name = "SERVICE_ID")
    private Integer serviceId;

    /**
     * Наименование Сервиса
     */
    @Column(name = "NAME")
    private String name;

    @OneToMany
    @JoinColumn(name = "SERVICE_ID", referencedColumnName = "SERVICE_ID")
    private List<WebServiceConfig> params;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public String toString() {
        return "WebService{" +
                "name='" + name + '\'' +
                ", serviceId=" + serviceId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WebService that = (WebService) o;

        if (serviceId != null ? !serviceId.equals(that.serviceId) : that.serviceId != null) return false;
        return !(name != null ? !name.equals(that.name) : that.name != null);

    }

    @Override
    public int hashCode() {
        int result = serviceId != null ? serviceId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
