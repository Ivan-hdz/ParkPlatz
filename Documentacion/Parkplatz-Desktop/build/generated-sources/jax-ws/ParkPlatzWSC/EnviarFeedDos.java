
package ParkPlatzWSC;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para enviarFeed_dos complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="enviarFeed_dos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fed" type="{http://WS.Controlador/}feedback" minOccurs="0"/>
 *         &lt;element name="conductor" type="{http://WS.Controlador/}conductor" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "enviarFeed_dos", propOrder = {
    "fed",
    "conductor"
})
public class EnviarFeedDos {

    protected Feedback fed;
    protected Conductor conductor;

    /**
     * Obtiene el valor de la propiedad fed.
     * 
     * @return
     *     possible object is
     *     {@link Feedback }
     *     
     */
    public Feedback getFed() {
        return fed;
    }

    /**
     * Define el valor de la propiedad fed.
     * 
     * @param value
     *     allowed object is
     *     {@link Feedback }
     *     
     */
    public void setFed(Feedback value) {
        this.fed = value;
    }

    /**
     * Obtiene el valor de la propiedad conductor.
     * 
     * @return
     *     possible object is
     *     {@link Conductor }
     *     
     */
    public Conductor getConductor() {
        return conductor;
    }

    /**
     * Define el valor de la propiedad conductor.
     * 
     * @param value
     *     allowed object is
     *     {@link Conductor }
     *     
     */
    public void setConductor(Conductor value) {
        this.conductor = value;
    }

}
