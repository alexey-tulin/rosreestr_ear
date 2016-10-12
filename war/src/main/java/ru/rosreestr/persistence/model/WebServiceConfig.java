package ru.rosreestr.persistence.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by KatrinaBosh on 08.10.2016.
 */
@Entity
@Table(name = "WS_CONFIG")
public class WebServiceConfig implements Serializable {

    private static final long serialVersionUID = 100L;

    @EmbeddedId
    private WebServiceConfigPk id;

    @Column(name = "name_param", insertable = false, updatable = false)
    private String name;

    @Column(name = "service_id", insertable = false, updatable = false)
    private Integer serviceId;

    @Column(name = "val_string")
    private String stringValue;

    @Column(name = "val_num")
    private Integer integerValue;

    @Column(name = "val_date")
    private Date dateValue;

    @Column(name = "description")
    private String description;

    public WebServiceConfigPk getId() {
        return id;
    }

    public void setId(WebServiceConfigPk id) {
        this.id = id;
    }

    public Date getDateValue() {
        return dateValue;
    }

    public void setDateValue(Date dateValue) {
        this.dateValue = dateValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIntegerValue() {
        return integerValue;
    }

    public void setIntegerValue(Integer integerValue) {
        this.integerValue = integerValue;
    }

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

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    @Transient
    public boolean getBooleanValue() {
        return Boolean.parseBoolean(stringValue);
    }

    public void setBooleanValue(Boolean booleanValue) {
        this.stringValue = booleanValue != null ? booleanValue.toString() : null;
    }
}
