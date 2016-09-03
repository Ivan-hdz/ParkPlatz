
package ParkPlatzWSC;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para registraUnEstacionamiento complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="registraUnEstacionamiento">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ObjetoConDatosNvoEst" type="{http://WS.Controlador/}estacionamiento" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "registraUnEstacionamiento", propOrder = {
    "objetoConDatosNvoEst"
})
public class RegistraUnEstacionamiento {

    @XmlElement(name = "ObjetoConDatosNvoEst")
    protected Estacionamiento objetoConDatosNvoEst;

    /**
     * Obtiene el valor de la propiedad objetoConDatosNvoEst.
     * 
     * @return
     *     possible object is
     *     {@link Estacionamiento }
     *     
     */
    public Estacionamiento getObjetoConDatosNvoEst() {
        return objetoConDatosNvoEst;
    }

    /**
     * Define el valor de la propiedad objetoConDatosNvoEst.
     * 
     * @param value
     *     allowed object is
     *     {@link Estacionamiento }
     *     
     */
    public void setObjetoConDatosNvoEst(Estacionamiento value) {
        this.objetoConDatosNvoEst = value;
    }

}
