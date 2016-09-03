
package ParkPlatzWSC;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para logoutConductor complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="logoutConductor">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="objetoConductor" type="{http://WS.Controlador/}conductor" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "logoutConductor", propOrder = {
    "objetoConductor"
})
public class LogoutConductor {

    protected Conductor objetoConductor;

    /**
     * Obtiene el valor de la propiedad objetoConductor.
     * 
     * @return
     *     possible object is
     *     {@link Conductor }
     *     
     */
    public Conductor getObjetoConductor() {
        return objetoConductor;
    }

    /**
     * Define el valor de la propiedad objetoConductor.
     * 
     * @param value
     *     allowed object is
     *     {@link Conductor }
     *     
     */
    public void setObjetoConductor(Conductor value) {
        this.objetoConductor = value;
    }

}
