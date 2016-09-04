/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.ovrwrite.parkplatz.web.controller.ws;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.servlet.ServletContext;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import me.ovrwrite.parkplatz.web.controller.Conversacion;
import me.ovrwrite.parkplatz.web.controller.Email;
import me.ovrwrite.parkplatz.web.controller.Seguridad;
import me.ovrwrite.parkplatz.web.controller.Servicios;
import me.ovrwrite.parkplatz.web.controller.SoporteRepository;
import me.ovrwrite.parkplatz.web.controller.beans.Administrador;
import me.ovrwrite.parkplatz.web.controller.beans.Conductor;
import me.ovrwrite.parkplatz.web.controller.beans.Esquema;
import me.ovrwrite.parkplatz.web.controller.beans.Estacionamiento;
import me.ovrwrite.parkplatz.web.controller.beans.Favoritos;
import me.ovrwrite.parkplatz.web.controller.beans.Feedback;
import me.ovrwrite.parkplatz.web.controller.beans.Recientes;

/**
 *
 * @author Dell
 */
@WebService(serviceName = "ParkPlatzWS")
public class ParkPlatzWS {

     @Resource
    private WebServiceContext context ;
   
    /**
     * This is a sample web service operation 
     */
    public ParkPlatzWS(){
        
    }
    @WebMethod(operationName = "registrarConductor")
    public boolean registrarConductor(@WebParam(name = "objetoLleno") Conductor objetoLleno) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Seguridad seg = new Seguridad();
        seg.setKey(Seguridad.genKey(objetoLleno.getCorreo()));
        objetoLleno.setPass(seg.encriptar(objetoLleno.getPass()));
        return objetoLleno.registrar();
    }
    
    @WebMethod(operationName = "registrarEstacionamiento")
    public boolean registrarEstacionamiento(@WebParam(name = "objetoLleno") Estacionamiento objetoLleno) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Seguridad seg = new Seguridad();
        seg.setKey(Seguridad.genKey(objetoLleno.getCorreo()));
        objetoLleno.setPass(seg.encriptar(objetoLleno.getPass()));
        return objetoLleno.registrar();
    }
    @WebMethod(operationName = "registrarAdministrador")
    public boolean registrarAdministrador(@WebParam(name = "objetoLleno") Administrador objetoLleno) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Seguridad seg = new Seguridad();
        seg.setKey(Seguridad.genKey(objetoLleno.getCorreo()));
        objetoLleno.setPass(seg.encriptar(objetoLleno.getPass()));
        return objetoLleno.registrar();
    }
    @WebMethod(operationName = "loginConductor")
    public Conductor loginConductor(@WebParam(name = "objetoConUserPass") Conductor objetoConUserPass) throws UnsupportedEncodingException, NoSuchAlgorithmException{
        Seguridad seg = new Seguridad();
        seg.setKey(Seguridad.genKey(objetoConUserPass.getCorreo()));
        objetoConUserPass.setPass(seg.encriptar(objetoConUserPass.getPass()));
        objetoConUserPass.iniciarSesion();
        return objetoConUserPass;
    }
    @WebMethod(operationName = "loginEstacionamiento")
    public Estacionamiento loginEstacionamiento(@WebParam(name = "objetoConUserPass") Estacionamiento objetoConUserPass) throws UnsupportedEncodingException, NoSuchAlgorithmException{
        
        Seguridad seg = new Seguridad();
        seg.setKey(Seguridad.genKey(objetoConUserPass.getCorreo()));
        objetoConUserPass.setPass(seg.encriptar(objetoConUserPass.getPass()));
        objetoConUserPass.iniciarSesion();
        return objetoConUserPass;
    }
    
    @WebMethod(operationName = "loginAdministrador")
    public Administrador loginAdministrador(@WebParam(name = "objetoConUserPass") Administrador objetoConUserPass) throws UnsupportedEncodingException, NoSuchAlgorithmException{
        Seguridad seg = new Seguridad();
        seg.setKey(Seguridad.genKey(objetoConUserPass.getCorreo()));
        objetoConUserPass.setPass(seg.encriptar(objetoConUserPass.getPass()));
        objetoConUserPass.iniciarSesion();
        return objetoConUserPass;
    }
    @WebMethod(operationName = "logoutConductor")
    public void logoutConductor(@WebParam(name = "objetoConductor") Conductor co){
        co.cerrarSesion();
    }
    @WebMethod(operationName = "logoutEstacionamiento")
    public void logoutEstacionamiento(@WebParam(name = "objetoEstacionamiento") Estacionamiento est){
        est.cerrarSesion();
    }
    @WebMethod(operationName = "logoutAdministrador")
    public void logoutAdministrador(@WebParam(name = "objetoAdministrador") Administrador admin){
        admin.cerrarSesion();
    }
    @WebMethod(operationName = "nuevoServicioOferta")
    public String nuevoServicioOferta(@WebParam(name = "nombreServ") String nombreServ, @WebParam(name = "descServ") String descServ, @WebParam(name="costServ") Float cost, @WebParam(name="idEsta")int idEsta) throws ClassNotFoundException
    {
        return Servicios.nuevoServicioOferta(nombreServ,descServ,cost,idEsta);
    }
    @WebMethod(operationName = "obtenerSevicios")
    public ArrayList<Servicios> obtenerServicios(@WebParam(name = "idEstacionamiento") int idEstacionamiento)
    {
        ArrayList<Servicios> servs = new <Servicios>ArrayList();
        try{
        servs = Servicios.obtenerServicios(idEstacionamiento);
        }catch(SQLException | ClassNotFoundException e){
            System.out.println("Error susitado aqui: "+e.getClass().getName()+" y fue: "+e.getMessage());
        }
        return servs;
    }
    @WebMethod(operationName = "eliminarServ")
    public String eliminarServ(@WebParam(name = "idServicio") int idServicio)
    {
        String estado = "";
        try{
        estado = Servicios.eliminarServicio(idServicio);
        }catch(Exception e){
            System.out.println("Error susitado aqui: "+e.getClass().getName()+" y fue: "+e.getMessage());
        }
        return estado;
    }
    
    @WebMethod(operationName = "actualizarServicio")
    public String actualizarServicio(@WebParam(name = "idServicio") int idServicio, @WebParam(name = "nuevoNombre") String nuevoNombre, @WebParam(name = "nuevaDescripcion") String nuevaDescripcion, @WebParam(name = "nuevoCosto") float nuevoCosto)
    {
        String estado = "";
        try{
        estado = Servicios.actualizar(idServicio, nuevoNombre, nuevaDescripcion, nuevoCosto);
        }catch(Exception e){
            System.out.println("Error susitado aqui: "+e.getClass().getName()+" y fue: "+e.getMessage());
        }
        return estado;
    }
    @WebMethod(operationName = "actualizarDatos")
    public Estacionamiento actualizarDatos(@WebParam(name = "objetoConDatosNuevos")Estacionamiento objConDatosActualizados) throws UnsupportedEncodingException, NoSuchAlgorithmException{
        if(!objConDatosActualizados.getPass().equals("sinCambios")){
           Seguridad seg = new Seguridad();
           seg.setKey(Seguridad.genKey(objConDatosActualizados.getCorreo()));
           objConDatosActualizados.setPass(seg.encriptar(objConDatosActualizados.getPass()));
        }
        objConDatosActualizados.actualizarDatos();
           
        
        return objConDatosActualizados;
    }
    
    @WebMethod(operationName = "actualizarDatosEsta")
    public Estacionamiento actualizarDatosEsta(@WebParam(name= "objetoDatosActualizados")Estacionamiento objetoActualizado){
        objetoActualizado.actualizarDatosEsta();
        return objetoActualizado;
    }
   
    @WebMethod(operationName = "recibeFoto")
    public String recibeFotoEstacionamiento(@WebParam(name = "archivoByte")byte[] archivoByte,@WebParam(name= "nombreArchivo")String nombreAr, @WebParam(name = "objetoUsuarioDeFoto") Estacionamiento est) throws ClassNotFoundException, IOException{
        String estado = "";
       FileOutputStream out = null;
        try{
             ServletContext servletContext = (ServletContext) context.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
    
            File dir = new File(servletContext.getRealPath("/")+"Imagenes/"+est.getCorreo()+"/"+est.getIdEstacionamiento()+"/");
            dir.mkdirs();
            File localFile = new File(dir+"/"+nombreAr);
            out = new FileOutputStream(localFile);
            out.write(archivoByte);
            System.out.println(localFile.getCanonicalPath());
            est.setUrlImg(localFile.getCanonicalPath());
            est.subirFoto();
            return est.getUrlImg();
        } catch (IOException ex) {
            Logger.getLogger(ParkPlatzWS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
             Logger.getLogger(ParkPlatzWS.class.getName()).log(Level.SEVERE, null, ex);
         }finally{
            try{
                if(out != null)
                    out.close();
                
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        return estado;
    }
    @WebMethod(operationName = "enviarFeed")
    public String enviarFeed(@WebParam(name= "Feedback")Feedback fb,@WebParam(name="Estacionamiento") Estacionamiento est) throws ClassNotFoundException{
        String estado = "";
        
        boolean exito = est.enviarFeed(fb);
        if(exito == true){
            estado = "Se ha enviado correctamente";
        }
        return estado;
    }
    
    @WebMethod(operationName = "registraUnEstacionamiento")
    public boolean registraUnEstacionamiento(@WebParam(name = "ObjetoConDatosNvoEst") Estacionamiento objetoEst) throws ClassNotFoundException{
        boolean exito = false;
        try{
       
        exito = objetoEst.registrarOtro();
        System.out.println("paso desde web"+exito);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return exito;
    }
    @WebMethod(operationName = "obtenerMisEstacionamientos")
    public Estacionamiento obtenerMisEstacionamientos(@WebParam(name = "objetoEst") Estacionamiento objetoEst) throws ClassNotFoundException{
        objetoEst.setMisEstacionamientos(objetoEst.obtenerEstacionamientos());
        return objetoEst; 
    }
    @WebMethod(operationName = "toSHA256")
    public String toSHA256(@WebParam(name = "txt") String txt) throws ClassNotFoundException{
        return Seguridad.toSHA256(txt); 
        
    }
    @WebMethod(operationName = "recover")
    public boolean recover(@WebParam(name = "correo")String correo){
        boolean exito;
        try{
            Email em = new Email(correo);
            exito = em.enviar(em.MENSAJE_RECUPERA_CONTRA);
        }catch(Exception e){
            System.out.println(e.getMessage());
            exito = false;
        }
        return exito;
    }
    /**
     * Web service operation
     * @param obj
     * @return 
     * @throws java.io.UnsupportedEncodingException
     * @throws java.security.NoSuchAlgorithmException
     */
    @WebMethod(operationName = "actualizarDatosConductor")
    public Conductor actualizarDatosConductor(@WebParam(name = "obj") Conductor obj) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        if(obj.getPass() != null){
           Seguridad seg = new Seguridad();         
           seg.setKey(Seguridad.genKey(obj.getCorreo()));
           obj.setPass(seg.encriptar(obj.getPass()));
        }
        obj.actualizarDatos();      
        return obj;
    }

    /**
     * Web service operation
     * @param fed
     * @param conductor
     * @return 
     * @throws java.lang.ClassNotFoundException 
     */
    @WebMethod(operationName = "enviarFeed_dos")
    public String enviarFeed_dos(@WebParam(name = "fed") Feedback fed, @WebParam(name = "conductor") Conductor conductor) throws ClassNotFoundException {
        String estado = "";
        
        boolean exito = conductor.enviarFeed(fed);
        if(exito == true){
            estado = "Se ha enviado correctamente";
        }
        return estado;
    }

    /**
     * Web service operation
     * @param conductor
     * @return 
     * @throws java.lang.ClassNotFoundException
     */
    @WebMethod(operationName = "get_misFavs")
    public Conductor get_misFavs(@WebParam(name = "conductor") Conductor conductor) throws ClassNotFoundException {
        conductor.misFavs();
        return conductor;
    }

    /**
     * Web service operation
     * @param conductor
     * @return 
     * @throws java.lang.ClassNotFoundException 
     */
    @WebMethod(operationName = "get_misRecientes")
    public Conductor get_misRecientes(@WebParam(name = "conductor") Conductor conductor) throws ClassNotFoundException {
        conductor.misRecientes();
        return conductor;
    }

    /**
     * Web service operation
     * @param obj
     * @return 
     * @throws java.io.UnsupportedEncodingException
     * @throws java.security.NoSuchAlgorithmException
     */
    @WebMethod(operationName = "actualizarDatosAdmin")
    public Administrador actualizarDatosAdmin(@WebParam(name = "obj") Administrador obj) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        if(obj.getPass() != null){
           Seguridad seg = new Seguridad();         
           seg.setKey(Seguridad.genKey(obj.getCorreo()));
           obj.setPass(seg.encriptar(obj.getPass()));
        }
        obj.actualizarDatos();      
        return obj;
    }

    /**
     * Web service operation
     * @param admin
     * @return 
     * @throws java.lang.ClassNotFoundException 
     */
    @WebMethod(operationName = "get_feedback")
    public Administrador get_feedback(@WebParam(name = "admin") Administrador admin) throws ClassNotFoundException {
        admin.traerFeedback();
        return admin;
    }

    /**
     * Web service operation
     * @param nombre
     * @return 
     * @throws java.lang.ClassNotFoundException 
     */
    @WebMethod(operationName = "recuperaEsta")
    public ArrayList<Estacionamiento> recuperaEsta(@WebParam(name = "id") String nombre) throws ClassNotFoundException {
        Administrador admin = new Administrador();
        
        ArrayList<Estacionamiento> estas = admin.recuperaEstacionamiento(nombre);
        
        return estas;
    }

        @WebMethod(operationName = "recuperarDatosEsta")
    public Estacionamiento recuperarDatosEsta(@WebParam(name= "objetoConIdaRecuperar")Estacionamiento objetoConId){
        objetoConId.recuperarDatos();
        return objetoConId;
    }
    /**
     * Web service operation
     * @param est
     * @return 
     * @throws java.lang.ClassNotFoundException
     */
    @WebMethod(operationName = "borrameEsta")
    public String borrameEsta(@WebParam(name = "EstaToDel") Estacionamiento est) throws ClassNotFoundException {
        String status;
        try{
            System.out.println("desde ws" + est.getIdEstacionamiento());
        status = est.eliminarEstacionamiento();
       }catch(Exception e){
            System.out.println(e.getMessage());
           status = "Ha ocurrido un error en el WebService!";
       }
        return status;
    }

    /**
     * Web service operation
     * @param feed
     * @param admin
     * @return 
     * @throws java.lang.ClassNotFoundException 
     */
    @WebMethod(operationName = "enviarAviso")
    public String enviarAviso(@WebParam(name = "feed") Feedback feed, @WebParam(name = "admin") Administrador admin) throws ClassNotFoundException {
        String estado = "";
        boolean exito = admin.enviarAviso(feed);
        if(exito == true){
            estado = "Se ha enviado correctamente";
        }
        return estado;
    }
    @WebMethod(operationName = "avisos")
    public ArrayList<Feedback> avisos(@WebParam(name = "EstacionamientoActual" )Estacionamiento est)
    {
        return est.recuperarAvisos();
    }
    @WebMethod(operationName = "getEsquema")
    public Esquema getEsquema(@WebParam(name = "Estacionamiento")Estacionamiento est){
        Esquema objetoEsquema = null;
        try {
            ServletContext servletContext = (ServletContext) context.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
             
             objetoEsquema =  Esquema.getEsquema(servletContext.getRealPath("/")+"Esquemas/"+est.getCorreo()+"/"+est.getIdEstacionamiento()+"/");
         } catch (IOException ex) {
             Logger.getLogger(ParkPlatzWS.class.getName()).log(Level.SEVERE, null, ex);
         }
        return objetoEsquema;
    }
    @WebMethod(operationName = "saveEsquema")
    public String saveEsquema(@WebParam(name = "Estacionamiento")Estacionamiento est,  @WebParam(name = "Esquema")Esquema esq){
        String status = "";
        try {
            ServletContext servletContext = (ServletContext) context.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
              status =  Esquema.saveEsquema(servletContext.getRealPath("/")+"Esquemas/"+est.getCorreo()+"/"+est.getIdEstacionamiento()+"/", esq);
         } catch (Exception ex) {
             Logger.getLogger(ParkPlatzWS.class.getName()).log(Level.SEVERE, null, ex);
         }
        return status;
    }
    
    @WebMethod(operationName = "delEsquema")
    public String delEsquema(@WebParam(name = "delEsquema")Estacionamiento est){
       String status;
        try{
        ServletContext servletContext = (ServletContext) context.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
        status = Esquema.delEsquema(servletContext.getRealPath("/")+"Esquemas/"+est.getCorreo()+"/"+est.getIdEstacionamiento()+"/");
       }catch(Exception e){
           status = "Ha ocurrido un error en el WebService!";
       }
        return status;
    }
    
    
    @WebMethod(operationName = "delUser")
    public String delUser(@WebParam(name = "correo")String correo){
       String status;
        try{
            Estacionamiento est = new Estacionamiento();
            status = est.delUser(correo);
        }catch(Exception e){
           status = "Ha ocurrido un error en el WebService; no se ha podido eliminar su cuenta!";
       }
        return status;
    }
    
    
    @WebMethod(operationName = "traeIdEstacionamiento")
    public ArrayList<Integer> traeIdEstacionamiento(@WebParam(name = "correo")String correo){
       ArrayList<Integer> ids = new ArrayList<>();
        try{
            
            ids = Estacionamiento.traeIdsEstacionamiento(correo);
        }catch(Exception e){
            System.out.println(e.getMessage());
       }
        return ids;
    }
    
    /**
     * @return
     */
    @WebMethod(operationName = "traeSuppLogs")
    public ArrayList<Conversacion> traeSuppLogs(){
       SoporteRepository supp = new SoporteRepository();
       ArrayList<Conversacion> conversaciones = new ArrayList<>();
        ServletContext servletContext = (ServletContext) context.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
        try{
            supp = supp.load(servletContext.getRealPath("/")+"SoporteLogs/");
            conversaciones = supp.getConversaciones();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return conversaciones;
    }
    
    @WebMethod(operationName = "guardaSuppLogs")
    public SoporteRepository guardaSuppLogs(@WebParam(name = "arrayConversacion")ArrayList<Conversacion> convers){
       SoporteRepository supp = new SoporteRepository();
        ServletContext servletContext = (ServletContext) context.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
        try{
            supp.setConversaciones(convers);
            supp.save(servletContext.getRealPath("/")+"SoporteLogs/");
            
        }catch(Exception e){
           System.out.println(e.getMessage());
           
       }
        return supp;
    }
    
    /**
     * Web service operation
     * @param correo
     * @param pass
     * @return 
     * @throws java.io.UnsupportedEncodingException 
     * @throws java.security.NoSuchAlgorithmException 
     */
    @WebMethod(operationName = "operacion_Login_Android")
    public String Operacion_Login_Android(@WebParam(name = "correo") String correo, @WebParam(name = "pass") String pass) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String cadena = "0";
        Seguridad seg = new Seguridad();
        Conductor conduc = new Conductor();
        conduc.setCorreo(correo);
        seg.setKey(Seguridad.genKey(conduc.getCorreo()));
        conduc.setPass(seg.encriptar(pass));
        conduc.iniciarSesion();
        if(conduc.isLogueado()){
            cadena = conduc.getNombre() + "," + conduc.getaPaterno() + "," + 
                    conduc.getaMaterno() + "," + conduc.getCorreo();
        }   
        return cadena;
    }

    /**
     * Web service operation
     * @param nombre
     * @param aPaterno
     * @param aMaterno
     * @param correo
     * @param pass
     * @return 
     * @throws java.io.UnsupportedEncodingException
     * @throws java.security.NoSuchAlgorithmException
     */
    @WebMethod(operationName = "operacion_Registro_Android")
    public String operacion_Registro_Android(@WebParam(name = "nombre") String nombre, @WebParam(name = "aPaterno") String aPaterno, @WebParam(name = "aMaterno") String aMaterno, @WebParam(name = "correo") String correo, @WebParam(name = "pass") String pass) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Conductor conductor = new Conductor(nombre, aPaterno, aMaterno, correo, pass);
        boolean exito;
        String cadena = "El correo ya existe";
        Seguridad seg = new Seguridad();
        seg.setKey(Seguridad.genKey(correo));
        conductor.setPass(seg.encriptar(pass));
        exito = conductor.registrar();
        if(exito){
            cadena = "Exito";
        }
        return cadena;
    }

    /**
     * Web service operation
     * @param correo
     * @return 
     * @throws java.lang.ClassNotFoundException 
     */
    @WebMethod(operationName = "operacion_Mis_Favs")
    public String operacion_Mis_Favs(@WebParam(name = "correo") String correo) throws ClassNotFoundException {
        String cadena = "";
        Conductor conductor = new Conductor();
        conductor.setCorreo(correo);
        conductor.misFavs();
        ArrayList <Favoritos> misFavs = conductor.getMisFavs();
        for(int i = 0; i < misFavs.size(); i++){
            cadena += misFavs.get(i).getNombreEsta() + ";" +
                    "Calle: " + misFavs.get(i).getCalle() + ", Col: " + misFavs.get(i).getColonia() + ", Del: " + 
                    misFavs.get(i).getDelegacion() + ", Estado: " + misFavs.get(i).getEstado() + ";" +
                    misFavs.get(i).getIdEstaciobnamiento() + ";" + misFavs.get(i).getUrlImagen() + "//";
        }
        if(cadena.equals(""))
            cadena = "0";
        
        return cadena;
    }

    /**
     * Web service operation
     * @param correo
     * @return 
     * @throws java.lang.ClassNotFoundException
     */
    @WebMethod(operationName = "operacion_Mis_Recs")
    public String operacion_Mis_Recs(@WebParam(name = "correo") String correo) throws ClassNotFoundException {
        String cadena = "";
        Conductor conductor = new Conductor();
        conductor.setCorreo(correo);
        conductor.misRecientes();
        ArrayList <Recientes> misRecs = conductor.getMisRecientes();
        for(int i = 0; i < misRecs.size(); i++){
            cadena += misRecs.get(i).getNombreEsta() + ";" +
                    "Calle: " + misRecs.get(i).getCalle() + ", Col: " + misRecs.get(i).getColonia() + ", Del: " + 
                    misRecs.get(i).getDelegacion() + ", Estado: " + misRecs.get(i).getEstado() + ";" +
                    "Fecha y Hora de Visita: " + misRecs.get(i).getFecha() + ";" + misRecs.get(i).getIdEstaciobnamiento()
                    + ";" + misRecs.get(i).getUrlImagen() + "//";
        }
        if(cadena.equals(""))
            cadena = "0";
        
        return cadena;
    }

    /**
     * Web service operation
     * @param correo
     * @param idEsta
     * @return 
     * @throws java.lang.ClassNotFoundException
     */
    @WebMethod(operationName = "operacion_Borra_Fav")
    public String operacion_Borra_Fav(@WebParam(name = "correo") String correo, @WebParam(name = "idEsta") String idEsta) throws ClassNotFoundException {
        Conductor conduc = new Conductor();
        conduc.setCorreo(correo);
        conduc.setNombre(idEsta);
        conduc.borrarFav();
        
        return "Eliminado";
    }

    @WebMethod(operationName = "operacion_Detalles_Estacionamiento")
    public String operacion_Detalles_Estacionamiento(@WebParam(name = "idEsta") String idEsta) {
        Estacionamiento esta = new Estacionamiento();
        boolean exito;
        String msj = "ko";
        esta.setIdEstacionamiento(Integer.parseInt(idEsta));
        exito = esta.detalles();
        
        if(exito) {
            msj = esta.getIdEstacionamiento() + "//" + esta.getNombreEsta() + "//" + esta.getCordenadaX() + "//" + esta.getCordenadaY() + "//" +
                    esta.getCalle()+", Col. "+esta.getColonia()+", " + esta.getDel_muni() + ", " + esta.getEstado() + ". " + "//" +
                    esta.getHorario() + "//" + esta.getTarifa() + "//" + esta.getAlturaMaxima() + "//" + esta.getDescripcion();
        }
        
        return msj;
    }

    /**
     * Web service operation
     * @param correo
     * @return 
     */
    @WebMethod(operationName = "operacion_Recuperar_Datos")
    public String operacion_Recuperar_Datos(@WebParam(name = "correo") String correo) {
        Conductor conduc = new Conductor();
        String cad;
        conduc.setCorreo(correo);
        conduc.recuperarDatos();
        cad = conduc.getNombre() + "//" + conduc.getaPaterno() + "//" + conduc.getaMaterno();
        return cad;
    }

    /**
     * Web service operation
     * @return 
     */
    @WebMethod(operationName = "operacion_Mi_Mapa")
    public String operacion_Mi_Mapa() {
        String cad = "";
        Estacionamiento esta = new Estacionamiento();
        List<Estacionamiento> lista = esta.miMapaAndroid();
        for(int i = 0; i < lista.size() ;i++){
            cad += lista.get(i).getIdEstacionamiento() + ";" + lista.get(i).getNombreEsta() + ";" +
                    lista.get(i).getCordenadaX() + ";" + lista.get(i).getCordenadaY() + "//";
        }
        
        return cad;
    }

    /**
     * Web service operation
     * @param correo
     * @param idEsta
     * @return 
     * @throws java.lang.ClassNotFoundException 
     */
    @WebMethod(operationName = "operacion_Is_Fav")
    public String operacion_Is_Fav(@WebParam(name = "correo") String correo, @WebParam(name = "idEsta") String idEsta) throws ClassNotFoundException {
        String cad;
        Conductor conduc = new Conductor();
        conduc.setCorreo(correo);
        conduc.setNombre(idEsta);
        if(conduc.isFav()){
            cad = "si";
        }
        else{
            cad = "no";
        }
        
        return cad;
    }

    /**
     * Web service operation
     * @param correo
     * @param idEsta
     * @return 
     * @throws java.lang.ClassNotFoundException 
     */
    @WebMethod(operationName = "operacion_Nuevo_Favorito")
    public String operacion_Nuevo_Favorito(@WebParam(name = "correo") String correo, @WebParam(name = "idEsta") String idEsta) throws ClassNotFoundException {
        String cad;
        Conductor conduc = new Conductor();
        conduc.setCorreo(correo);
        conduc.setNombre(idEsta);
        if(conduc.nuevoFav()){
            cad = "si";
        }
        else{
            cad = "no";
        }
        
        return cad;
    }

    /**
     * Web service operation
     * @param correo
     * @param idEsta
     * @return 
     * @throws java.lang.ClassNotFoundException 
     */
    @WebMethod(operationName = "operacion_Nuevo_Reciente")
    public String operacion_Nuevo_Reciente(@WebParam(name = "correo") String correo, @WebParam(name = "idEsta") String idEsta) throws ClassNotFoundException {
        String cad;
        Conductor conduc = new Conductor();
        conduc.setCorreo(correo);
        conduc.setNombre(idEsta);
        if(conduc.nuevoReciente()){
            cad = "si";
        }
        else{
            cad = "no";
        }
        
        return cad;
    }

    /**
     * Web service operation
     * @param nombre
     * @param aPaterno
     * @param aMaterno
     * @param correo
     * @param pass
     * @return 
     * @throws java.io.UnsupportedEncodingException
     * @throws java.security.NoSuchAlgorithmException
     */
    @WebMethod(operationName = "operacion_Actualiza_Datos")
    public String operacion_Actualiza_Datos(@WebParam(name = "nombre") String nombre, @WebParam(name = "aPaterno") String aPaterno, @WebParam(name = "aMaterno") String aMaterno, @WebParam(name = "correo") String correo, @WebParam(name = "pass") String pass) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Conductor obj = new Conductor(nombre, aPaterno, aMaterno, correo, pass);
        String cad;
        Seguridad seg = new Seguridad();         
        seg.setKey(Seguridad.genKey(obj.getCorreo()));
        obj.setPass(seg.encriptar(obj.getPass()));
        cad = obj.actualizarDatos();      
        return cad;
    }

    /**
     * Web service operation
     * @param correo
     * @param msj
     * @return 
     * @throws java.lang.ClassNotFoundException
     */
    @WebMethod(operationName = "operacion_Feed")
    public String operacion_Feed(@WebParam(name = "correo") String correo, @WebParam(name = "msj") String msj) throws ClassNotFoundException {
        String cad;
        boolean exito;
        Feedback fb = new Feedback();
        fb.setDescripcion(msj);
        Conductor conduc = new Conductor();
        conduc.setCorreo(correo);
        exito = conduc.enviarFeed(fb);
        
        if(exito)
            cad = "Enviado con Exito";
        else
            cad = "Ups... ocurrio un error";
        return cad;
    }

    /**
     * Web service operation
     * @param id
     * @return 
     * @throws java.lang.ClassNotFoundException
     */
    @WebMethod(operationName = "operacion_buscar")
    public String operacion_buscar(@WebParam(name = "id") String id) throws ClassNotFoundException {
        Administrador admin = new Administrador();
        
        ArrayList<Estacionamiento> estas = admin.recuperaEstacionamiento(id);
        String cadena = "";

        for(int i = 0; i < estas.size(); i++){
            cadena += estas.get(i).getNombreEsta() + ";" +
                    "Calle: " + estas.get(i).getCalle() + ", Col: " + estas.get(i).getColonia() + ", Del: " + 
                    estas.get(i).getDel_muni() + ", Estado: " + estas.get(i).getEstado() + ";" +
                    estas.get(i).getIdEstacionamiento() + ";" + estas.get(i).getUrlImg() + "//";
        }
        if(cadena.equals(""))
            cadena = "0";
        
        return cadena;
    }
   
      

    @WebMethod(operationName = "operacion_Muro")
    public String operacion_Muro() {
        String cad;
        Conductor conduc = new Conductor();
        cad = conduc.muro();       
        return cad;
    }

    /**
     * Web service operation
     * @param msj
     * @return 
     */
    @WebMethod(operationName = "operacion_NewComen")
    public String operacion_NewComen(@WebParam(name = "msj") String msj) {
        String cad;
        Conductor conduc = new Conductor();
        conduc.setCorreo(msj);
        cad = conduc.newComen();
        return cad;
    }

    /**
     * Web service operation
     * @param nombre
     * @return 
     */
    @WebMethod(operationName = "operacion_busAmigos")
    public String operacion_busAmigos(@WebParam(name = "nombre") String nombre) {
        String cad;
        Conductor conduc = new Conductor();
        conduc.setNombre(nombre);
        cad = conduc.buscarAmigo();
        return cad;
    }

    /**
     * Web service operation
     * @param correo
     * @param coAmigo
     * @param nomAmigo
     * @return 
     */
    @WebMethod(operationName = "operacion_newAmigo")
    public String operacion_newAmigo(@WebParam(name = "correo") String correo, @WebParam(name = "coAmigo") String coAmigo, @WebParam(name = "nomAmigo") String nomAmigo) {
        String cad;
        Conductor conduc = new Conductor();
        conduc.setCorreo(correo);
        conduc.setaPaterno(coAmigo);
        conduc.setNombre(nomAmigo);
        cad = conduc.newAmigo();
        return cad;
    }

    /**
     * Web service operation
     * @param correo
     * @return 
     */
    @WebMethod(operationName = "operacion_getAmigos")
    public String operacion_getAmigos(@WebParam(name = "correo") String correo) {
        String cad;
        Conductor conduc = new Conductor();
        conduc.setCorreo(correo);
        cad = conduc.getAmigos();
        return cad;
    } 

    /**
     * Web service operation
     * @param idEsta
     * @return 
     * @throws java.lang.ClassNotFoundException
     */
    @WebMethod(operationName = "operacion_Lugares")
    public String operacion_Lugares(@WebParam(name = "idEsta") String idEsta) throws ClassNotFoundException {
        String cad;
        Conductor conduc = new Conductor();
        conduc.setNombre(idEsta);
        cad = conduc.lugares();
        return cad;
    }

    

}
