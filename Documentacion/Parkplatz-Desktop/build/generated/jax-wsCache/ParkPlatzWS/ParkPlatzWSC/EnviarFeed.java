
package ParkPlatzWSC;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para enviarFeed complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="enviarFeed">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Feedback" type="{http://WS.Controlador/}feedback" minOccurs="0"/>
 *         &lt;element name="Estacionamiento" type="{http://WS.Controlador/}estacionamiento" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "enviarFeed", propOrder = {
    "feedback",
    "estacionamiento"
})
public class EnviarFeed {

    @XmlElement(name = "Feedback")
    protected Feedback feedback;
    @XmlElement(name = "Estacionamiento")
    protected Estacionamiento estacionamiento;

    /**
     * Obtiene el valor de la propiedad feedback.
     * 
     * @return
     *     possible object is
     *     {@link Feedback }
     *     
     */
    public Feedback getFeedback() {
        return feedback;
    }

    /**
     * Define el valor de la propiedad feedback.
     * 
     * @param value
     *     allowed object is
     *     {@link Feedback }
     *     
     */
    public void setFeedback(Feedback value) {
        this.feedback = value;
    }

    /**
     * Obtiene el valor de la propiedad estacionamiento.
     * 
     * @return
     *     possible object is
     *     {@link Estacionamiento }
     *     
     */
    public Estacionamiento getEstacionamiento() {
        return estacionamiento;
    }

    /**
     * Define el valor de la propiedad estacionamiento.
     * 
     * @param value
     *     allowed object is
     *     {@link Estacionamiento }
     *     
     */
    public void setEstacionamiento(Estacionamiento value) {
        this.estacionamiento = value;
    }

}
