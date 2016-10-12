
package ru.rosreestr.client.isur.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfServiceNumberStatusesOnly complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfServiceNumberStatusesOnly">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ServiceNumberStatusesOnly" type="{http://asguf.mos.ru/rkis_gu/coordinate/v5/}ServiceNumberStatusesOnly" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfServiceNumberStatusesOnly", propOrder = {
    "serviceNumberStatusesOnly"
})
public class ArrayOfServiceNumberStatusesOnly {

    @XmlElement(name = "ServiceNumberStatusesOnly", nillable = true)
    protected List<ServiceNumberStatusesOnly> serviceNumberStatusesOnly;

    /**
     * Gets the value of the serviceNumberStatusesOnly property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the serviceNumberStatusesOnly property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServiceNumberStatusesOnly().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceNumberStatusesOnly }
     * 
     * 
     */
    public List<ServiceNumberStatusesOnly> getServiceNumberStatusesOnly() {
        if (serviceNumberStatusesOnly == null) {
            serviceNumberStatusesOnly = new ArrayList<ServiceNumberStatusesOnly>();
        }
        return this.serviceNumberStatusesOnly;
    }

}
