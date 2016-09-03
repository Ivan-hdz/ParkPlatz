
package ParkPlatzWSC;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para obtenerSevicios complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="obtenerSevicios">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idEstacionamiento" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "obtenerSevicios", propOrder = {
    "idEstacionamiento"
})
public class ObtenerSevicios {

    protected int idEstacionamiento;

    /**
     * Obtiene el valor de la propiedad idEstacionamiento.
     * 
     */
    public int getIdEstacionamiento() {
        return idEstacionamiento;
    }

    /**
     * Define el valor de la propiedad idEstacionamiento.
     * 
     */
    public void setIdEstacionamiento(int value) {
        this.idEstacionamiento = value;
    }

}
