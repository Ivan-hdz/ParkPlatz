
package ParkPlatzWSC;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para get_feedback complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="get_feedback">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="admin" type="{http://WS.Controlador/}administrador" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "get_feedback", propOrder = {
    "admin"
})
public class GetFeedback {

    protected Administrador admin;

    /**
     * Obtiene el valor de la propiedad admin.
     * 
     * @return
     *     possible object is
     *     {@link Administrador }
     *     
     */
    public Administrador getAdmin() {
        return admin;
    }

    /**
     * Define el valor de la propiedad admin.
     * 
     * @param value
     *     allowed object is
     *     {@link Administrador }
     *     
     */
    public void setAdmin(Administrador value) {
        this.admin = value;
    }

}
