/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

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
 * @author honte_000
 */
public class Servicios {
    private String nombre;
    private String descripcion;
    private float costo;
    private int idServicio;
    
    public static String nuevoServicioOferta(String nombreS, String descS, Float costoS, int idEst)
    {
         String estado = "";
        try{
        
        Connection con = Sql.conectar();
        PreparedStatement ps = con.prepareStatement("call nuevo_Servicio(?,?,?,?)");
        ps.setString(1, nombreS);
        ps.setString(2, descS);
        ps.setFloat(3, costoS);
        ps.setInt(4, idEst);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            estado = rs.getString(1);
        }
        }catch(SQLException e){
            System.out.println("El error ocurrio en: "+e.getClass().getName()+" y fue: "+e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Servicios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return estado;
    }
    public static String eliminarServicio(int idServicio ){
        String estado = "";
        try{
            Connection con = Sql.conectar();
            PreparedStatement ps = con.prepareStatement("call eliminarServicio(?)");
            ps.setInt(1,idServicio);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                estado = rs.getString(1);
            }
            
        }catch(Exception e){
            System.out.println(e.getMessage()+" fue en el metodo 'eliminarServicio' del servidor");
        }
        return estado;
    }
    public static String actualizar(int idServ, String nombre, String descr, float costo){
        String estado = "";
        try{
            Connection con = Sql.conectar();
            PreparedStatement ps = con.prepareStatement("call actualizarServicio(?,?,?,?)");
            ps.setInt(1,idServ);
            ps.setString(2, nombre);
            ps.setString(3, descr);
            ps.setFloat(4, costo);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                estado = rs.getString(1);
            }
            
        }catch(Exception e){
            System.out.println(e.getMessage()+" fue en el metodo 'actualizar' del servidor");
        }
        return estado;
    }
    public static ArrayList<Servicios> obtenerServicios(int idEstacionamiento) throws SQLException, ClassNotFoundException{
        ArrayList<Servicios> listaServs = new <Servicios>ArrayList();
        Connection con = Sql.conectar();
        PreparedStatement ps = con.prepareStatement("call verServicios(?)");
        ps.setInt(1, idEstacionamiento);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Servicios serv = new Servicios();
            serv.setIdServicio(rs.getInt(1));
            serv.setNombre(rs.getString(2));
            serv.setDescripcion(rs.getString(3));
            serv.setCosto(rs.getFloat(4));
            
            listaServs.add(serv);
        }
        return listaServs;
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

    /**
     * @return the costo
     */
    public float getCosto() {
        return costo;
    }

    /**
     * @param costo the costo to set
     */
    public void setCosto(float costo) {
        this.costo = costo;
    }

    /**
     * @return the idServicio
     */
    public int getIdServicio() {
        return idServicio;
    }

    /**
     * @param idServicio the idServicio to set
     */
    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }
}
