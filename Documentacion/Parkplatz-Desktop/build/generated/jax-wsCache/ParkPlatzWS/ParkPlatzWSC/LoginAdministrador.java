
package ParkPlatzWSC;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para loginAdministrador complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="loginAdministrador">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="objetoConUserPass" type="{http://WS.Controlador/}administrador" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "loginAdministrador", propOrder = {
    "objetoConUserPass"
})
public class LoginAdministrador {

    protected Administrador objetoConUserPass;

    /**
     * Obtiene el valor de la propiedad objetoConUserPass.
     * 
     * @return
     *     possible object is
     *     {@link Administrador }
     *     
     */
    public Administrador getObjetoConUserPass() {
        return objetoConUserPass;
    }

    /**
     * Define el valor de la propiedad objetoConUserPass.
     * 
     * @param value
     *     allowed object is
     *     {@link Administrador }
     *     
     */
    public void setObjetoConUserPass(Administrador value) {
        this.objetoConUserPass = value;
    }

}
