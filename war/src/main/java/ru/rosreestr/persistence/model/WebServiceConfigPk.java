package ru.rosreestr.persistence.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by KatrinaBosh on 08.10.2016.
 */
@Embeddable
public class WebServiceConfigPk implements Serializable {

    private static final long serialVersionUID = 102L;

    @Column(name = "name_param")
    private String nameParam;

    @Column(name = "service_id")
    private Integer serviceId;

    public String getNameParam() {
        return nameParam;
    }

    public void setNameParam(String nameParam) {
        this.nameParam = nameParam;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }
}
