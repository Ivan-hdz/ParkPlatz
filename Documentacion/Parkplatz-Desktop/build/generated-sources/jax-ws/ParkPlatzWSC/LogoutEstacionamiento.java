
package ParkPlatzWSC;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para logoutEstacionamiento complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="logoutEstacionamiento">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="objetoEstacionamiento" type="{http://WS.Controlador/}estacionamiento" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "logoutEstacionamiento", propOrder = {
    "objetoEstacionamiento"
})
public class LogoutEstacionamiento {

    protected Estacionamiento objetoEstacionamiento;

    /**
     * Obtiene el valor de la propiedad objetoEstacionamiento.
     * 
     * @return
     *     possible object is
     *     {@link Estacionamiento }
     *     
     */
    public Estacionamiento getObjetoEstacionamiento() {
        return objetoEstacionamiento;
    }

    /**
     * Define el valor de la propiedad objetoEstacionamiento.
     * 
     * @param value
     *     allowed object is
     *     {@link Estacionamiento }
     *     
     */
    public void setObjetoEstacionamiento(Estacionamiento value) {
        this.objetoEstacionamiento = value;
    }

}
