
package ParkPlatzWSC;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para conductor complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="conductor">
 *   &lt;complexContent>
 *     &lt;extension base="{http://WS.Controlador/}persona">
 *       &lt;sequence>
 *         &lt;element name="correo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="misFavs" type="{http://WS.Controlador/}favoritos" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="misFavsLLeno" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="misRecientes" type="{http://WS.Controlador/}recientes" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="misRecientesLleno" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
@XmlType(name = "conductor", propOrder = {
    "correo",
    "misFavs",
    "misFavsLLeno",
    "misRecientes",
    "misRecientesLleno",
    "nombre",
    "pass",
    "aMaterno",
    "aPaterno"
})
public class Conductor
    extends Persona
{

    protected String correo;
    @XmlElement(nillable = true)
    protected List<Favoritos> misFavs;
    protected boolean misFavsLLeno;
    @XmlElement(nillable = true)
    protected List<Recientes> misRecientes;
    protected boolean misRecientesLleno;
    protected String nombre;
    protected String pass;
    protected String aMaterno;
    protected String aPaterno;

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
     * Gets the value of the misFavs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the misFavs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMisFavs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Favoritos }
     * 
     * 
     */
    public List<Favoritos> getMisFavs() {
        if (misFavs == null) {
            misFavs = new ArrayList<Favoritos>();
        }
        return this.misFavs;
    }

    /**
     * Obtiene el valor de la propiedad misFavsLLeno.
     * 
     */
    public boolean isMisFavsLLeno() {
        return misFavsLLeno;
    }

    /**
     * Define el valor de la propiedad misFavsLLeno.
     * 
     */
    public void setMisFavsLLeno(boolean value) {
        this.misFavsLLeno = value;
    }

    /**
     * Gets the value of the misRecientes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the misRecientes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMisRecientes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Recientes }
     * 
     * 
     */
    public List<Recientes> getMisRecientes() {
        if (misRecientes == null) {
            misRecientes = new ArrayList<Recientes>();
        }
        return this.misRecientes;
    }

    /**
     * Obtiene el valor de la propiedad misRecientesLleno.
     * 
     */
    public boolean isMisRecientesLleno() {
        return misRecientesLleno;
    }

    /**
     * Define el valor de la propiedad misRecientesLleno.
     * 
     */
    public void setMisRecientesLleno(boolean value) {
        this.misRecientesLleno = value;
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
