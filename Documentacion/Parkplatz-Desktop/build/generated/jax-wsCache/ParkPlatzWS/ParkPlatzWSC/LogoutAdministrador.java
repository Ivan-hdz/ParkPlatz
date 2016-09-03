
package ParkPlatzWSC;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para logoutAdministrador complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="logoutAdministrador">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="objetoAdministrador" type="{http://WS.Controlador/}administrador" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "logoutAdministrador", propOrder = {
    "objetoAdministrador"
})
public class LogoutAdministrador {

    protected Administrador objetoAdministrador;

    /**
     * Obtiene el valor de la propiedad objetoAdministrador.
     * 
     * @return
     *     possible object is
     *     {@link Administrador }
     *     
     */
    public Administrador getObjetoAdministrador() {
        return objetoAdministrador;
    }

    /**
     * Define el valor de la propiedad objetoAdministrador.
     * 
     * @param value
     *     allowed object is
     *     {@link Administrador }
     *     
     */
    public void setObjetoAdministrador(Administrador value) {
        this.objetoAdministrador = value;
    }

}
