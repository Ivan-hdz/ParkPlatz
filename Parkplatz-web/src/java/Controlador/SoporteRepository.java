/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.Beans.Esquema;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Ivan
 */
public class SoporteRepository {

    private ArrayList<Conversacion> conversaciones = new ArrayList<>();
    
    /**
     * @return the conversaciones
     */
    public ArrayList<Conversacion> getConversaciones() {
        return conversaciones;
    }

    /**
     * @param conversaciones the conversaciones to set
     */
    public void setConversaciones(ArrayList<Conversacion> conversaciones) {
        this.conversaciones = conversaciones;
    }

        
        //Methods
     public String save(String url){
       String status;
       ObjectMapper mapper = new ObjectMapper();
        try{
             File dir = new File(url+"/");
              File archivo = new File(url+"/supportLogs.json");
             dir.mkdirs();
           mapper.writeValue(archivo, this);
            status = "Esquema guardado exitosamente!";
        }catch(Exception e){
            e.printStackTrace();
            status = "Al parecer no se ha podido guardar el esquema en el servidor, intentelo mas tarde";
        }
        return status;
    }
     public SoporteRepository load(String url) throws FileNotFoundException, IOException{
       SoporteRepository supp = null;
       ObjectMapper mapper = new ObjectMapper();
        try{
           File archivo = new File(url+"/supportLogs.json");
           supp = mapper.readValue( archivo,  SoporteRepository.class);
            System.out.println(supp.getConversaciones().size());
           
        }catch(Exception e){
            e.printStackTrace();
        }
            return supp;
    }
}
