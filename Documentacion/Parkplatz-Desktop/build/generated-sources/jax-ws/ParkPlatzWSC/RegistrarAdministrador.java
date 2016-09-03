
package ParkPlatzWSC;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para registrarAdministrador complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="registrarAdministrador">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="objetoLleno" type="{http://WS.Controlador/}administrador" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "registrarAdministrador", propOrder = {
    "objetoLleno"
})
public class RegistrarAdministrador {

    protected Administrador objetoLleno;

    /**
     * Obtiene el valor de la propiedad objetoLleno.
     * 
     * @return
     *     possible object is
     *     {@link Administrador }
     *     
     */
    public Administrador getObjetoLleno() {
        return objetoLleno;
    }

    /**
     * Define el valor de la propiedad objetoLleno.
     * 
     * @param value
     *     allowed object is
     *     {@link Administrador }
     *     
     */
    public void setObjetoLleno(Administrador value) {
        this.objetoLleno = value;
    }

}
