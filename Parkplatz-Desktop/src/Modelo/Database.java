/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Ivan
 */
public class Database {
     public static Connection conectar() throws ClassNotFoundException, SQLException{
        Connection con = null;
        
        String usuario = "root";
        String contrasenia = "n0m3l0";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost/parkplatz",usuario, contrasenia);
        }catch(SQLException e){
           e.printStackTrace();
        }
        return con;
    }
    public static Connection conectar(String nombreBase) throws ClassNotFoundException, SQLException{
        Connection con = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/"+nombreBase;
            return DriverManager.getConnection(url, "root", "n0m3l0");
        }catch(SQLException e){
            e.printStackTrace();
        }
        return con;
    }
    public static Connection conectar(String nombreBase, String pass) throws ClassNotFoundException, SQLException{
        Connection con = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/"+nombreBase;
            return DriverManager.getConnection(url, "root", pass);
        }catch(SQLException e){
           e.printStackTrace();
        }
        return con;
    }
    public static Connection conectar(String nombreBase,String user, String pass) throws ClassNotFoundException, SQLException{
        Connection con = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/"+nombreBase;
            return DriverManager.getConnection(url, "root", pass);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return con;
    }
}
