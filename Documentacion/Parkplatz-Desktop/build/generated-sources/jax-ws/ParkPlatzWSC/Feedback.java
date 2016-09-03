
package ParkPlatzWSC;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para feedback complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="feedback">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fecha" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idEstaADarAviso" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="idFeedback" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="idUsuarioRedactor" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="prioridad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "feedback", propOrder = {
    "descripcion",
    "fecha",
    "idEstaADarAviso",
    "idFeedback",
    "idUsuarioRedactor",
    "prioridad"
})
public class Feedback {

    protected String descripcion;
    protected String fecha;
    protected int idEstaADarAviso;
    protected int idFeedback;
    protected int idUsuarioRedactor;
    protected String prioridad;

    /**
     * Obtiene el valor de la propiedad descripcion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Define el valor de la propiedad descripcion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    /**
     * Obtiene el valor de la propiedad fecha.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Define el valor de la propiedad fecha.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFecha(String value) {
        this.fecha = value;
    }

    /**
     * Obtiene el valor de la propiedad idEstaADarAviso.
     * 
     */
    public int getIdEstaADarAviso() {
        return idEstaADarAviso;
    }

    /**
     * Define el valor de la propiedad idEstaADarAviso.
     * 
     */
    public void setIdEstaADarAviso(int value) {
        this.idEstaADarAviso = value;
    }

    /**
     * Obtiene el valor de la propiedad idFeedback.
     * 
     */
    public int getIdFeedback() {
        return idFeedback;
    }

    /**
     * Define el valor de la propiedad idFeedback.
     * 
     */
    public void setIdFeedback(int value) {
        this.idFeedback = value;
    }

    /**
     * Obtiene el valor de la propiedad idUsuarioRedactor.
     * 
     */
    public int getIdUsuarioRedactor() {
        return idUsuarioRedactor;
    }

    /**
     * Define el valor de la propiedad idUsuarioRedactor.
     * 
     */
    public void setIdUsuarioRedactor(int value) {
        this.idUsuarioRedactor = value;
    }

    /**
     * Obtiene el valor de la propiedad prioridad.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrioridad() {
        return prioridad;
    }

    /**
     * Define el valor de la propiedad prioridad.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrioridad(String value) {
        this.prioridad = value;
    }

}
