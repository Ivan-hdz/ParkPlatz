
package ParkPlatzWSC;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para administrador complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="administrador">
 *   &lt;complexContent>
 *     &lt;extension base="{http://WS.Controlador/}persona">
 *       &lt;sequence>
 *         &lt;element name="catFeedback" type="{http://WS.Controlador/}feedback" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="correo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="feedLleno" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pass" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="aMaterno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="aPaterno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "administrador", propOrder = {
    "catFeedback",
    "correo",
    "feedLleno",
    "nombre",
    "pass",
    "aMaterno",
    "aPaterno"
})
public class Administrador
    extends Persona
{

    @XmlElement(nillable = true)
    protected List<Feedback> catFeedback;
    protected String correo;
    protected boolean feedLleno;
    protected String nombre;
    protected String pass;
    protected String aMaterno;
    protected String aPaterno;

    /**
     * Gets the value of the catFeedback property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the catFeedback property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCatFeedback().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Feedback }
     * 
     * 
     */
    public List<Feedback> getCatFeedback() {
        if (catFeedback == null) {
            catFeedback = new ArrayList<Feedback>();
        }
        return this.catFeedback;
    }

    /**
     * Obtiene el valor de la propiedad correo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Define el valor de la propiedad correo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorreo(String value) {
        this.correo = value;
    }

    /**
     * Obtiene el valor de la propiedad feedLleno.
     * 
     */
    public boolean isFeedLleno() {
        return feedLleno;
    }

    /**
     * Define el valor de la propiedad feedLleno.
     * 
     */
    public void setFeedLleno(boolean value) {
        this.feedLleno = value;
    }

    /**
     * Obtiene el valor de la propiedad nombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Define el valor de la propiedad nombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Obtiene el valor de la propiedad pass.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPass() {
        return pass;
    }

    /**
     * Define el valor de la propiedad pass.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPass(String value) {
        this.pass = value;
    }

    /**
     * Obtiene el valor de la propiedad aMaterno.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAMaterno() {
        return aMaterno;
    }

    /**
     * Define el valor de la propiedad aMaterno.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAMaterno(String value) {
        this.aMaterno = value;
    }

    /**
     * Obtiene el valor de la propiedad aPaterno.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAPaterno() {
        return aPaterno;
    }

    /**
     * Define el valor de la propiedad aPaterno.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAPaterno(String value) {
        this.aPaterno = value;
    }

}
