
package ru.rosreestr.client.isur.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="GetRequestListResult" type="{http://asguf.mos.ru/rkis_gu/coordinate/v5/}GetRequestListOutMessage" minOccurs="0"/>
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
    "getRequestListResult"
})
@XmlRootElement(name = "GetRequestListResponse")
public class GetRequestListResponse {

    @XmlElement(name = "GetRequestListResult")
    protected GetRequestListOutMessage getRequestListResult;

    /**
     * Gets the value of the getRequestListResult property.
     * 
     * @return
     *     possible object is
     *     {@link GetRequestListOutMessage }
     *     
     */
    public GetRequestListOutMessage getGetRequestListResult() {
        return getRequestListResult;
    }

    /**
     * Sets the value of the getRequestListResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetRequestListOutMessage }
     *     
     */
    public void setGetRequestListResult(GetRequestListOutMessage value) {
        this.getRequestListResult = value;
    }

}
