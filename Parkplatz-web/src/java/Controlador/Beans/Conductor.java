/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Beans;

import Controlador.Beans.Favoritos;
import Controlador.Beans.Recientes;
import Modelo.Sql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class Conductor extends Persona{
    
    private ArrayList <Favoritos> misFavs;
    private ArrayList <Recientes> misRecientes;

    public ArrayList<Favoritos> getMisFavs() {
        return misFavs;
    }

    public void setMisFavs(ArrayList<Favoritos> misFavs) {
        this.misFavs = misFavs;
    }

    public boolean isMisFavsLLeno() {
        return misFavsLLeno;
    }

    public void setMisFavsLLeno(boolean misFavsLLeno) {
        this.misFavsLLeno = misFavsLLeno;
    }

    public ArrayList<Recientes> getMisRecientes() {
        return misRecientes;
    }

    public void setMisRecientes(ArrayList<Recientes> misRecientes) {
        this.misRecientes = misRecientes;
    }

    public boolean isMisRecientesLleno() {
        return misRecientesLleno;
    }

    public void setMisRecientesLleno(boolean misRecientesLleno) {
        this.misRecientesLleno = misRecientesLleno;
    }
    private boolean misFavsLLeno;
    private boolean misRecientesLleno;
    public Conductor(){};
    
    public Conductor(String nombre, String aPaterno, String aMaterno, String correo, String pass){
       this.nombre = nombre;
       this.aPaterno = aPaterno;
       this.aMaterno = aMaterno;
       this.correo = correo;
       this.pass = pass;
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
    @Override
    public boolean registrar() {
        boolean exito;
        try {
            Connection con = Sql.conectar();
            PreparedStatement ps = con.prepareStatement("call nuevo_usuario(?,?,?,?,?,?)");
            ps.setString(1, nombre);
            ps.setString(2, aPaterno);
            ps.setString(3, aMaterno);
            ps.setInt(4, 3);
            ps.setString(5, correo);
            ps.setString(6, pass);
            ps.executeUpdate();
            exito = true;
        }catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Verifique que los campos del objeto no esten nulos: "+ex.getMessage());
            exito = false;
        }
        return exito;
    }

    

    @Override
    public boolean recuperarDatos() {
         boolean exito;
        try {
            Connection con = Sql.conectar();
            PreparedStatement ps = con.prepareStatement("call obtener_datos(?)");
            ps.setString(1, correo);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                this.nombre = rs.getString(2);
                this.aPaterno = rs.getString(3);
                this.aMaterno = rs.getString(4);
            }
            exito = true;
        }catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Verifique el correo electronico: "+ ex.getMessage());
            exito = false;
        }
        return exito;
    }
    
    public String actualizarDatos(){
        String estado = "";
        try{
            Connection con = Sql.conectar();
            PreparedStatement ps = con.prepareStatement("call modifica_conductor(?,?,?,?,?)");
            ps.setString(1, nombre);
            ps.setString(2, aPaterno);
            ps.setString(3, aMaterno);
            ps.setString(4, pass);
            ps.setString(5, correo);
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
    
    @Override
    public boolean iniciarSesion(){
       boolean exito = super.iniciarSesion();
       if(exito == true){
           logueado = this.recuperarDatos();
           if(logueado == false){
               this.cerrarSesion();
           }
       }else{
           this.cerrarSesion();
       }
       return exito;
    }
    
    public boolean enviarFeed(Feedback fb) throws ClassNotFoundException{
        boolean exito = false;
        try{
            
            Connection con = Sql.conectar();
            PreparedStatement ps;
            ps = con.prepareStatement("call getIdUsuario_dos(?)");
            ps.setString(1, correo);
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
    
    
    
 //
        public void misFavs() throws ClassNotFoundException{
        Connection con;
        PreparedStatement ps;
        ResultSet rs;
        Favoritos fav = new Favoritos();
        misFavs = new <Favoritos> ArrayList();
        misFavsLLeno = false;
        try {
            con = Sql.conectar();         
            ps = con.prepareStatement("call get_Favoritos(?)");
            ps.setString(1, correo);
            rs = ps.executeQuery();
            if(!rs.next()){
                misFavsLLeno = false; 
            }
            else{
                rs.previous();
                while(rs.next()){
                    fav.setNombreEsta(rs.getString("nombreEstacionamiento"));
                    fav.setUrlImagen(rs.getString("url"));
                    fav.setCalle(rs.getString("calle"));
                    fav.setColonia(rs.getString("colonia"));
                    fav.setDelegacion(rs.getString("dele"));
                    fav.setEstado(rs.getString("estado"));
                    fav.setIdEstaciobnamiento(rs.getInt("id"));
                    misFavs.add(fav);
                    fav = new Favoritos();
                }              
                misFavsLLeno = true;       
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conductor.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public void misRecientes() throws ClassNotFoundException{
        Connection con;
        PreparedStatement ps;
        ResultSet rs;
        Recientes resc = new Recientes();
        misRecientes = new <Recientes> ArrayList();
        misRecientesLleno = false;
        try {
            con = Sql.conectar();         
            ps = con.prepareStatement("call get_Recientes(?)");
            ps.setString(1, correo);
            rs = ps.executeQuery();
            
            if(!rs.next()){
                misRecientesLleno = false; 
            }
            else{   
                rs.previous();
                while(rs.next()){                    
                    resc.setFecha(rs.getString("fecha"));
                    resc.setNombreEsta(rs.getString("nonbre"));
                    resc.setUrlImagen(rs.getString("url"));
                    resc.setCalle(rs.getString("calle"));
                    resc.setColonia(rs.getString("colonia"));
                    resc.setDelegacion(rs.getString("dele"));
                    resc.setEstado(rs.getString("estado"));
                    resc.setIdEstaciobnamiento(rs.getInt("id"));
                    misRecientes.add(resc);
                    resc = new Recientes();
                }              
                misRecientesLleno = true;       
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conductor.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public void borrarFav() throws ClassNotFoundException{
        Connection con;
        PreparedStatement ps;
        ResultSet rs;
        try {
            con = Sql.conectar();         
            ps = con.prepareStatement("call borrar_favo(?,?)");
            ps.setString(1, correo);
            ps.setInt(2, Integer.parseInt(nombre));
            rs = ps.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(Conductor.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    //Agregar
    public boolean isFav() throws ClassNotFoundException{
        Connection con;
        PreparedStatement ps;
        ResultSet rs;
        boolean is = false;
        int a;
        try {
            con = Sql.conectar();         
            ps = con.prepareStatement("call isfav(?,?)");
            ps.setString(1, correo);
            ps.setInt(2, Integer.parseInt(nombre));
            rs = ps.executeQuery();
            while(rs.next()){
                a = rs.getInt("c");
                if(a == 1){
                    is = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conductor.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return is;
    }
    
    public boolean nuevoFav() throws ClassNotFoundException{
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    boolean is = false;
    String a;
    try {
        con = Sql.conectar();         
        ps = con.prepareStatement("call nuevo_favorito(?,?)");
        ps.setString(1, correo);
        ps.setInt(2, Integer.parseInt(nombre));
        rs = ps.executeQuery();
        while(rs.next()){
            a = rs.getString("Mensaje");
            if(!a.equalsIgnoreCase("Ya existe")){
                is = true;
            }
        }
    } catch (SQLException ex) {
        Logger.getLogger(Conductor.class.getName()).log(Level.SEVERE, null, ex);
    } 
    return is;
    }
    
    public boolean nuevoReciente() throws ClassNotFoundException{
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    boolean is = false;
    String a;
    try {
        con = Sql.conectar();         
        ps = con.prepareStatement("call nuevo_reciente(?,?)");
        ps.setString(1, correo);
        ps.setInt(2, Integer.parseInt(nombre));
        rs = ps.executeQuery();
        while(rs.next()){
            a = rs.getString("Mensaje");
            if(!a.equalsIgnoreCase("Ya existe")){
                is = true;
            }
        }
    } catch (SQLException ex) {
        Logger.getLogger(Conductor.class.getName()).log(Level.SEVERE, null, ex);
    } 
    return is;
    }
    
        public String muro(){
        String cad = "";
        int vacia = 1;
        try {
            Connection c = Sql.conectar();
            PreparedStatement ps = c.prepareStatement("call getMuro");
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                vacia = rs.getInt("id");
            }
            if(vacia != 0){
                rs.previous();
                while(rs.next()){
                    cad += rs.getString("msj") + "/;/";
                }
                return cad;
            }
            else{
                cad = "v4zio";
            }
        } catch (ClassNotFoundException | SQLException ex) {
            cad = "Ups... hubo un erroor";
            return cad;
        }
        return cad;
    }
    
    public String newComen(){
        String cad = "";
        try {
            Connection con;
            PreparedStatement ps;
            ResultSet rs;     
            con = Sql.conectar();
            ps = con.prepareStatement("call newComent(?)");
            ps.setString(1, correo);
            ps.executeQuery();
            cad = "Enviado Correctamente";
            return cad;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Conductor.class.getName()).log(Level.SEVERE, null, ex);
            cad = "error";
        }
        return cad;
    }
    
    public String buscarAmigo(){
        String cad = "";
        try {
            Connection con;
            PreparedStatement ps;
            ResultSet rs;

            
            con = Sql.conectar();
            ps = con.prepareStatement("call buscarAmigos(?)");
            ps.setString(1, nombre);
            rs = ps.executeQuery();
            while(rs.next()){
                cad += rs.getString("correo") + ";" +  rs.getString("nombre") + "//";
            }
            return cad;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Conductor.class.getName()).log(Level.SEVERE, null, ex);
            cad = "error";
        }
        return cad;
    }
    
    public String newAmigo(){
        String cad = "";
        try {
            Connection con;
            PreparedStatement ps;
            ResultSet rs;     
            con = Sql.conectar();
            ps = con.prepareStatement("call newAmigo(?,?,?)");
            ps.setString(1, correo);
            ps.setString(2, aPaterno);
            ps.setString(3, nombre);
            ps.executeQuery();
            cad = "Amigo Agregado";
            return cad;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Conductor.class.getName()).log(Level.SEVERE, null, ex);
            cad = "error";
        }
        return cad;
    }
    
    public String getAmigos(){
        String cad = "";
        try {
            Connection con;
            PreparedStatement ps;
            ResultSet rs;
     
            con = Sql.conectar();
            ps = con.prepareStatement("call getAmigos(?)");
            ps.setString(1, correo);
            rs = ps.executeQuery();
            while(rs.next()){
                cad += rs.getString("coAmigo") + ";" +  rs.getString("nomAmigo") + "//";
            }
            return cad;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Conductor.class.getName()).log(Level.SEVERE, null, ex);
            cad = "error";
        }
        return cad;
    }
    
        public String lugares() throws ClassNotFoundException{
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    boolean is = false;
    String cad = "";
    try {
        con = Sql.conectar();         
        ps = con.prepareStatement("call getDisponibilidad(?)");
        ps.setInt(1, Integer.parseInt(nombre));
        rs = ps.executeQuery();
        while(rs.next()){
            cad = rs.getString("t") + "//" + rs.getString("d");
        }
    } catch (SQLException ex) {
        Logger.getLogger(Conductor.class.getName()).log(Level.SEVERE, null, ex);
    } 
    return cad;
    }
    
}
