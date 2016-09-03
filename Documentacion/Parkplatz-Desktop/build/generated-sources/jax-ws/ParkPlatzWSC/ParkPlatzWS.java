
package ParkPlatzWSC;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ParkPlatzWS", targetNamespace = "http://WS.Controlador/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ParkPlatzWS {


    /**
     * 
     * @param correo
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "recover", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.Recover")
    @ResponseWrapper(localName = "recoverResponse", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.RecoverResponse")
    @Action(input = "http://WS.Controlador/ParkPlatzWS/recoverRequest", output = "http://WS.Controlador/ParkPlatzWS/recoverResponse")
    public boolean recover(
        @WebParam(name = "correo", targetNamespace = "")
        String correo);

    /**
     * 
     * @param idEstacionamiento
     * @return
     *     returns java.util.List<ParkPlatzWSC.Servicios>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "obtenerSevicios", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.ObtenerSevicios")
    @ResponseWrapper(localName = "obtenerSeviciosResponse", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.ObtenerSeviciosResponse")
    @Action(input = "http://WS.Controlador/ParkPlatzWS/obtenerSeviciosRequest", output = "http://WS.Controlador/ParkPlatzWS/obtenerSeviciosResponse")
    public List<Servicios> obtenerSevicios(
        @WebParam(name = "idEstacionamiento", targetNamespace = "")
        int idEstacionamiento);

    /**
     * 
     * @param objetoConDatosNuevos
     * @return
     *     returns ParkPlatzWSC.Estacionamiento
     * @throws NoSuchAlgorithmException_Exception
     * @throws UnsupportedEncodingException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "actualizarDatos", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.ActualizarDatos")
    @ResponseWrapper(localName = "actualizarDatosResponse", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.ActualizarDatosResponse")
    @Action(input = "http://WS.Controlador/ParkPlatzWS/actualizarDatosRequest", output = "http://WS.Controlador/ParkPlatzWS/actualizarDatosResponse", fault = {
        @FaultAction(className = UnsupportedEncodingException_Exception.class, value = "http://WS.Controlador/ParkPlatzWS/actualizarDatos/Fault/UnsupportedEncodingException"),
        @FaultAction(className = NoSuchAlgorithmException_Exception.class, value = "http://WS.Controlador/ParkPlatzWS/actualizarDatos/Fault/NoSuchAlgorithmException")
    })
    public Estacionamiento actualizarDatos(
        @WebParam(name = "objetoConDatosNuevos", targetNamespace = "")
        Estacionamiento objetoConDatosNuevos)
        throws NoSuchAlgorithmException_Exception, UnsupportedEncodingException_Exception
    ;

    /**
     * 
     * @param idServicio
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "eliminarServ", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.EliminarServ")
    @ResponseWrapper(localName = "eliminarServResponse", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.EliminarServResponse")
    @Action(input = "http://WS.Controlador/ParkPlatzWS/eliminarServRequest", output = "http://WS.Controlador/ParkPlatzWS/eliminarServResponse")
    public String eliminarServ(
        @WebParam(name = "idServicio", targetNamespace = "")
        int idServicio);

    /**
     * 
     * @param objetoConductor
     */
    @WebMethod
    @RequestWrapper(localName = "logoutConductor", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.LogoutConductor")
    @ResponseWrapper(localName = "logoutConductorResponse", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.LogoutConductorResponse")
    @Action(input = "http://WS.Controlador/ParkPlatzWS/logoutConductorRequest", output = "http://WS.Controlador/ParkPlatzWS/logoutConductorResponse")
    public void logoutConductor(
        @WebParam(name = "objetoConductor", targetNamespace = "")
        Conductor objetoConductor);

    /**
     * 
     * @param objetoConUserPass
     * @return
     *     returns ParkPlatzWSC.Conductor
     * @throws NoSuchAlgorithmException_Exception
     * @throws UnsupportedEncodingException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "loginConductor", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.LoginConductor")
    @ResponseWrapper(localName = "loginConductorResponse", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.LoginConductorResponse")
    @Action(input = "http://WS.Controlador/ParkPlatzWS/loginConductorRequest", output = "http://WS.Controlador/ParkPlatzWS/loginConductorResponse", fault = {
        @FaultAction(className = UnsupportedEncodingException_Exception.class, value = "http://WS.Controlador/ParkPlatzWS/loginConductor/Fault/UnsupportedEncodingException"),
        @FaultAction(className = NoSuchAlgorithmException_Exception.class, value = "http://WS.Controlador/ParkPlatzWS/loginConductor/Fault/NoSuchAlgorithmException")
    })
    public Conductor loginConductor(
        @WebParam(name = "objetoConUserPass", targetNamespace = "")
        Conductor objetoConUserPass)
        throws NoSuchAlgorithmException_Exception, UnsupportedEncodingException_Exception
    ;

    /**
     * 
     * @param feedback
     * @param estacionamiento
     * @return
     *     returns java.lang.String
     * @throws ClassNotFoundException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "enviarFeed", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.EnviarFeed")
    @ResponseWrapper(localName = "enviarFeedResponse", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.EnviarFeedResponse")
    @Action(input = "http://WS.Controlador/ParkPlatzWS/enviarFeedRequest", output = "http://WS.Controlador/ParkPlatzWS/enviarFeedResponse", fault = {
        @FaultAction(className = ClassNotFoundException_Exception.class, value = "http://WS.Controlador/ParkPlatzWS/enviarFeed/Fault/ClassNotFoundException")
    })
    public String enviarFeed(
        @WebParam(name = "Feedback", targetNamespace = "")
        Feedback feedback,
        @WebParam(name = "Estacionamiento", targetNamespace = "")
        Estacionamiento estacionamiento)
        throws ClassNotFoundException_Exception
    ;

    /**
     * 
     * @param txt
     * @return
     *     returns java.lang.String
     * @throws ClassNotFoundException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "toSHA256", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.ToSHA256")
    @ResponseWrapper(localName = "toSHA256Response", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.ToSHA256Response")
    @Action(input = "http://WS.Controlador/ParkPlatzWS/toSHA256Request", output = "http://WS.Controlador/ParkPlatzWS/toSHA256Response", fault = {
        @FaultAction(className = ClassNotFoundException_Exception.class, value = "http://WS.Controlador/ParkPlatzWS/toSHA256/Fault/ClassNotFoundException")
    })
    public String toSHA256(
        @WebParam(name = "txt", targetNamespace = "")
        String txt)
        throws ClassNotFoundException_Exception
    ;

    /**
     * 
     * @param fed
     * @param conductor
     * @return
     *     returns java.lang.String
     * @throws ClassNotFoundException_Exception
     */
    @WebMethod(operationName = "enviarFeed_dos")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "enviarFeed_dos", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.EnviarFeedDos")
    @ResponseWrapper(localName = "enviarFeed_dosResponse", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.EnviarFeedDosResponse")
    @Action(input = "http://WS.Controlador/ParkPlatzWS/enviarFeed_dosRequest", output = "http://WS.Controlador/ParkPlatzWS/enviarFeed_dosResponse", fault = {
        @FaultAction(className = ClassNotFoundException_Exception.class, value = "http://WS.Controlador/ParkPlatzWS/enviarFeed_dos/Fault/ClassNotFoundException")
    })
    public String enviarFeedDos(
        @WebParam(name = "fed", targetNamespace = "")
        Feedback fed,
        @WebParam(name = "conductor", targetNamespace = "")
        Conductor conductor)
        throws ClassNotFoundException_Exception
    ;

    /**
     * 
     * @param conductor
     * @return
     *     returns ParkPlatzWSC.Conductor
     * @throws ClassNotFoundException_Exception
     */
    @WebMethod(operationName = "get_misFavs")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "get_misFavs", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.GetMisFavs")
    @ResponseWrapper(localName = "get_misFavsResponse", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.GetMisFavsResponse")
    @Action(input = "http://WS.Controlador/ParkPlatzWS/get_misFavsRequest", output = "http://WS.Controlador/ParkPlatzWS/get_misFavsResponse", fault = {
        @FaultAction(className = ClassNotFoundException_Exception.class, value = "http://WS.Controlador/ParkPlatzWS/get_misFavs/Fault/ClassNotFoundException")
    })
    public Conductor getMisFavs(
        @WebParam(name = "conductor", targetNamespace = "")
        Conductor conductor)
        throws ClassNotFoundException_Exception
    ;

    /**
     * 
     * @param conductor
     * @return
     *     returns ParkPlatzWSC.Conductor
     * @throws ClassNotFoundException_Exception
     */
    @WebMethod(operationName = "get_misRecientes")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "get_misRecientes", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.GetMisRecientes")
    @ResponseWrapper(localName = "get_misRecientesResponse", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.GetMisRecientesResponse")
    @Action(input = "http://WS.Controlador/ParkPlatzWS/get_misRecientesRequest", output = "http://WS.Controlador/ParkPlatzWS/get_misRecientesResponse", fault = {
        @FaultAction(className = ClassNotFoundException_Exception.class, value = "http://WS.Controlador/ParkPlatzWS/get_misRecientes/Fault/ClassNotFoundException")
    })
    public Conductor getMisRecientes(
        @WebParam(name = "conductor", targetNamespace = "")
        Conductor conductor)
        throws ClassNotFoundException_Exception
    ;

    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "ssjjs", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.Ssjjs")
    @ResponseWrapper(localName = "ssjjsResponse", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.SsjjsResponse")
    @Action(input = "http://WS.Controlador/ParkPlatzWS/ssjjsRequest", output = "http://WS.Controlador/ParkPlatzWS/ssjjsResponse")
    public String ssjjs();

    /**
     * 
     * @param admin
     * @return
     *     returns ParkPlatzWSC.Administrador
     * @throws ClassNotFoundException_Exception
     */
    @WebMethod(operationName = "get_feedback")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "get_feedback", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.GetFeedback")
    @ResponseWrapper(localName = "get_feedbackResponse", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.GetFeedbackResponse")
    @Action(input = "http://WS.Controlador/ParkPlatzWS/get_feedbackRequest", output = "http://WS.Controlador/ParkPlatzWS/get_feedbackResponse", fault = {
        @FaultAction(className = ClassNotFoundException_Exception.class, value = "http://WS.Controlador/ParkPlatzWS/get_feedback/Fault/ClassNotFoundException")
    })
    public Administrador getFeedback(
        @WebParam(name = "admin", targetNamespace = "")
        Administrador admin)
        throws ClassNotFoundException_Exception
    ;

    /**
     * 
     * @param id
     * @return
     *     returns ParkPlatzWSC.Estacionamiento
     * @throws ClassNotFoundException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "recuperaEsta", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.RecuperaEsta")
    @ResponseWrapper(localName = "recuperaEstaResponse", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.RecuperaEstaResponse")
    @Action(input = "http://WS.Controlador/ParkPlatzWS/recuperaEstaRequest", output = "http://WS.Controlador/ParkPlatzWS/recuperaEstaResponse", fault = {
        @FaultAction(className = ClassNotFoundException_Exception.class, value = "http://WS.Controlador/ParkPlatzWS/recuperaEsta/Fault/ClassNotFoundException")
    })
    public Estacionamiento recuperaEsta(
        @WebParam(name = "id", targetNamespace = "")
        String id)
        throws ClassNotFoundException_Exception
    ;

    /**
     * 
     * @param id
     * @return
     *     returns java.lang.Boolean
     * @throws ClassNotFoundException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "borrameEsta", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.BorrameEsta")
    @ResponseWrapper(localName = "borrameEstaResponse", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.BorrameEstaResponse")
    @Action(input = "http://WS.Controlador/ParkPlatzWS/borrameEstaRequest", output = "http://WS.Controlador/ParkPlatzWS/borrameEstaResponse", fault = {
        @FaultAction(className = ClassNotFoundException_Exception.class, value = "http://WS.Controlador/ParkPlatzWS/borrameEsta/Fault/ClassNotFoundException")
    })
    public Boolean borrameEsta(
        @WebParam(name = "id", targetNamespace = "")
        String id)
        throws ClassNotFoundException_Exception
    ;

    /**
     * 
     * @param feed
     * @param admin
     * @return
     *     returns java.lang.String
     * @throws ClassNotFoundException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "enviarAviso", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.EnviarAviso")
    @ResponseWrapper(localName = "enviarAvisoResponse", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.EnviarAvisoResponse")
    @Action(input = "http://WS.Controlador/ParkPlatzWS/enviarAvisoRequest", output = "http://WS.Controlador/ParkPlatzWS/enviarAvisoResponse", fault = {
        @FaultAction(className = ClassNotFoundException_Exception.class, value = "http://WS.Controlador/ParkPlatzWS/enviarAviso/Fault/ClassNotFoundException")
    })
    public String enviarAviso(
        @WebParam(name = "feed", targetNamespace = "")
        Feedback feed,
        @WebParam(name = "admin", targetNamespace = "")
        Administrador admin)
        throws ClassNotFoundException_Exception
    ;

    /**
     * 
     * @param objetoLleno
     * @return
     *     returns boolean
     * @throws NoSuchAlgorithmException_Exception
     * @throws UnsupportedEncodingException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "registrarConductor", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.RegistrarConductor")
    @ResponseWrapper(localName = "registrarConductorResponse", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.RegistrarConductorResponse")
    @Action(input = "http://WS.Controlador/ParkPlatzWS/registrarConductorRequest", output = "http://WS.Controlador/ParkPlatzWS/registrarConductorResponse", fault = {
        @FaultAction(className = UnsupportedEncodingException_Exception.class, value = "http://WS.Controlador/ParkPlatzWS/registrarConductor/Fault/UnsupportedEncodingException"),
        @FaultAction(className = NoSuchAlgorithmException_Exception.class, value = "http://WS.Controlador/ParkPlatzWS/registrarConductor/Fault/NoSuchAlgorithmException")
    })
    public boolean registrarConductor(
        @WebParam(name = "objetoLleno", targetNamespace = "")
        Conductor objetoLleno)
        throws NoSuchAlgorithmException_Exception, UnsupportedEncodingException_Exception
    ;

    /**
     * 
     * @param objetoLleno
     * @return
     *     returns boolean
     * @throws NoSuchAlgorithmException_Exception
     * @throws UnsupportedEncodingException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "registrarEstacionamiento", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.RegistrarEstacionamiento")
    @ResponseWrapper(localName = "registrarEstacionamientoResponse", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.RegistrarEstacionamientoResponse")
    @Action(input = "http://WS.Controlador/ParkPlatzWS/registrarEstacionamientoRequest", output = "http://WS.Controlador/ParkPlatzWS/registrarEstacionamientoResponse", fault = {
        @FaultAction(className = UnsupportedEncodingException_Exception.class, value = "http://WS.Controlador/ParkPlatzWS/registrarEstacionamiento/Fault/UnsupportedEncodingException"),
        @FaultAction(className = NoSuchAlgorithmException_Exception.class, value = "http://WS.Controlador/ParkPlatzWS/registrarEstacionamiento/Fault/NoSuchAlgorithmException")
    })
    public boolean registrarEstacionamiento(
        @WebParam(name = "objetoLleno", targetNamespace = "")
        Estacionamiento objetoLleno)
        throws NoSuchAlgorithmException_Exception, UnsupportedEncodingException_Exception
    ;

    /**
     * 
     * @param objetoLleno
     * @return
     *     returns boolean
     * @throws NoSuchAlgorithmException_Exception
     * @throws UnsupportedEncodingException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "registrarAdministrador", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.RegistrarAdministrador")
    @ResponseWrapper(localName = "registrarAdministradorResponse", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.RegistrarAdministradorResponse")
    @Action(input = "http://WS.Controlador/ParkPlatzWS/registrarAdministradorRequest", output = "http://WS.Controlador/ParkPlatzWS/registrarAdministradorResponse", fault = {
        @FaultAction(className = UnsupportedEncodingException_Exception.class, value = "http://WS.Controlador/ParkPlatzWS/registrarAdministrador/Fault/UnsupportedEncodingException"),
        @FaultAction(className = NoSuchAlgorithmException_Exception.class, value = "http://WS.Controlador/ParkPlatzWS/registrarAdministrador/Fault/NoSuchAlgorithmException")
    })
    public boolean registrarAdministrador(
        @WebParam(name = "objetoLleno", targetNamespace = "")
        Administrador objetoLleno)
        throws NoSuchAlgorithmException_Exception, UnsupportedEncodingException_Exception
    ;

    /**
     * 
     * @param objetoConUserPass
     * @return
     *     returns ParkPlatzWSC.Estacionamiento
     * @throws NoSuchAlgorithmException_Exception
     * @throws UnsupportedEncodingException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "loginEstacionamiento", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.LoginEstacionamiento")
    @ResponseWrapper(localName = "loginEstacionamientoResponse", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.LoginEstacionamientoResponse")
    @Action(input = "http://WS.Controlador/ParkPlatzWS/loginEstacionamientoRequest", output = "http://WS.Controlador/ParkPlatzWS/loginEstacionamientoResponse", fault = {
        @FaultAction(className = UnsupportedEncodingException_Exception.class, value = "http://WS.Controlador/ParkPlatzWS/loginEstacionamiento/Fault/UnsupportedEncodingException"),
        @FaultAction(className = NoSuchAlgorithmException_Exception.class, value = "http://WS.Controlador/ParkPlatzWS/loginEstacionamiento/Fault/NoSuchAlgorithmException")
    })
    public Estacionamiento loginEstacionamiento(
        @WebParam(name = "objetoConUserPass", targetNamespace = "")
        Estacionamiento objetoConUserPass)
        throws NoSuchAlgorithmException_Exception, UnsupportedEncodingException_Exception
    ;

    /**
     * 
     * @param objetoConUserPass
     * @return
     *     returns ParkPlatzWSC.Administrador
     * @throws NoSuchAlgorithmException_Exception
     * @throws UnsupportedEncodingException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "loginAdministrador", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.LoginAdministrador")
    @ResponseWrapper(localName = "loginAdministradorResponse", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.LoginAdministradorResponse")
    @Action(input = "http://WS.Controlador/ParkPlatzWS/loginAdministradorRequest", output = "http://WS.Controlador/ParkPlatzWS/loginAdministradorResponse", fault = {
        @FaultAction(className = UnsupportedEncodingException_Exception.class, value = "http://WS.Controlador/ParkPlatzWS/loginAdministrador/Fault/UnsupportedEncodingException"),
        @FaultAction(className = NoSuchAlgorithmException_Exception.class, value = "http://WS.Controlador/ParkPlatzWS/loginAdministrador/Fault/NoSuchAlgorithmException")
    })
    public Administrador loginAdministrador(
        @WebParam(name = "objetoConUserPass", targetNamespace = "")
        Administrador objetoConUserPass)
        throws NoSuchAlgorithmException_Exception, UnsupportedEncodingException_Exception
    ;

    /**
     * 
     * @param objetoEstacionamiento
     */
    @WebMethod
    @RequestWrapper(localName = "logoutEstacionamiento", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.LogoutEstacionamiento")
    @ResponseWrapper(localName = "logoutEstacionamientoResponse", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.LogoutEstacionamientoResponse")
    @Action(input = "http://WS.Controlador/ParkPlatzWS/logoutEstacionamientoRequest", output = "http://WS.Controlador/ParkPlatzWS/logoutEstacionamientoResponse")
    public void logoutEstacionamiento(
        @WebParam(name = "objetoEstacionamiento", targetNamespace = "")
        Estacionamiento objetoEstacionamiento);

    /**
     * 
     * @param objetoAdministrador
     */
    @WebMethod
    @RequestWrapper(localName = "logoutAdministrador", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.LogoutAdministrador")
    @ResponseWrapper(localName = "logoutAdministradorResponse", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.LogoutAdministradorResponse")
    @Action(input = "http://WS.Controlador/ParkPlatzWS/logoutAdministradorRequest", output = "http://WS.Controlador/ParkPlatzWS/logoutAdministradorResponse")
    public void logoutAdministrador(
        @WebParam(name = "objetoAdministrador", targetNamespace = "")
        Administrador objetoAdministrador);

    /**
     * 
     * @param nombreServ
     * @param descServ
     * @param idEsta
     * @param costServ
     * @return
     *     returns java.lang.String
     * @throws ClassNotFoundException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "nuevoServicioOferta", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.NuevoServicioOferta")
    @ResponseWrapper(localName = "nuevoServicioOfertaResponse", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.NuevoServicioOfertaResponse")
    @Action(input = "http://WS.Controlador/ParkPlatzWS/nuevoServicioOfertaRequest", output = "http://WS.Controlador/ParkPlatzWS/nuevoServicioOfertaResponse", fault = {
        @FaultAction(className = ClassNotFoundException_Exception.class, value = "http://WS.Controlador/ParkPlatzWS/nuevoServicioOferta/Fault/ClassNotFoundException")
    })
    public String nuevoServicioOferta(
        @WebParam(name = "nombreServ", targetNamespace = "")
        String nombreServ,
        @WebParam(name = "descServ", targetNamespace = "")
        String descServ,
        @WebParam(name = "costServ", targetNamespace = "")
        Float costServ,
        @WebParam(name = "idEsta", targetNamespace = "")
        int idEsta)
        throws ClassNotFoundException_Exception
    ;

    /**
     * 
     * @param nuevoCosto
     * @param nuevaDescripcion
     * @param idServicio
     * @param nuevoNombre
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "actualizarServicio", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.ActualizarServicio")
    @ResponseWrapper(localName = "actualizarServicioResponse", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.ActualizarServicioResponse")
    @Action(input = "http://WS.Controlador/ParkPlatzWS/actualizarServicioRequest", output = "http://WS.Controlador/ParkPlatzWS/actualizarServicioResponse")
    public String actualizarServicio(
        @WebParam(name = "idServicio", targetNamespace = "")
        int idServicio,
        @WebParam(name = "nuevoNombre", targetNamespace = "")
        String nuevoNombre,
        @WebParam(name = "nuevaDescripcion", targetNamespace = "")
        String nuevaDescripcion,
        @WebParam(name = "nuevoCosto", targetNamespace = "")
        float nuevoCosto);

    /**
     * 
     * @param objetoDatosActualizados
     * @return
     *     returns ParkPlatzWSC.Estacionamiento
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "actualizarDatosEsta", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.ActualizarDatosEsta")
    @ResponseWrapper(localName = "actualizarDatosEstaResponse", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.ActualizarDatosEstaResponse")
    @Action(input = "http://WS.Controlador/ParkPlatzWS/actualizarDatosEstaRequest", output = "http://WS.Controlador/ParkPlatzWS/actualizarDatosEstaResponse")
    public Estacionamiento actualizarDatosEsta(
        @WebParam(name = "objetoDatosActualizados", targetNamespace = "")
        Estacionamiento objetoDatosActualizados);

    /**
     * 
     * @param nombreArchivo
     * @param objetoUsuarioDeFoto
     * @param archivoByte
     * @return
     *     returns java.lang.String
     * @throws ClassNotFoundException_Exception
     * @throws IOException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "recibeFoto", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.RecibeFoto")
    @ResponseWrapper(localName = "recibeFotoResponse", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.RecibeFotoResponse")
    @Action(input = "http://WS.Controlador/ParkPlatzWS/recibeFotoRequest", output = "http://WS.Controlador/ParkPlatzWS/recibeFotoResponse", fault = {
        @FaultAction(className = ClassNotFoundException_Exception.class, value = "http://WS.Controlador/ParkPlatzWS/recibeFoto/Fault/ClassNotFoundException"),
        @FaultAction(className = IOException_Exception.class, value = "http://WS.Controlador/ParkPlatzWS/recibeFoto/Fault/IOException")
    })
    public String recibeFoto(
        @WebParam(name = "archivoByte", targetNamespace = "")
        byte[] archivoByte,
        @WebParam(name = "nombreArchivo", targetNamespace = "")
        String nombreArchivo,
        @WebParam(name = "objetoUsuarioDeFoto", targetNamespace = "")
        Estacionamiento objetoUsuarioDeFoto)
        throws ClassNotFoundException_Exception, IOException_Exception
    ;

    /**
     * 
     * @param objetoConDatosNvoEst
     * @return
     *     returns boolean
     * @throws ClassNotFoundException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "registraUnEstacionamiento", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.RegistraUnEstacionamiento")
    @ResponseWrapper(localName = "registraUnEstacionamientoResponse", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.RegistraUnEstacionamientoResponse")
    @Action(input = "http://WS.Controlador/ParkPlatzWS/registraUnEstacionamientoRequest", output = "http://WS.Controlador/ParkPlatzWS/registraUnEstacionamientoResponse", fault = {
        @FaultAction(className = ClassNotFoundException_Exception.class, value = "http://WS.Controlador/ParkPlatzWS/registraUnEstacionamiento/Fault/ClassNotFoundException")
    })
    public boolean registraUnEstacionamiento(
        @WebParam(name = "ObjetoConDatosNvoEst", targetNamespace = "")
        Estacionamiento objetoConDatosNvoEst)
        throws ClassNotFoundException_Exception
    ;

    /**
     * 
     * @param objetoEst
     * @return
     *     returns ParkPlatzWSC.Estacionamiento
     * @throws ClassNotFoundException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "obtenerMisEstacionamientos", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.ObtenerMisEstacionamientos")
    @ResponseWrapper(localName = "obtenerMisEstacionamientosResponse", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.ObtenerMisEstacionamientosResponse")
    @Action(input = "http://WS.Controlador/ParkPlatzWS/obtenerMisEstacionamientosRequest", output = "http://WS.Controlador/ParkPlatzWS/obtenerMisEstacionamientosResponse", fault = {
        @FaultAction(className = ClassNotFoundException_Exception.class, value = "http://WS.Controlador/ParkPlatzWS/obtenerMisEstacionamientos/Fault/ClassNotFoundException")
    })
    public Estacionamiento obtenerMisEstacionamientos(
        @WebParam(name = "objetoEst", targetNamespace = "")
        Estacionamiento objetoEst)
        throws ClassNotFoundException_Exception
    ;

    /**
     * 
     * @param obj
     * @return
     *     returns ParkPlatzWSC.Conductor
     * @throws NoSuchAlgorithmException_Exception
     * @throws UnsupportedEncodingException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "actualizarDatosConductor", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.ActualizarDatosConductor")
    @ResponseWrapper(localName = "actualizarDatosConductorResponse", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.ActualizarDatosConductorResponse")
    @Action(input = "http://WS.Controlador/ParkPlatzWS/actualizarDatosConductorRequest", output = "http://WS.Controlador/ParkPlatzWS/actualizarDatosConductorResponse", fault = {
        @FaultAction(className = UnsupportedEncodingException_Exception.class, value = "http://WS.Controlador/ParkPlatzWS/actualizarDatosConductor/Fault/UnsupportedEncodingException"),
        @FaultAction(className = NoSuchAlgorithmException_Exception.class, value = "http://WS.Controlador/ParkPlatzWS/actualizarDatosConductor/Fault/NoSuchAlgorithmException")
    })
    public Conductor actualizarDatosConductor(
        @WebParam(name = "obj", targetNamespace = "")
        Conductor obj)
        throws NoSuchAlgorithmException_Exception, UnsupportedEncodingException_Exception
    ;

    /**
     * 
     * @param obj
     * @return
     *     returns ParkPlatzWSC.Administrador
     * @throws NoSuchAlgorithmException_Exception
     * @throws UnsupportedEncodingException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "actualizarDatosAdmin", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.ActualizarDatosAdmin")
    @ResponseWrapper(localName = "actualizarDatosAdminResponse", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.ActualizarDatosAdminResponse")
    @Action(input = "http://WS.Controlador/ParkPlatzWS/actualizarDatosAdminRequest", output = "http://WS.Controlador/ParkPlatzWS/actualizarDatosAdminResponse", fault = {
        @FaultAction(className = UnsupportedEncodingException_Exception.class, value = "http://WS.Controlador/ParkPlatzWS/actualizarDatosAdmin/Fault/UnsupportedEncodingException"),
        @FaultAction(className = NoSuchAlgorithmException_Exception.class, value = "http://WS.Controlador/ParkPlatzWS/actualizarDatosAdmin/Fault/NoSuchAlgorithmException")
    })
    public Administrador actualizarDatosAdmin(
        @WebParam(name = "obj", targetNamespace = "")
        Administrador obj)
        throws NoSuchAlgorithmException_Exception, UnsupportedEncodingException_Exception
    ;

    /**
     * 
     * @param objetoConIdaRecuperar
     * @return
     *     returns ParkPlatzWSC.Estacionamiento
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "recuperarDatosEsta", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.RecuperarDatosEsta")
    @ResponseWrapper(localName = "recuperarDatosEstaResponse", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.RecuperarDatosEstaResponse")
    @Action(input = "http://WS.Controlador/ParkPlatzWS/recuperarDatosEstaRequest", output = "http://WS.Controlador/ParkPlatzWS/recuperarDatosEstaResponse")
    public Estacionamiento recuperarDatosEsta(
        @WebParam(name = "objetoConIdaRecuperar", targetNamespace = "")
        Estacionamiento objetoConIdaRecuperar);

    /**
     * 
     * @param mensaje
     * @param autor
     * @throws ClassNotFoundException_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "enviarMensajeChat", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.EnviarMensajeChat")
    @ResponseWrapper(localName = "enviarMensajeChatResponse", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.EnviarMensajeChatResponse")
    @Action(input = "http://WS.Controlador/ParkPlatzWS/enviarMensajeChatRequest", output = "http://WS.Controlador/ParkPlatzWS/enviarMensajeChatResponse", fault = {
        @FaultAction(className = ClassNotFoundException_Exception.class, value = "http://WS.Controlador/ParkPlatzWS/enviarMensajeChat/Fault/ClassNotFoundException")
    })
    public void enviarMensajeChat(
        @WebParam(name = "autor", targetNamespace = "")
        String autor,
        @WebParam(name = "mensaje", targetNamespace = "")
        String mensaje)
        throws ClassNotFoundException_Exception
    ;

    /**
     * 
     * @return
     *     returns java.util.List<java.lang.String>
     * @throws ClassNotFoundException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "recibirMensajeChat", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.RecibirMensajeChat")
    @ResponseWrapper(localName = "recibirMensajeChatResponse", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.RecibirMensajeChatResponse")
    @Action(input = "http://WS.Controlador/ParkPlatzWS/recibirMensajeChatRequest", output = "http://WS.Controlador/ParkPlatzWS/recibirMensajeChatResponse", fault = {
        @FaultAction(className = ClassNotFoundException_Exception.class, value = "http://WS.Controlador/ParkPlatzWS/recibirMensajeChat/Fault/ClassNotFoundException")
    })
    public List<String> recibirMensajeChat()
        throws ClassNotFoundException_Exception
    ;

    /**
     * 
     * @return
     *     returns java.lang.String
     * @throws ClassNotFoundException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "recibirUltimoChat", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.RecibirUltimoChat")
    @ResponseWrapper(localName = "recibirUltimoChatResponse", targetNamespace = "http://WS.Controlador/", className = "ParkPlatzWSC.RecibirUltimoChatResponse")
    @Action(input = "http://WS.Controlador/ParkPlatzWS/recibirUltimoChatRequest", output = "http://WS.Controlador/ParkPlatzWS/recibirUltimoChatResponse", fault = {
        @FaultAction(className = ClassNotFoundException_Exception.class, value = "http://WS.Controlador/ParkPlatzWS/recibirUltimoChat/Fault/ClassNotFoundException")
    })
    public String recibirUltimoChat()
        throws ClassNotFoundException_Exception
    ;

}
