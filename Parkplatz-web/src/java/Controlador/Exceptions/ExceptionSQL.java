/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Exceptions;

import Modelo.Sql;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class ExceptionSQL extends SQLException{
    public Connection volverIntentar() throws ClassNotFoundException, SQLException{
        Connection con = null;
        try{
        con = Sql.conectar();
        }catch(SQLException e){
            con = this.volverIntentarBase();
        }
        return con;
    }

    /**
     *
     * @return 
     * @throws ClassNotFoundException
     */
    public Connection volverIntentarBase() throws ClassNotFoundException{
        Connection con = null;
        try{
        Scanner sc = new Scanner(System.in);
        System.out.println("Favor de verificar el nombre de la base:");
        con = Sql.conectar(sc.nextLine());
        }catch(SQLException e){
            System.out.println(e.getMessage()+" DESDE "+this.getClass().getName());
            con = this.volverIntentarBasePass();
        }
        return con;
    }
    public Connection volverIntentarBasePass() throws ClassNotFoundException{
        Connection con = null;
        try{
            Scanner sc = new Scanner(System.in);
            System.out.println("Favor de verificar el nombre de la base:");
            String base = sc.nextLine();
            System.out.println("Favor de verificar la contraseña");
            String pass = sc.nextLine();
            con = Sql.conectar(base,pass);
        }catch(SQLException e){
            System.out.println(e.getMessage()+" DESDE "+this.getClass().getName());
            con = this.volverIntentarBaseUsuerPass();
        }
        return con;
    }
    
    public Connection volverIntentarBaseUsuerPass() throws ClassNotFoundException{
        Connection con = null;
        try{
            Scanner sc = new Scanner(System.in);
            System.out.println("Favor de verificar el nombre de la base:");
            String base = sc.nextLine();
            System.out.println("Favor de verificar el usuario");
            String usuario = sc.nextLine();
            System.out.println("Favor de verificar la contraseña");
            String pass = sc.nextLine();
            Sql.conectar(base,pass);
        }catch(SQLException e){
            System.out.println(e.getMessage()+" DESDE "+this.getClass().getName());
        }
        return con;
    }
    
    
}
