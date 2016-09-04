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
import java.util.logging.Level;
import java.util.logging.Logger;
import me.ovrwrite.parkplatz.web.model.Sql;

/**
 *
 * @author Dell
 */
public abstract class Persona {
    protected String nombre;
    protected String aPaterno;
    protected String aMaterno;
    protected String correo;
    protected String pass;
    protected String tipoUsuario;
    public abstract boolean registrar();
    public void cerrarSesion() {
       try {
            this.finalize();
        } catch (Throwable ex) {
            Logger.getLogger(Conductor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void setPass(String pass){
        this.pass = pass;
    }
    public abstract boolean recuperarDatos();
    protected boolean logueado;
    public boolean isLogueado(){
        return this.logueado;
    }
    public void setLogueado(boolean l){
        this.logueado = l;
    }
    public boolean iniciarSesion(){
        boolean exito = false;
        try{
            Connection con = Sql.conectar();
            PreparedStatement ps = con.prepareStatement("call login(?,?)");
            ps.setString(1, correo);
            ps.setString(2, pass);
            System.out.println(correo);
            System.out.println(pass);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                exito = rs.getBoolean(1);
            }
            System.out.println(exito);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return exito;
    }
    public String delUser(String emailC){
        String estado = "";
        try{
            Connection con = Sql.conectar();
            PreparedStatement ps = con.prepareStatement("call borrar_usuario(?)");
            ps.setString(1, emailC);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                estado = rs.getString(1);
            }
        }catch(ClassNotFoundException | SQLException e){
            estado = e.getMessage();
        }
        return estado;
    }
}
