/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.ovrwrite.parkplatz.web.controller.beans;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.ovrwrite.parkplatz.web.model.Sql;
/**
 *
 * @author Dell
 */
public class Estacionamiento extends Persona {
    private String nombreEsta;
    private String calle;
    private String colonia;
    private String estado;
    private String del_muni;
    private double cordenadaX;
    private double cordenadaY;
    private String urlImg;
    private int idEstacionamiento;
    private String horario;
    private String tarifa;
    private float alturaMaxima;
    private String descripcion;
    private ArrayList<Estacionamiento> misEstacionamientos;
    
    public Estacionamiento(){
     
 }
    
    public int getIdEstacionamiento(){
        return this.idEstacionamiento;
    }
    public void setIdEstacionamiento(int id ){
        this.idEstacionamiento = id;
    }
    public Estacionamiento(String nombre, String aPaterno, String aMaterno, String correo, String pass, String nombreEsta, String calle, String colonia, String estado, String del_muni, double cordenadaX, double cordenadaY){
       this.nombre = nombre;
       this.aPaterno = aPaterno;
       this.aMaterno = aMaterno;
       this.correo = correo;
       this.pass = pass;
       this.nombreEsta = nombreEsta;
       this.calle = calle;
       this.colonia = colonia;
       this.cordenadaX = cordenadaX;
       this.cordenadaY = cordenadaY;
       this.del_muni = del_muni;
       this.estado = estado;
   }
     /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the aPaterno
     */
    public String getaPaterno() {
        return aPaterno;
    }

    /**
     * @param aPaterno the aPaterno to set
     */
    public void setaPaterno(String aPaterno) {
        this.aPaterno = aPaterno;
    }

    /**
     * @return the aMaterno
     */
    public String getaMaterno() {
        return aMaterno;
    }

