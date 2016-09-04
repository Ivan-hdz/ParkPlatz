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
import java.util.logging.Level;
import java.util.logging.Logger;
import me.ovrwrite.parkplatz.web.model.Sql;

/**
 *
 * @author Dell
 */
public class Administrador extends Persona{
     
    private ArrayList <Feedback> catFeedback = new <Feedback> ArrayList();
    private ArrayList <Estacionamiento> catEstacionamientos = new <Estacionamiento> ArrayList();
    private boolean feedLleno;

    public Administrador(){};
    

    public boolean isFeedLleno() {
        return feedLleno;
    }

    public void setFeedLleno(boolean feedLleno) {
        this.feedLleno = feedLleno;
    }
    
    public Administrador(String nombre, String aPaterno, String aMaterno, String correo, String pass){
       this.nombre = nombre;
       this.aPaterno = aPaterno;
       this.aMaterno = aMaterno;
       this.correo = correo;
       this.pass = pass;
   }

    public ArrayList<Feedback> getCatFeedback() {
        return catFeedback;
    }

    public void setCatFeedback(ArrayList<Feedback> catFeedback) {
        this.catFeedback = catFeedback;
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
            ps.setInt(4, 1);
            ps.setString(5, correo);
            ps.setString(6, pass);
            ps.executeUpdate();
            exito = true;
        }catch (SQLException ex) {
            System.out.println("Verifique que los campos del objeto no esten nulos: "+ex.getMessage());
            exito = false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
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
        }catch (SQLException ex) {
            System.out.println("Verifique el correo electronico: "+ ex.getMessage());
            exito = false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
            exito = false;
        }
        return exito;
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
        
    public void traerFeedback() throws ClassNotFoundException{
        Connection con;
        Feedback feed = new Feedback();
        try {
            con = Sql.conectar();
            PreparedStatement ps = con.prepareStatement("call get_feedback()");
            ResultSet rs = ps.executeQuery();
        
        if(!rs.next()){
            feedLleno = false;
        }
        else{
            rs.previous();
            while(rs.next()){
                feed.setIdFeedback(rs.getInt("idfeedback"));
                feed.setDescripcion(rs.getString("descripcion"));
                feed.setIdUsuarioRedactor(rs.getInt("idUsuario"));
                feed.setPrioridad(rs.getString("idPrioridad"));
                feed.setFecha(rs.getString("fecha"));
                feed.setCorreoUsuarioRedactor(rs.getString("correo"));
                catFeedback.add(feed);
                feed = new Feedback();
            }
            feedLleno = true;
        }
        } catch (SQLException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public ArrayList<Estacionamiento> recuperaEstacionamiento(String nombre) throws ClassNotFoundException{
        Connection con;
        
        ArrayList<Estacionamiento> estas = new ArrayList();
        try {
            
            con = Sql.conectar();
            PreparedStatement ps = con.prepareStatement("call trae_Esta(?)");
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Estacionamiento esta = new Estacionamiento();
                esta.setIdEstacionamiento(rs.getInt("id"));
                esta.setNombreEsta(rs.getString("nombreEstacionamiento"));
                esta.setCalle(rs.getString("calle"));
                esta.setColonia(rs.getString("colonia"));
                esta.setDel_muni(rs.getString("dele"));
                esta.setEstado(rs.getString("estado"));
                
                estas.add(esta);
            
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return estas;
    }
    
    public void borrarEstacionamiento(String idE) throws ClassNotFoundException{
        int id = Integer.parseInt(idE);
        try {
            Connection con = Sql.conectar();
            PreparedStatement ps = con.prepareStatement("call borra_estacionamiento(?)");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean enviarAviso(Feedback fb) throws ClassNotFoundException{
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
            ps.setInt(2, fb.getIdEstaADarAviso());
            ps.setInt(3, 3);
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

}
