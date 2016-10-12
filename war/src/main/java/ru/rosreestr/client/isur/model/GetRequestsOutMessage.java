
package ru.rosreestr.client.isur.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetRequestsOutMessage complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetRequestsOutMessage">
 *   &lt;complexContent>
 *     &lt;extension base="{http://asguf.mos.ru/rkis_gu/coordinate/v5/}MessageBase">
 *       &lt;sequence>
 *         &lt;element name="Requests" type="{http://asguf.mos.ru/rkis_gu/coordinate/v5/}ArrayOfRequestInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetRequestsOutMessage", propOrder = {
    "requests"
})
public class GetRequestsOutMessage
    extends MessageBase
{

    @XmlElement(name = "Requests")
    protected ArrayOfRequestInfo requests;

    /**
     * Gets the value of the requests property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRequestInfo }
     *     
     */
    public ArrayOfRequestInfo getRequests() {
        return requests;
    }

    /**
     * Sets the value of the requests property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRequestInfo }
     *     
     */
    public void setRequests(ArrayOfRequestInfo value) {
        this.requests = value;
    }

}
