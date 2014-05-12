
package ServicesTypes;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for field complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="field">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="successors" type="{http://Services/}field" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="turtles" type="{http://Services/}turtle" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="type" type="{http://Services/}fieldType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "field", propOrder = {
    "id",
    "successors",
    "turtles",
    "type"
})
public class Field {

    protected int id;
    @XmlElement(nillable = true)
    protected List<Field> successors;
    @XmlElement(nillable = true)
    protected List<Turtle> turtles;
    protected FieldType type;

    /**
     * Gets the value of the id property.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Gets the value of the successors property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the successors property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSuccessors().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Field }
     * 
     * 
     */
    public List<Field> getSuccessors() {
        if (successors == null) {
            successors = new ArrayList<Field>();
        }
        return this.successors;
    }

    /**
     * Gets the value of the turtles property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the turtles property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTurtles().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Turtle }
     * 
     * 
     */
    public List<Turtle> getTurtles() {
        if (turtles == null) {
            turtles = new ArrayList<Turtle>();
        }
        return this.turtles;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link FieldType }
     *     
     */
    public FieldType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link FieldType }
     *     
     */
    public void setType(FieldType value) {
        this.type = value;
    }

}
