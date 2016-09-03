
package ParkPlatzWSC;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "ClassNotFoundException", targetNamespace = "http://WS.Controlador/")
public class ClassNotFoundException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private ClassNotFoundException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public ClassNotFoundException_Exception(String message, ClassNotFoundException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public ClassNotFoundException_Exception(String message, ClassNotFoundException faultInfo, java.lang.Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: ParkPlatzWSC.ClassNotFoundException
     */
    public ClassNotFoundException getFaultInfo() {
        return faultInfo;
    }

}
