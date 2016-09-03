
package ParkPlatzWSC;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para persona complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="persona">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="logueado" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "persona", propOrder = {
    "logueado"
})
@XmlSeeAlso({
    Administrador.class,
    Estacionamiento.class,
    Conductor.class
})
public abstract class Persona {

    protected boolean logueado;

    /**
     * Obtiene el valor de la propiedad logueado.
     * 
     */
    public boolean isLogueado() {
        return logueado;
    }

    /**
     * Define el valor de la propiedad logueado.
     * 
     */
    public void setLogueado(boolean value) {
        this.logueado = value;
    }

}
