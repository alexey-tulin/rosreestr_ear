
package ru.rosreestr.client.isur.model;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for CoordinateTaskData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CoordinateTaskData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Task" type="{http://asguf.mos.ru/rkis_gu/coordinate/v5/}RequestTask" minOccurs="0"/>
 *         &lt;element name="Data" type="{http://asguf.mos.ru/rkis_gu/coordinate/v5/}DocumentsRequestData" minOccurs="0"/>
 *         &lt;element name="Signature" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CoordinateTaskData", propOrder = {
    "task",
    "data",
    "signature"
})
@XmlSeeAlso({ru.rosreestr.client.isur.model.base64.ServiceProperties.class})
public class CoordinateTaskData {

    @XmlElement(name = "Task")
    protected RequestTask task;
    @XmlElement(name = "Data")
    protected DocumentsRequestData data;
    @XmlElement(name = "Signature")
    protected Object signature;

    /**
     * Gets the value of the task property.
     * 
     * @return
     *     possible object is
     *     {@link RequestTask }
     *     
     */
    public RequestTask getTask() {
        return task;
    }

    /**
     * Sets the value of the task property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestTask }
     *     
     */
    public void setTask(RequestTask value) {
        this.task = value;
    }

    /**
     * Gets the value of the data property.
     * 
     * @return
     *     possible object is
     *     {@link DocumentsRequestData }
     *     
     */
    public DocumentsRequestData getData() {
        return data;
    }

    /**
     * Sets the value of the data property.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentsRequestData }
     *     
     */
    public void setData(DocumentsRequestData value) {
        this.data = value;
    }

    /**
     * Gets the value of the signature property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getSignature() {
        return signature;
    }

    /**
     * Sets the value of the signature property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setSignature(Object value) {
        this.signature = value;
    }

}
