
package ParkPlatzWSC;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para actualizarServicio complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="actualizarServicio">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idServicio" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="nuevoNombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nuevaDescripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nuevoCosto" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "actualizarServicio", propOrder = {
    "idServicio",
    "nuevoNombre",
    "nuevaDescripcion",
    "nuevoCosto"
})
public class ActualizarServicio {

    protected int idServicio;
    protected String nuevoNombre;
    protected String nuevaDescripcion;
    protected float nuevoCosto;

    /**
     * Obtiene el valor de la propiedad idServicio.
     * 
     */
    public int getIdServicio() {
        return idServicio;
    }

    /**
     * Define el valor de la propiedad idServicio.
     * 
     */
    public void setIdServicio(int value) {
        this.idServicio = value;
    }

    /**
     * Obtiene el valor de la propiedad nuevoNombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNuevoNombre() {
        return nuevoNombre;
    }

    /**
     * Define el valor de la propiedad nuevoNombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNuevoNombre(String value) {
        this.nuevoNombre = value;
    }

    /**
     * Obtiene el valor de la propiedad nuevaDescripcion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNuevaDescripcion() {
        return nuevaDescripcion;
    }

    /**
     * Define el valor de la propiedad nuevaDescripcion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNuevaDescripcion(String value) {
        this.nuevaDescripcion = value;
    }

    /**
     * Obtiene el valor de la propiedad nuevoCosto.
     * 
     */
    public float getNuevoCosto() {
        return nuevoCosto;
    }

    /**
     * Define el valor de la propiedad nuevoCosto.
     * 
     */
    public void setNuevoCosto(float value) {
        this.nuevoCosto = value;
    }

}
