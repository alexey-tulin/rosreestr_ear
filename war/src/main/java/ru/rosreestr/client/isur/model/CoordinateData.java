
package ru.rosreestr.client.isur.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CoordinateData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CoordinateData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Declarant" type="{http://asguf.mos.ru/rkis_gu/coordinate/v5/}BaseDeclarant" minOccurs="0"/>
 *         &lt;element name="Trustee" type="{http://asguf.mos.ru/rkis_gu/coordinate/v5/}RequestContact" minOccurs="0"/>
 *         &lt;element name="Service" type="{http://asguf.mos.ru/rkis_gu/coordinate/v5/}RequestService" minOccurs="0"/>
 *         &lt;element name="CustomAttributes" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;any/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Contacts" type="{http://asguf.mos.ru/rkis_gu/coordinate/v5/}ArrayOfRequestContact" minOccurs="0"/>
 *         &lt;element name="Signature" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CoordinateData", propOrder = {
    "declarant",
    "trustee",
    "service",
    "customAttributes",
    "contacts",
    "signature"
})
public class CoordinateData {

    @XmlElement(name = "Declarant")
    protected BaseDeclarant declarant;
    @XmlElement(name = "Trustee")
    protected RequestContact trustee;
    @XmlElement(name = "Service")
    protected RequestService service;
    @XmlElement(name = "CustomAttributes")
    protected CustomAttributes customAttributes;
    @XmlElement(name = "Contacts")
    protected ArrayOfRequestContact contacts;
    @XmlElement(name = "Signature")
    protected byte[] signature;

    /**
     * Gets the value of the declarant property.
     * 
     * @return
     *     possible object is
     *     {@link BaseDeclarant }
     *     
     */
    public BaseDeclarant getDeclarant() {
        return declarant;
    }

    /**
     * Sets the value of the declarant property.
     * 
     * @param value
     *     allowed object is
     *     {@link BaseDeclarant }
     *     
     */
    public void setDeclarant(BaseDeclarant value) {
        this.declarant = value;
    }

    /**
     * Gets the value of the trustee property.
     * 
     * @return
     *     possible object is
     *     {@link RequestContact }
     *     
     */
    public RequestContact getTrustee() {
        return trustee;
    }

    /**
     * Sets the value of the trustee property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestContact }
     *     
     */
    public void setTrustee(RequestContact value) {
        this.trustee = value;
    }

    /**
     * Gets the value of the service property.
     * 
     * @return
     *     possible object is
     *     {@link RequestService }
     *     
     */
    public RequestService getService() {
        return service;
    }

    /**
     * Sets the value of the service property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestService }
     *     
     */
    public void setService(RequestService value) {
        this.service = value;
    }

    /**
     * Gets the value of the customAttributes property.
     * 
     * @return
     *     possible object is
     *     {@link CustomAttributes }
     *     
     */
    public CustomAttributes getCustomAttributes() {
        return customAttributes;
    }

    /**
     * Sets the value of the customAttributes property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomAttributes }
     *     
     */
    public void setCustomAttributes(CustomAttributes value) {
        this.customAttributes = value;
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

    /**
     * Gets the value of the signature property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getSignature() {
        return signature;
    }

    /**
     * Sets the value of the signature property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setSignature(byte[] value) {
        this.signature = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;any/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "any"
    })
    public static class CustomAttributes {

        @XmlAnyElement(lax = true)
        protected Object any;

        /**
         * Gets the value of the any property.
         * 
         * @return
         *     possible object is
         *     {@link Object }
         *     
         */
        public Object getAny() {
            return any;
        }

        /**
         * Sets the value of the any property.
         * 
         * @param value
         *     allowed object is
         *     {@link Object }
         *     
         */
        public void setAny(Object value) {
            this.any = value;
        }

    }

}
