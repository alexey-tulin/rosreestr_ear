//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.11.07 at 02:19:58 PM AMT 
//


package ru.rosreestr.client.isur.model.base64;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *       &lt;attribute name="Quantity" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="Quantity_Sheet" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class Original {

    @XmlAttribute(name = "Quantity")
    protected Integer quantity;
    @XmlAttribute(name = "Quantity_Sheet")
    protected Integer quantitySheet;

    /**
     * Gets the value of the quantity property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Sets the value of the quantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setQuantity(Integer value) {
        this.quantity = value;
    }

    /**
     * Gets the value of the quantitySheet property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getQuantitySheet() {
        return quantitySheet;
    }

    /**
     * Sets the value of the quantitySheet property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setQuantitySheet(Integer value) {
        this.quantitySheet = value;
    }

}
