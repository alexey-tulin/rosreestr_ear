package ru.rosreestr.persistence.model;

import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Конфигурация веб-сервиса.
 * Каждая запись хранит 1 параметр веб-сервиса.
 * Значение может быть строковым, целочисленным или дата и время.
 * Логические параметра записываются в строковое поле в виде строки true/false
 */
@Entity
@Table(name = "WS_CONFIG")
public class WebServiceConfig implements Serializable {

    private static final long serialVersionUID = 100L;

    @EmbeddedId
    private WebServiceConfigPk id;

    /**
     * Наименование параметров
     */
    @Column(name = "name_param", insertable = false, updatable = false)
    private String nameParam;

    /**
     * Ссылка на веб-сервис, которому принадлежит данный параметр
     */
    @Column(name = "service_id", insertable = false, updatable = false)
    private Integer serviceId;

    /**
     * Строковое значение параметра
     */
    @Column(name = "val_string")
    private String stringValue;

    /**
     * Целочисленное значение параметра
     */
    @Column(name = "val_num")
    private Integer integerValue;

    /**
     * Значение параметра в виде даты и времени
     */
    @Column(name = "val_date")
    private Date dateValue;

    /**
     * Описание параметра
     */
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

    /**
     * Позволяет определить тип параметра.
     * Тип вычисляется до первого выполнения условия (если условие выполнено, следующая проверка не производится).
     * Если заполнено строковое поле, значит параметр строкового типа (STRING).
     * Если заполнено целочисленное поле, значит параметр целоцисленного типа (INTEGER).
     * Если заполнено поле параметр типа 'дата и время', значит тараметр имеет тип 'дата и время' (DATE).
     * Если нет ни одного соответствия, возращается null.
     * @return STRING/INTEGER/DATE/null
     */
    @Transient
    public WebServiceParamType readType() {

        WebServiceParamType type;
        if(!StringUtils.isEmpty(this.getStringValue())) {
            type = WebServiceParamType.STRING;
        } else if (this.getIntegerValue() != null) {
            type = WebServiceParamType.INTEGER;
        } else if (this.getDateValue() != null) {
            type = WebServiceParamType.DATE;
        } else {
            type = null;
        }
        return type;
    }

    @Override
    public String toString() {
        return "WebServiceConfig{" +
                "dateValue=" + dateValue +
                ", id=" + id +
                ", nameParam='" + nameParam + '\'' +
                ", serviceId=" + serviceId +
                ", stringValue='" + stringValue + '\'' +
                ", integerValue=" + integerValue +
                ", description='" + description + '\'' +
                '}';
    }
}
