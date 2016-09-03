
package ParkPlatzWSC;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para obtenerMisEstacionamientos complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="obtenerMisEstacionamientos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="objetoEst" type="{http://WS.Controlador/}estacionamiento" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "obtenerMisEstacionamientos", propOrder = {
    "objetoEst"
})
public class ObtenerMisEstacionamientos {

    protected Estacionamiento objetoEst;

    /**
     * Obtiene el valor de la propiedad objetoEst.
     * 
     * @return
     *     possible object is
     *     {@link Estacionamiento }
     *     
     */
    public Estacionamiento getObjetoEst() {
        return objetoEst;
    }

    /**
     * Define el valor de la propiedad objetoEst.
     * 
     * @param value
     *     allowed object is
     *     {@link Estacionamiento }
     *     
     */
    public void setObjetoEst(Estacionamiento value) {
        this.objetoEst = value;
    }

}
