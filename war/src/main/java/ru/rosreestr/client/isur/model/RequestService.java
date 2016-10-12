
package ru.rosreestr.client.isur.model;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for RequestService complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RequestService">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RegNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RegDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="ServiceNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ServiceTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ServicePrice" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="PrepareTargetDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="OutputTargetDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="Copies" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Responsible" type="{http://asguf.mos.ru/rkis_gu/coordinate/v5/}Person" minOccurs="0"/>
 *         &lt;element name="Department" type="{http://asguf.mos.ru/rkis_gu/coordinate/v5/}Department" minOccurs="0"/>
 *         &lt;element name="Documents" type="{http://asguf.mos.ru/rkis_gu/coordinate/v5/}ArrayOfServiceDocument" minOccurs="0"/>
 *         &lt;element name="DeclineReasonCodes" type="{http://asguf.mos.ru/rkis_gu/coordinate/v5/}ArrayOfString" minOccurs="0"/>
 *         &lt;element name="CreatedByDepartment" type="{http://asguf.mos.ru/rkis_gu/coordinate/v5/}Department" minOccurs="0"/>
 *         &lt;element name="PrepareFactDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="OutputFactDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestService", propOrder = {
    "regNum",
    "regDate",
    "serviceNumber",
    "serviceTypeCode",
    "servicePrice",
    "prepareTargetDate",
    "outputTargetDate",
    "copies",
    "responsible",
    "department",
    "documents",
    "declineReasonCodes",
    "createdByDepartment",
    "prepareFactDate",
    "outputFactDate"
})
public class RequestService {

    @XmlElement(name = "RegNum")
    protected String regNum;
    @XmlElement(name = "RegDate", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar regDate;
    @XmlElement(name = "ServiceNumber")
    protected String serviceNumber;
    @XmlElement(name = "ServiceTypeCode")
    protected String serviceTypeCode;
    @XmlElement(name = "ServicePrice", required = true, nillable = true)
    protected BigDecimal servicePrice;
    @XmlElement(name = "PrepareTargetDate", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar prepareTargetDate;
    @XmlElement(name = "OutputTargetDate", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar outputTargetDate;
    @XmlElement(name = "Copies", required = true, type = Integer.class, nillable = true)
    protected Integer copies;
    @XmlElement(name = "Responsible")
    protected Person responsible;
    @XmlElement(name = "Department")
    protected Department department;
    @XmlElement(name = "Documents")
    protected ArrayOfServiceDocument documents;
    @XmlElement(name = "DeclineReasonCodes")
    protected ArrayOfString declineReasonCodes;
    @XmlElement(name = "CreatedByDepartment")
    protected Department createdByDepartment;
    @XmlElement(name = "PrepareFactDate", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar prepareFactDate;
    @XmlElement(name = "OutputFactDate", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar outputFactDate;

    /**
     * Gets the value of the regNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegNum() {
        return regNum;
    }

    /**
     * Sets the value of the regNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegNum(String value) {
        this.regNum = value;
    }

    /**
     * Gets the value of the regDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRegDate() {
        return regDate;
    }

    /**
     * Sets the value of the regDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRegDate(XMLGregorianCalendar value) {
        this.regDate = value;
    }

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
     * Gets the value of the serviceTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceTypeCode() {
        return serviceTypeCode;
    }

    /**
     * Sets the value of the serviceTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceTypeCode(String value) {
        this.serviceTypeCode = value;
    }

    /**
     * Gets the value of the servicePrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getServicePrice() {
        return servicePrice;
    }

    /**
     * Sets the value of the servicePrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setServicePrice(BigDecimal value) {
        this.servicePrice = value;
    }

    /**
     * Gets the value of the prepareTargetDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPrepareTargetDate() {
        return prepareTargetDate;
    }

    /**
     * Sets the value of the prepareTargetDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPrepareTargetDate(XMLGregorianCalendar value) {
        this.prepareTargetDate = value;
    }

    /**
     * Gets the value of the outputTargetDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOutputTargetDate() {
        return outputTargetDate;
    }

    /**
     * Sets the value of the outputTargetDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOutputTargetDate(XMLGregorianCalendar value) {
        this.outputTargetDate = value;
    }

    /**
     * Gets the value of the copies property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCopies() {
        return copies;
    }

    /**
     * Sets the value of the copies property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCopies(Integer value) {
        this.copies = value;
    }

    /**
     * Gets the value of the responsible property.
     * 
     * @return
     *     possible object is
     *     {@link Person }
     *     
     */
    public Person getResponsible() {
        return responsible;
    }

    /**
     * Sets the value of the responsible property.
     * 
     * @param value
     *     allowed object is
     *     {@link Person }
     *     
     */
    public void setResponsible(Person value) {
        this.responsible = value;
    }

    /**
     * Gets the value of the department property.
     * 
     * @return
     *     possible object is
     *     {@link Department }
     *     
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * Sets the value of the department property.
     * 
     * @param value
     *     allowed object is
     *     {@link Department }
     *     
     */
    public void setDepartment(Department value) {
        this.department = value;
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
     * Gets the value of the declineReasonCodes property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getDeclineReasonCodes() {
        return declineReasonCodes;
    }

    /**
     * Sets the value of the declineReasonCodes property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setDeclineReasonCodes(ArrayOfString value) {
        this.declineReasonCodes = value;
    }

    /**
     * Gets the value of the createdByDepartment property.
     * 
     * @return
     *     possible object is
     *     {@link Department }
     *     
     */
    public Department getCreatedByDepartment() {
        return createdByDepartment;
    }

    /**
     * Sets the value of the createdByDepartment property.
     * 
     * @param value
     *     allowed object is
     *     {@link Department }
     *     
     */
    public void setCreatedByDepartment(Department value) {
        this.createdByDepartment = value;
    }

    /**
     * Gets the value of the prepareFactDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPrepareFactDate() {
        return prepareFactDate;
    }

    /**
     * Sets the value of the prepareFactDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPrepareFactDate(XMLGregorianCalendar value) {
        this.prepareFactDate = value;
    }

    /**
     * Gets the value of the outputFactDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOutputFactDate() {
        return outputFactDate;
    }

    /**
     * Sets the value of the outputFactDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOutputFactDate(XMLGregorianCalendar value) {
        this.outputFactDate = value;
    }

}
