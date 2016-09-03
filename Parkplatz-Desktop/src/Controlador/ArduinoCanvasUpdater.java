/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import static Controlador.VerEnVivoController.alive;
import Modelo.Database;
import ParkPlatzWSC.Estacionamiento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author Ivan
 */
public class ArduinoCanvasUpdater implements Runnable{
    Connection con;

    ObservableList listToUpdate;
    Estacionamiento est;
    public ArduinoCanvasUpdater(ObservableList listToUpdate, Estacionamiento est){
        try {
            Platform.setImplicitExit(false);
            this.est = est;
            this.listToUpdate =  listToUpdate;
           this. con = Database.conectar();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ArduinoCanvasUpdater.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ArduinoCanvasUpdater.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
               if(alive == false){
                   throw new InterruptedException();
                }
                actualizarEstado();
                Thread.sleep(1000);
            } catch (InterruptedException ex ) {
                Thread.currentThread().interrupt();
            }
        }
    }
    private void actualizarEstado(){
        try {
            PreparedStatement ps = con.prepareStatement("call getMisLugares(?)");
            ps.setInt(1, est.getIdEstacionamiento());   
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                for(int i = 0; i<listToUpdate.size(); i++){
                    Canvas can = (Canvas)listToUpdate.get(i);
                    ArrayList userData = (ArrayList) can.getUserData();
                    String idNum = userData.get(2).toString().split(";")[0];
                    String status = rs.getString("Status");
                    
                    if(String.valueOf(rs.getInt("ID")).equals(idNum)){
                       if(status.equals("Fuera de Servicio")){
                           setArduinoStatus("FueraDeServicio", userData);
                       }else if(status.equals("Sin asignar")){
                           setArduinoStatus("unknownStatus", userData);
                       }else {
                           setArduinoStatus(rs.getString("Status"), userData);
                       }
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArduinoCanvasUpdater.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void setArduinoStatus(String status, ArrayList canvasData){
        try {
           /*
           userData.get(0) ---> Canvas img name
           userData.get(1) ---> Floor number
           userData.get(2) ---> id
           */
            
           String urlImgNueva = "";
           final String oldID = String.valueOf(canvasData.get(2));
           String idString = String.valueOf(canvasData.get(2));
           String[] datosDeId = idString.split(";");
           if(status.equals("Disponible")){
               datosDeId[3] = "available";
               urlImgNueva = "on.png"; //Imagen supuesta
           }else if(status.equals("Ocupado")){
               datosDeId[3] = "busy";
               urlImgNueva = "alert.png"; //Imagen supuesta
          }else if(status.equals("FueraDeServicio")){
               datosDeId[3] = "outOfOrder";
               urlImgNueva = "off.png"; //Imagen supuesta
           }else if(status.equals("unknownStatus")){
               urlImgNueva = "default";
           }
          
           //Se reconstruye el idString con los nuevos datos
           idString = "";
           for(int i = 0; i<datosDeId.length; i++){
               idString += datosDeId[i]+";";
           }
           
           
           //Se tiene que actualizar el canvas afectado en el esquema
           ObservableList objects = listToUpdate;
           for(int i = 0; i <objects.size(); i++){
               Canvas canv = (Canvas)objects.get(i);
               ArrayList userData =  (ArrayList)canv.getUserData();
               if(userData.get(2).toString().equals(oldID)){
                   if(urlImgNueva.equals("default")){
                       if(datosDeId[1].endsWith("T")){
                           urlImgNueva = "arduinoT.png";
                       }else if(datosDeId[1].endsWith("B")){
                           urlImgNueva = "arduinoB.png";
                       } else if(datosDeId[1].endsWith("L")){
                           urlImgNueva = "arduinoL.png";
                       } else if(datosDeId[1].endsWith("R")){
                           urlImgNueva = "arduinoR.png";
                       }
                       canv.setUserData(canvasData);
                       GraphicsContext gc = canv.getGraphicsContext2D();
                       gc.fill();
                       Image img = new Image("/Vista/Imagenes/"+urlImgNueva, 48,48, true, true);
                       gc.drawImage(img,0,0);
                       
                   }else{
                       canv.setUserData(canvasData);
                       GraphicsContext gc = canv.getGraphicsContext2D();
                       Image img = new Image("/Vista/Imagenes/"+urlImgNueva);
                       gc.drawImage(img, 0, 0, 20, 20);
                       
                   }
                   final ObservableList objectsFinal = objects;
                   final int j = i;
                   final Canvas canvFinal = canv;
                   Platform.runLater(new Runnable() {
                       @Override
                       public void run() {
                          objectsFinal.set(j, canvFinal);
                       }
                   });
               break;
               }
           }
           
       } catch (Exception ex) {
           Logger.getLogger(VerEnVivoController.class.getName()).log(Level.SEVERE, null, ex);
       }
}
}
