
package ru.rosreestr.client.isur.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfRequestContact complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfRequestContact">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RequestContact" type="{http://asguf.mos.ru/rkis_gu/coordinate/v5/}RequestContact" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfRequestContact", propOrder = {
    "requestContact"
})
public class ArrayOfRequestContact {

    @XmlElement(name = "RequestContact", nillable = true)
    protected List<RequestContact> requestContact;

    /**
     * Gets the value of the requestContact property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the requestContact property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRequestContact().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RequestContact }
     * 
     * 
     */
    public List<RequestContact> getRequestContact() {
        if (requestContact == null) {
            requestContact = new ArrayList<RequestContact>();
        }
        return this.requestContact;
    }

}