    /**
     * @param aMaterno the aMaterno to set
     */
    public void setaMaterno(String aMaterno) {
        this.aMaterno = aMaterno;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

   
    /**
     * @param pass the pass to set
     */
    @Override
    public void setPass(String pass) {
        this.pass = pass;
    }
    public String getPass(){
        return this.pass;
    }
    /**
     * @return the nombreEsta
     */
    public String getNombreEsta() {
        return nombreEsta;
    }

    /**
     * @param nombreEsta the nombreEsta to set
     */
    public void setNombreEsta(String nombreEsta) {
        this.nombreEsta = nombreEsta;
    }

    /**
     * @return the calle
     */
    public String getCalle() {
        return calle;
    }

    /**
     * @param calle the calle to set
     */
    public void setCalle(String calle) {
        this.calle = calle;
    }

    /**
     * @return the colonia
     */
    public String getColonia() {
        return colonia;
    }

    /**
     * @param colonia the colonia to set
     */
    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the del_muni
     */
    public String getDel_muni() {
        return del_muni;
    }

    /**
     * @param del_muni the del_muni to set
     */
    public void setDel_muni(String del_muni) {
        this.del_muni = del_muni;
    }

    /**
     * @return the cordenadaX
     */
    public double getCordenadaX() {
        return cordenadaX;
    }

    /**
     * @param cordenadaX the cordenadaX to set
     */
    public void setCordenadaX(double cordenadaX) {
        this.cordenadaX = cordenadaX;
    }

    /**
     * @return the cordenadaY
     */
    public double getCordenadaY() {
        return cordenadaY;
    }

    /**
     * @param cordenadaY the cordenadaY to set
     */
    public void setCordenadaY(double cordenadaY) {
        this.cordenadaY = cordenadaY;
    }
    public boolean registrarOtro() throws ClassNotFoundException{
        boolean exito = false;
       
        try{
            String result;
            int idDatos = 0;
            System.out.println("antes de id datos");
            Connection con = Sql.conectar();
            PreparedStatement ps = con.prepareStatement("call getIdDatos(?)");
            ps.setString(1, correo);
            System.out.println(correo+" <---");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                System.out.println("entra1");
                idDatos = rs.getInt(1);
            }
            System.out.println(idDatos);
            ps = con.prepareStatement("call registroEstaSinUsr(?,?,?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, idDatos);
            ps.setString(2, String.valueOf(cordenadaX));
            ps.setString(3, String.valueOf(cordenadaY));
            ps.setInt(4, 1);
            ps.setString(5, calle);
            ps.setString(6, colonia);
            ps.setString(7, del_muni);
            ps.setString(8, estado);
            ps.setString(9, "default");
            ps.setInt(10, 1);
            ps.setString(11, nombreEsta);
            rs = ps.executeQuery();
            while(rs.next()){
                result = rs.getString(1);
                 if(result.equalsIgnoreCase("Exito")){
                     exito = true;
                     break;
               }
            }
           
        }catch(SQLException e){
            System.out.println(e.getMessage());
      
        }
        return exito;
    }
    @Override
    public boolean registrar() {
        boolean exito = false;
        try {
            String res = "";
            Connection con = Sql.conectar();
            PreparedStatement ps = con.prepareStatement("call registroEsta(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, correo);
            ps.setString(2, pass);
            ps.setInt(3, 2);
            ps.setString(4, nombre);
            ps.setString(5, aPaterno);
            ps.setString(6, aMaterno);
            ps.setString(7, String.valueOf(cordenadaX));
            ps.setString(8, String.valueOf(cordenadaY));
            ps.setInt(9,1);
            ps.setString(10, calle);
            ps.setString(11, colonia);
            ps.setString(12, del_muni);
            ps.setString(13, estado);
            ps.setString(14, "default");
            ps.setInt(15, 1);
            ps.setString(16,nombreEsta);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                res = rs.getString("Mensaje");
            }
            if(res.equalsIgnoreCase("Exito")){
                exito = true;
            }
        }catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Verifique que los campos del objeto no esten nulos: "+ex.getMessage());
            exito = false;
        }
        return exito;
    }
    public String eliminarEstacionamiento(){
        String lcl_estado = "";
        try{

           System.out.println("desde server" + getIdEstacionamiento());
            Connection con = Sql.conectar();
            PreparedStatement ps = con.prepareStatement("call borra_estacionamiento(?)");
            ps.setInt(1, idEstacionamiento);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                lcl_estado = rs.getString(1);
            }
        }catch(ClassNotFoundException | SQLException e){
            lcl_estado = e.getMessage();
        }
        return lcl_estado;
    }

    /**
     * @return the urlImg
     */
    public String getUrlImg() {
        return urlImg;
    }

    /**
     * @param urlImg the urlImg to set
     */
    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    @Override
    public boolean recuperarDatos() {
        boolean exito = false;
        try{
            Connection con = Sql.conectar();
            PreparedStatement ps = con.prepareStatement("call recuperameEsta(?,?)");
            ps.setString(1, correo);
            ps.setInt(2, idEstacionamiento);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                nombre = rs.getString("nombre");
                aPaterno = rs.getString("aPaterno");
                aMaterno = rs.getString("aMaterno");
                cordenadaX = Double.parseDouble(rs.getString("coorX"));
                cordenadaY = Double.parseDouble(rs.getString("coorY"));
                calle = rs.getString("calle");
                colonia = rs.getString("colonia");
                del_muni = rs.getString("dele");
                estado = rs.getString("estado");
                urlImg = rs.getString("url");
                System.out.println(urlImg);
                idEstacionamiento = rs.getInt("idEst");
                alturaMaxima = rs.getFloat("alturaMaxima");
                horario = rs.getString("horarios");
                descripcion = rs.getString("descripcion");
                tarifa = rs.getString("tarifa");
                nombreEsta = rs.getString("nombreEsta");
            }
            exito = true;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("El error es de esta clase: "+e.getClass().getName());
            exito = false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Estacionamiento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exito;
    }

    public String actualizarDatosEsta(){
        String exito = "";
        try{
            Connection con = Sql.conectar();
            PreparedStatement ps = con.prepareStatement("call editarDatosEsta(?,?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, idEstacionamiento);
            ps.setString(2, nombreEsta);
            ps.setString(3, horario);
            ps.setString(4, tarifa);
            ps.setFloat(5, alturaMaxima);
            ps.setString(6, descripcion);
            ps.setString(7, calle);
            ps.setString(8, colonia);
            ps.setString(9, del_muni);
            ps.setString(10, estado);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                exito = rs.getString(1);
            }
            if(estado.equalsIgnoreCase("Actualizacion exitosa!")){
                this.recuperarDatos();
            }
        }catch(ClassNotFoundException | SQLException e ){
            System.out.println("El error es desde la clase Estacionamiento, metodo actualizarDatosEst"+e.getMessage());
        }
        return exito;
    }
    @Override
    public boolean iniciarSesion(){
        
       boolean exito = super.iniciarSesion();
       if(exito == true){
           logueado = true;
       }else{
           this.cerrarSesion();
       }
       return exito;
    }
    public String subirFoto() throws ClassNotFoundException, SQLException{
        String lcl_estado = "";
        Connection con = null;
        try{
            con = Sql.conectar();
            PreparedStatement ps = con.prepareStatement("call actualizarFoto(?,?)");
            ps.setInt(1, idEstacionamiento);
            ps.setString(2, urlImg);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                lcl_estado = rs.getString(1);
            }
            
        }catch(ClassNotFoundException | SQLException e){
            con = null;
            lcl_estado = "Hubo un error a la hora de registrar el archivo en la base";
        }finally{
            if(con != null)
                con.close();
        }
        return lcl_estado;
    }
    public String actualizarDatos(){
        String estado = "";
        try{
            Connection con = Sql.conectar();
            PreparedStatement ps = con.prepareStatement("call editar_datos(?,?,?,?,?)");
            ps.setString(1, nombre);
            ps.setString(2, aPaterno);
            ps.setString(3, aMaterno);
            ps.setInt(4, idEstacionamiento);
            ps.setString(5, pass);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                estado = rs.getString(1);
            }
            if(estado.equalsIgnoreCase("Actualizacion exitosa!")){
                this.recuperarDatos();
            }
        }catch(SQLException e){
            System.out.println(e.getMessage()+" <--- desde la clase estacionamiento");   
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Estacionamiento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return estado;
    }
    public boolean enviarFeed(Feedback fb) throws ClassNotFoundException{
        boolean exito = false;
        try{
            
            Connection con = Sql.conectar();
            PreparedStatement ps = con.prepareStatement("call getIdUsuario(?)");
            ps.setInt(1, idEstacionamiento);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                fb.setIdUsuarioRedactor(rs.getInt(1));
            }
            ps = null;
            rs = null;
            ps = con.prepareStatement("call nuevo_feedback(?,?,?)");
            ps.setString(1, fb.getDescripcion());
            ps.setInt(2, fb.getIdUsuarioRedactor());
            ps.setInt(3, 1);
            rs = ps.executeQuery();
            while(rs.next()){
                if(rs.getString(1).equalsIgnoreCase("Exito")){
                    exito = true;
                }
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
            exito = false;
        }
        return exito;
    }

    /**
     * @return the horario
     */
    public String getHorario() {
        return horario;
    }

    /**
     * @param horario the horario to set
     */
    public void setHorario(String horario) {
        this.horario = horario;
    }

    /**
     * @return the tarifa
     */
    public String getTarifa() {
        return tarifa;
    }

    /**
     * @param tarifa the tarifa to set
     */
    public void setTarifa(String tarifa) {
        this.tarifa = tarifa;
    }

    /**
     * @return the alturaMaxima
     */
    public float getAlturaMaxima() {
        return alturaMaxima;
    }

    /**
     * @param alturaMaxima the alturaMaxima to set
     */
    public void setAlturaMaxima(float alturaMaxima) {
        this.alturaMaxima = alturaMaxima;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public ArrayList<Estacionamiento> obtenerEstacionamientos() throws ClassNotFoundException{
        ArrayList<Estacionamiento> cualesTengo = new ArrayList<>();
        try{
            Connection con = Sql.conectar();
            PreparedStatement ps = con.prepareStatement("select * from verMiEsta where correo = ?");
            ps.setString(1, correo);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Estacionamiento esta = new Estacionamiento();
                esta.setNombreEsta(rs.getString(1));
                esta.setIdEstacionamiento(rs.getInt(2));
                esta.setUrlImg(rs.getString("url"));
                cualesTengo.add(esta);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage()+"desde cuales tengo");
        }
        return cualesTengo;
    }


    /**
     * @return the misEstacionamientos
     */
    public ArrayList<Estacionamiento> getMisEstacionamientos() {
        return misEstacionamientos;
    }

    /**
     * @param misEstacionamientos the misEstacionamientos to set
     */
    public void setMisEstacionamientos(ArrayList<Estacionamiento> misEstacionamientos) {
        this.misEstacionamientos = misEstacionamientos;
    }
    
    public ArrayList<Feedback> recuperarAvisos(){
        ArrayList<Feedback> avisos = new ArrayList<>();
        try{
            Connection con = Sql.conectar();
            PreparedStatement ps = con.prepareStatement("call dameAvisos(?)");
            ps.setInt(1, idEstacionamiento);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Feedback aviso = new Feedback();
                aviso.setFecha(rs.getString("fecha"));
                aviso.setDescripcion(rs.getString("descripcion"));
                aviso.setPrioridad("Aviso");
                aviso.setIdEstaADarAviso(idEstacionamiento);
                aviso.setIdFeedback(rs.getInt("idfeedback"));
                avisos.add(aviso);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return avisos;
    }
    public static ArrayList<Integer> traeIdsEstacionamiento(String correo){
      ArrayList<Integer> ids = new ArrayList<>();
        try {
            
            Connection con = Sql.conectar();
            PreparedStatement ps = con.prepareStatement("select e.idestacionamientos from estacionamientos e inner join usuario u on u.idDatos = e.idDatos where u.correo = ?");
            ps.setString(1, correo);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
              ids.add(rs.getInt(1));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Estacionamiento.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Estacionamiento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ids;
    }
    
   
//
            public boolean detalles() {
        boolean exito = false;
        try{
            Connection con = Sql.conectar();
            PreparedStatement ps = con.prepareStatement("call detalles_Estacionamiento(?)");
            ps.setInt(1, idEstacionamiento);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                cordenadaX = Double.parseDouble(rs.getString("x"));
                cordenadaY = Double.parseDouble(rs.getString("y"));
                calle = rs.getString("calle");
                colonia = rs.getString("colonia");
                del_muni = rs.getString("dele");
                estado = rs.getString("estado");
                urlImg = rs.getString("url");
                idEstacionamiento = rs.getInt("idEsta");
                alturaMaxima = rs.getFloat("alturaMaxima");
                horario = rs.getString("horarios");
                descripcion = rs.getString("descripcion");
                tarifa = rs.getString("tarifa");
                nombreEsta = rs.getString("nombre");
            }
            exito = true;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("El error es de esta clase: "+e.getClass().getName());
            exito = false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Estacionamiento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exito;
    }
        
    public List<Estacionamiento> miMapaAndroid(){
        boolean exito = false;
        List<Estacionamiento> lista = new ArrayList<>();
        Estacionamiento esta = null;
        try{
            Connection con = Sql.conectar();
            PreparedStatement ps = con.prepareStatement("select idEst, x, y, nombre from verestas");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                esta = new Estacionamiento();

                cordenadaX = Double.parseDouble(rs.getString("x"));
                cordenadaY = Double.parseDouble(rs.getString("y"));
                idEstacionamiento = rs.getInt("idEst");
                nombreEsta = rs.getString("Nombre");
                esta.setIdEstacionamiento(idEstacionamiento);
                esta.setNombreEsta(nombreEsta);
                esta.setCordenadaX(cordenadaX);
                esta.setCordenadaY(cordenadaY);
                lista.add(esta);
            }
            exito = true;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("El error es de esta clase: "+e.getClass().getName());
            exito = false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Estacionamiento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

}
