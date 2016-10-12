
package ru.rosreestr.client.isur.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for RequestAccount complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RequestAccount">
 *   &lt;complexContent>
 *     &lt;extension base="{http://asguf.mos.ru/rkis_gu/coordinate/v5/}BaseDeclarant">
 *       &lt;sequence>
 *         &lt;element name="FullName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BrandName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Ogrn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OgrnAuthority" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OgrnNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OgrnDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="Inn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InnAuthority" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InnNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InnDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="Kpp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Okpo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OrgFormCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PostalAddress" type="{http://asguf.mos.ru/rkis_gu/coordinate/v5/}Address" minOccurs="0"/>
 *         &lt;element name="FactAddress" type="{http://asguf.mos.ru/rkis_gu/coordinate/v5/}Address" minOccurs="0"/>
 *         &lt;element name="OrgHead" type="{http://asguf.mos.ru/rkis_gu/coordinate/v5/}RequestContact" minOccurs="0"/>
 *         &lt;element name="Okved" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Okfs" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BankName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BankBik" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CorrAccount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SetAccount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Phone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Fax" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EMail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="WebSite" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestAccount", propOrder = {
    "fullName",
    "name",
    "brandName",
    "ogrn",
    "ogrnAuthority",
    "ogrnNum",
    "ogrnDate",
    "inn",
    "innAuthority",
    "innNum",
    "innDate",
    "kpp",
    "okpo",
    "orgFormCode",
    "postalAddress",
    "factAddress",
    "orgHead",
    "okved",
    "okfs",
    "bankName",
    "bankBik",
    "corrAccount",
    "setAccount",
    "phone",
    "fax",
    "eMail",
    "webSite"
})
public class RequestAccount
    extends BaseDeclarant
{

    @XmlElement(name = "FullName")
    protected String fullName;
    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "BrandName")
    protected String brandName;
    @XmlElement(name = "Ogrn")
    protected String ogrn;
    @XmlElement(name = "OgrnAuthority")
    protected String ogrnAuthority;
    @XmlElement(name = "OgrnNum")
    protected String ogrnNum;
    @XmlElement(name = "OgrnDate", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar ogrnDate;
    @XmlElement(name = "Inn")
    protected String inn;
    @XmlElement(name = "InnAuthority")
    protected String innAuthority;
    @XmlElement(name = "InnNum")
    protected String innNum;
    @XmlElement(name = "InnDate", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar innDate;
    @XmlElement(name = "Kpp")
    protected String kpp;
    @XmlElement(name = "Okpo")
    protected String okpo;
    @XmlElement(name = "OrgFormCode")
    protected String orgFormCode;
    @XmlElement(name = "PostalAddress")
    protected Address postalAddress;
    @XmlElement(name = "FactAddress")
    protected Address factAddress;
    @XmlElement(name = "OrgHead")
    protected RequestContact orgHead;
    @XmlElement(name = "Okved")
    protected String okved;
    @XmlElement(name = "Okfs")
    protected String okfs;
    @XmlElement(name = "BankName")
    protected String bankName;
    @XmlElement(name = "BankBik")
    protected String bankBik;
    @XmlElement(name = "CorrAccount")
    protected String corrAccount;
    @XmlElement(name = "SetAccount")
    protected String setAccount;
    @XmlElement(name = "Phone")
    protected String phone;
    @XmlElement(name = "Fax")
    protected String fax;
    @XmlElement(name = "EMail")
    protected String eMail;
    @XmlElement(name = "WebSite")
    protected String webSite;

    /**
     * Gets the value of the fullName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets the value of the fullName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFullName(String value) {
        this.fullName = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the brandName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     * Sets the value of the brandName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrandName(String value) {
        this.brandName = value;
    }

    /**
     * Gets the value of the ogrn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOgrn() {
        return ogrn;
    }

    /**
     * Sets the value of the ogrn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOgrn(String value) {
        this.ogrn = value;
    }

    /**
     * Gets the value of the ogrnAuthority property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOgrnAuthority() {
        return ogrnAuthority;
    }

    /**
     * Sets the value of the ogrnAuthority property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOgrnAuthority(String value) {
        this.ogrnAuthority = value;
    }

    /**
     * Gets the value of the ogrnNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOgrnNum() {
        return ogrnNum;
    }

    /**
     * Sets the value of the ogrnNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOgrnNum(String value) {
        this.ogrnNum = value;
    }

    /**
     * Gets the value of the ogrnDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOgrnDate() {
        return ogrnDate;
    }

    /**
     * Sets the value of the ogrnDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOgrnDate(XMLGregorianCalendar value) {
        this.ogrnDate = value;
    }

    /**
     * Gets the value of the inn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInn() {
        return inn;
    }

    /**
     * Sets the value of the inn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInn(String value) {
        this.inn = value;
    }

    /**
     * Gets the value of the innAuthority property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInnAuthority() {
        return innAuthority;
    }

    /**
     * Sets the value of the innAuthority property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInnAuthority(String value) {
        this.innAuthority = value;
    }

    /**
     * Gets the value of the innNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInnNum() {
        return innNum;
    }

    /**
     * Sets the value of the innNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInnNum(String value) {
        this.innNum = value;
    }

    /**
     * Gets the value of the innDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getInnDate() {
        return innDate;
    }

    /**
     * Sets the value of the innDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setInnDate(XMLGregorianCalendar value) {
        this.innDate = value;
    }

    /**
     * Gets the value of the kpp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKpp() {
        return kpp;
    }

    /**
     * Sets the value of the kpp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKpp(String value) {
        this.kpp = value;
    }

    /**
     * Gets the value of the okpo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOkpo() {
        return okpo;
    }

    /**
     * Sets the value of the okpo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOkpo(String value) {
        this.okpo = value;
    }

    /**
     * Gets the value of the orgFormCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgFormCode() {
        return orgFormCode;
    }

    /**
     * Sets the value of the orgFormCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgFormCode(String value) {
        this.orgFormCode = value;
    }

    /**
     * Gets the value of the postalAddress property.
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getPostalAddress() {
        return postalAddress;
    }

    /**
     * Sets the value of the postalAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setPostalAddress(Address value) {
        this.postalAddress = value;
    }

    /**
     * Gets the value of the factAddress property.
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getFactAddress() {
        return factAddress;
    }

    /**
     * Sets the value of the factAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setFactAddress(Address value) {
        this.factAddress = value;
    }

    /**
     * Gets the value of the orgHead property.
     * 
     * @return
     *     possible object is
     *     {@link RequestContact }
     *     
     */
    public RequestContact getOrgHead() {
        return orgHead;
    }

    /**
     * Sets the value of the orgHead property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestContact }
     *     
     */
    public void setOrgHead(RequestContact value) {
        this.orgHead = value;
    }

    /**
     * Gets the value of the okved property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOkved() {
        return okved;
    }

    /**
     * Sets the value of the okved property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOkved(String value) {
        this.okved = value;
    }

    /**
     * Gets the value of the okfs property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOkfs() {
        return okfs;
    }

    /**
     * Sets the value of the okfs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOkfs(String value) {
        this.okfs = value;
    }

    /**
     * Gets the value of the bankName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * Sets the value of the bankName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankName(String value) {
        this.bankName = value;
    }

    /**
     * Gets the value of the bankBik property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankBik() {
        return bankBik;
    }

    /**
     * Sets the value of the bankBik property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankBik(String value) {
        this.bankBik = value;
    }

    /**
     * Gets the value of the corrAccount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorrAccount() {
        return corrAccount;
    }

    /**
     * Sets the value of the corrAccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorrAccount(String value) {
        this.corrAccount = value;
    }

    /**
     * Gets the value of the setAccount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSetAccount() {
        return setAccount;
    }

    /**
     * Sets the value of the setAccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSetAccount(String value) {
        this.setAccount = value;
    }

    /**
     * Gets the value of the phone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the value of the phone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhone(String value) {
        this.phone = value;
    }

    /**
     * Gets the value of the fax property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFax() {
        return fax;
    }

    /**
     * Sets the value of the fax property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFax(String value) {
        this.fax = value;
    }

    /**
     * Gets the value of the eMail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEMail() {
        return eMail;
    }

    /**
     * Sets the value of the eMail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEMail(String value) {
        this.eMail = value;
    }

    /**
     * Gets the value of the webSite property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWebSite() {
        return webSite;
    }

    /**
     * Sets the value of the webSite property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWebSite(String value) {
        this.webSite = value;
    }

}
