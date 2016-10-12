
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
 *         &lt;element name="GetRequestsResult" type="{http://asguf.mos.ru/rkis_gu/coordinate/v5/}GetRequestsOutMessage" minOccurs="0"/>
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
    "getRequestsResult"
})
@XmlRootElement(name = "GetRequestsResponse")
public class GetRequestsResponse {

    @XmlElement(name = "GetRequestsResult")
    protected GetRequestsOutMessage getRequestsResult;

    /**
     * Gets the value of the getRequestsResult property.
     * 
     * @return
     *     possible object is
     *     {@link GetRequestsOutMessage }
     *     
     */
    public GetRequestsOutMessage getGetRequestsResult() {
        return getRequestsResult;
    }

    /**
     * Sets the value of the getRequestsResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetRequestsOutMessage }
     *     
     */
    public void setGetRequestsResult(GetRequestsOutMessage value) {
        this.getRequestsResult = value;
    }

}
