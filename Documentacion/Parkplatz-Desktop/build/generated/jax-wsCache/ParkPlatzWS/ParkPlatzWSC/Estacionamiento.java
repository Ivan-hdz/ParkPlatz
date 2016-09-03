
package ParkPlatzWSC;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para estacionamiento complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="estacionamiento">
 *   &lt;complexContent>
 *     &lt;extension base="{http://WS.Controlador/}persona">
 *       &lt;sequence>
 *         &lt;element name="alturaMaxima" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="calle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="colonia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cordenadaX" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="cordenadaY" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="correo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="del_muni" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="estado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="horario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idEstacionamiento" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="misEstacionamientos" type="{http://WS.Controlador/}estacionamiento" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombreEsta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pass" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tarifa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="urlImg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="aMaterno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="aPaterno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "estacionamiento", propOrder = {
    "alturaMaxima",
    "calle",
    "colonia",
    "cordenadaX",
    "cordenadaY",
    "correo",
    "delMuni",
    "descripcion",
    "estado",
    "horario",
    "idEstacionamiento",
    "misEstacionamientos",
    "nombre",
    "nombreEsta",
    "pass",
    "tarifa",
    "urlImg",
    "aMaterno",
    "aPaterno"
})
public class Estacionamiento
    extends Persona
{

    protected float alturaMaxima;
    protected String calle;
    protected String colonia;
    protected double cordenadaX;
    protected double cordenadaY;
    protected String correo;
    @XmlElement(name = "del_muni")
    protected String delMuni;
    protected String descripcion;
    protected String estado;
    protected String horario;
    protected int idEstacionamiento;
    @XmlElement(nillable = true)
    protected List<Estacionamiento> misEstacionamientos;
    protected String nombre;
    protected String nombreEsta;
    protected String pass;
    protected String tarifa;
    protected String urlImg;
    protected String aMaterno;
    protected String aPaterno;

    /**
     * Obtiene el valor de la propiedad alturaMaxima.
     * 
     */
    public float getAlturaMaxima() {
        return alturaMaxima;
    }

    /**
     * Define el valor de la propiedad alturaMaxima.
     * 
     */
    public void setAlturaMaxima(float value) {
        this.alturaMaxima = value;
    }

    /**
     * Obtiene el valor de la propiedad calle.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCalle() {
        return calle;
    }

    /**
     * Define el valor de la propiedad calle.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCalle(String value) {
        this.calle = value;
    }

    /**
     * Obtiene el valor de la propiedad colonia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getColonia() {
        return colonia;
    }

    /**
     * Define el valor de la propiedad colonia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setColonia(String value) {
        this.colonia = value;
    }

    /**
     * Obtiene el valor de la propiedad cordenadaX.
     * 
     */
    public double getCordenadaX() {
        return cordenadaX;
    }

    /**
     * Define el valor de la propiedad cordenadaX.
     * 
     */
    public void setCordenadaX(double value) {
        this.cordenadaX = value;
    }

    /**
     * Obtiene el valor de la propiedad cordenadaY.
     * 
     */
    public double getCordenadaY() {
        return cordenadaY;
    }

    /**
     * Define el valor de la propiedad cordenadaY.
     * 
     */
    public void setCordenadaY(double value) {
        this.cordenadaY = value;
    }

    /**
     * Obtiene el valor de la propiedad correo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Define el valor de la propiedad correo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorreo(String value) {
        this.correo = value;
    }

    /**
     * Obtiene el valor de la propiedad delMuni.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDelMuni() {
        return delMuni;
    }

    /**
     * Define el valor de la propiedad delMuni.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDelMuni(String value) {
        this.delMuni = value;
    }

    /**
     * Obtiene el valor de la propiedad descripcion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Define el valor de la propiedad descripcion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    /**
     * Obtiene el valor de la propiedad estado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Define el valor de la propiedad estado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstado(String value) {
        this.estado = value;
    }

    /**
     * Obtiene el valor de la propiedad horario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHorario() {
        return horario;
    }

    /**
     * Define el valor de la propiedad horario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHorario(String value) {
        this.horario = value;
    }

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

    /**
     * Gets the value of the misEstacionamientos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the misEstacionamientos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMisEstacionamientos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Estacionamiento }
     * 
     * 
     */
    public List<Estacionamiento> getMisEstacionamientos() {
        if (misEstacionamientos == null) {
            misEstacionamientos = new ArrayList<Estacionamiento>();
        }
        return this.misEstacionamientos;
    }

    /**
     * Obtiene el valor de la propiedad nombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Define el valor de la propiedad nombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreEsta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreEsta() {
        return nombreEsta;
    }

    /**
     * Define el valor de la propiedad nombreEsta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreEsta(String value) {
        this.nombreEsta = value;
    }

    /**
     * Obtiene el valor de la propiedad pass.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPass() {
        return pass;
    }

    /**
     * Define el valor de la propiedad pass.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPass(String value) {
        this.pass = value;
    }

    /**
     * Obtiene el valor de la propiedad tarifa.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTarifa() {
        return tarifa;
    }

    /**
     * Define el valor de la propiedad tarifa.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTarifa(String value) {
        this.tarifa = value;
    }

    /**
     * Obtiene el valor de la propiedad urlImg.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlImg() {
        return urlImg;
    }

    /**
     * Define el valor de la propiedad urlImg.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlImg(String value) {
        this.urlImg = value;
    }

    /**
     * Obtiene el valor de la propiedad aMaterno.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAMaterno() {
        return aMaterno;
    }

    /**
     * Define el valor de la propiedad aMaterno.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAMaterno(String value) {
        this.aMaterno = value;
    }

    /**
     * Obtiene el valor de la propiedad aPaterno.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAPaterno() {
        return aPaterno;
    }

    /**
     * Define el valor de la propiedad aPaterno.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAPaterno(String value) {
        this.aPaterno = value;
    }

}
