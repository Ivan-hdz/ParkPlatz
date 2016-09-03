
package ParkPlatzWSC;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para registrarEstacionamiento complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="registrarEstacionamiento">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="objetoLleno" type="{http://WS.Controlador/}estacionamiento" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "registrarEstacionamiento", propOrder = {
    "objetoLleno"
})
public class RegistrarEstacionamiento {

    protected Estacionamiento objetoLleno;

    /**
     * Obtiene el valor de la propiedad objetoLleno.
     * 
     * @return
     *     possible object is
     *     {@link Estacionamiento }
     *     
     */
    public Estacionamiento getObjetoLleno() {
        return objetoLleno;
    }

    /**
     * Define el valor de la propiedad objetoLleno.
     * 
     * @param value
     *     allowed object is
     *     {@link Estacionamiento }
     *     
     */
    public void setObjetoLleno(Estacionamiento value) {
        this.objetoLleno = value;
    }

}
