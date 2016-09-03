
package ParkPlatzWSC;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para nuevoServicioOferta complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="nuevoServicioOferta">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nombreServ" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descServ" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="costServ" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
 *         &lt;element name="idEsta" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "nuevoServicioOferta", propOrder = {
    "nombreServ",
    "descServ",
    "costServ",
    "idEsta"
})
public class NuevoServicioOferta {

    protected String nombreServ;
    protected String descServ;
    protected Float costServ;
    protected int idEsta;

    /**
     * Obtiene el valor de la propiedad nombreServ.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreServ() {
        return nombreServ;
    }

    /**
     * Define el valor de la propiedad nombreServ.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreServ(String value) {
        this.nombreServ = value;
    }

    /**
     * Obtiene el valor de la propiedad descServ.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescServ() {
        return descServ;
    }

    /**
     * Define el valor de la propiedad descServ.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescServ(String value) {
        this.descServ = value;
    }

    /**
     * Obtiene el valor de la propiedad costServ.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getCostServ() {
        return costServ;
    }

    /**
     * Define el valor de la propiedad costServ.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setCostServ(Float value) {
        this.costServ = value;
    }

    /**
     * Obtiene el valor de la propiedad idEsta.
     * 
     */
    public int getIdEsta() {
        return idEsta;
    }

    /**
     * Define el valor de la propiedad idEsta.
     * 
     */
    public void setIdEsta(int value) {
        this.idEsta = value;
    }

}
