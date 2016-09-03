
package ParkPlatzWSC;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para recuperarDatosEsta complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="recuperarDatosEsta">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="objetoConIdaRecuperar" type="{http://WS.Controlador/}estacionamiento" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "recuperarDatosEsta", propOrder = {
    "objetoConIdaRecuperar"
})
public class RecuperarDatosEsta {

    protected Estacionamiento objetoConIdaRecuperar;

    /**
     * Obtiene el valor de la propiedad objetoConIdaRecuperar.
     * 
     * @return
     *     possible object is
     *     {@link Estacionamiento }
     *     
     */
    public Estacionamiento getObjetoConIdaRecuperar() {
        return objetoConIdaRecuperar;
    }

    /**
     * Define el valor de la propiedad objetoConIdaRecuperar.
     * 
     * @param value
     *     allowed object is
     *     {@link Estacionamiento }
     *     
     */
    public void setObjetoConIdaRecuperar(Estacionamiento value) {
        this.objetoConIdaRecuperar = value;
    }

}
