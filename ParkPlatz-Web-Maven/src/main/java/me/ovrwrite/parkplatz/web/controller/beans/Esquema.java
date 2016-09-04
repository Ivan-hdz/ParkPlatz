/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.ovrwrite.parkplatz.web.controller.beans;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Ivan
 */

public class Esquema {
    
   private ArrayList<ObjetoDeEsquema> ObjetosEnEsquema = new <ObjetoDeEsquema>ArrayList();;
    private int totalPisos = 1;

    public static Esquema getEsquema(String url) throws FileNotFoundException, IOException{
       Esquema esquema = null;
       ObjectMapper mapper = new ObjectMapper();
        try{
           File archivo = new File(url+"/esquema.json");
           if(archivo.exists()){
               esquema = mapper.readValue( archivo,  Esquema.class);
           }else{
               esquema = new Esquema();
           }
            
        }catch(Exception e){
            e.printStackTrace();
        }
            return esquema;
    }
    public static String delEsquema(String url){
        
        String status = "";
        try{
            File archivo = new File(url+"/esquema.json");
            if(archivo.exists()){
                archivo.delete();
            }else{
                status = "Na hay esquema que eliminar!";
            }
            status = "Se ha eliminado su esquema correctamente!";
        }catch(Exception e){
            e.printStackTrace();
            status = "Al parecer ha habido un error a la hora de intentar borrar su esquema, favor de intentarlo mas tarde";
        }
        return status;
    }
    public static String saveEsquema(String url, Esquema esq){
       String status;
       ObjectMapper mapper = new ObjectMapper();
        try{
             File dir = new File(url+"/");
              File archivo = new File(url+"/esquema.json");
             dir.mkdirs();
           mapper.writeValue(archivo, esq);
            status = "Esquema guardado exitosamente!";
        }catch(Exception e){
            e.printStackTrace();
            status = "Al parecer no se ha podido guardar el esquema en el servidor, intentelo mas tarde";
        }
        return status;
    }

  

    /**
     * @return the totalPisos
     */
    public int getTotalPisos() {
        return totalPisos;
    }

    /**
     * @param totalPisos the totalPisos to set
     */
    public void setTotalPisos(int totalPisos) {
        this.totalPisos = totalPisos;
    }

    /**
     * @return the ObjetosEnEsquema
     */
    public ArrayList<ObjetoDeEsquema> getObjetosEnEsquema() {
        return ObjetosEnEsquema;
    }

    /**
     * @param ObjetosEnEsquema the ObjetosEnEsquema to set
     */
    public void setObjetosEnEsquema(ArrayList<ObjetoDeEsquema> ObjetosEnEsquema) {
        this.ObjetosEnEsquema = ObjetosEnEsquema;
    }

   
    
    
    
    
}
