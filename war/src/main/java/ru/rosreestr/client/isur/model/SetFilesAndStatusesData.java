
package ru.rosreestr.client.isur.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SetFilesAndStatusesData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SetFilesAndStatusesData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ServiceNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RequestId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Statuses" type="{http://asguf.mos.ru/rkis_gu/coordinate/v5/}ArrayOfRequestStatus" minOccurs="0"/>
 *         &lt;element name="Result" type="{http://asguf.mos.ru/rkis_gu/coordinate/v5/}RequestResult" minOccurs="0"/>
 *         &lt;element name="Documents" type="{http://asguf.mos.ru/rkis_gu/coordinate/v5/}ArrayOfServiceDocument" minOccurs="0"/>
 *         &lt;element name="Contacts" type="{http://asguf.mos.ru/rkis_gu/coordinate/v5/}ArrayOfRequestContact" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SetFilesAndStatusesData", propOrder = {
    "serviceNumber",
    "requestId",
    "statuses",
    "result",
    "documents",
    "contacts"
})
public class SetFilesAndStatusesData {

    @XmlElement(name = "ServiceNumber")
    protected String serviceNumber;
    @XmlElement(name = "RequestId")
    protected String requestId;
    @XmlElement(name = "Statuses")
    protected ArrayOfRequestStatus statuses;
    @XmlElement(name = "Result")
    protected RequestResult result;
    @XmlElement(name = "Documents")
    protected ArrayOfServiceDocument documents;
    @XmlElement(name = "Contacts")
    protected ArrayOfRequestContact contacts;

    /**
     * Gets the value of the serviceNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceNumber() {
        return serviceNumber;
    }

    /**
     * Sets the value of the serviceNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceNumber(String value) {
        this.serviceNumber = value;
    }

    /**
     * Gets the value of the requestId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * Sets the value of the requestId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestId(String value) {
        this.requestId = value;
    }

    /**
     * Gets the value of the statuses property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRequestStatus }
     *     
     */
    public ArrayOfRequestStatus getStatuses() {
        return statuses;
    }

    /**
     * Sets the value of the statuses property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRequestStatus }
     *     
     */
    public void setStatuses(ArrayOfRequestStatus value) {
        this.statuses = value;
    }

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link RequestResult }
     *     
     */
    public RequestResult getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestResult }
     *     
     */
    public void setResult(RequestResult value) {
        this.result = value;
    }

    /**
     * Gets the value of the documents property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfServiceDocument }
     *     
     */
    public ArrayOfServiceDocument getDocuments() {
        return documents;
    }

    /**
     * Sets the value of the documents property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfServiceDocument }
     *     
     */
    public void setDocuments(ArrayOfServiceDocument value) {
        this.documents = value;
    }

    /**
     * Gets the value of the contacts property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRequestContact }
     *     
     */
    public ArrayOfRequestContact getContacts() {
        return contacts;
    }

    /**
     * Sets the value of the contacts property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRequestContact }
     *     
     */
    public void setContacts(ArrayOfRequestContact value) {
        this.contacts = value;
    }

}
