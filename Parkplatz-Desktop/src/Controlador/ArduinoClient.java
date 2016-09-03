/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import static Controlador.LoginController.est;
import Modelo.Database;
import ParkPlatzWSC.Esquema;
import ParkPlatzWSC.ObjetoDeEsquema;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;

/**
 *
 * @author Ivan
 */
public class ArduinoClient implements Runnable{
    ArrayList<Integer> lista;
    Scanner in;
    DataOutputStream out;
    long tiempo;
    int IdNumLugar;
    boolean emparejado;
 Connection con;
    SerialPort serialPort;
    public ArduinoClient(CommPortIdentifier portId) throws PortInUseException, UnsupportedCommOperationException{
        try {
            lista = new ArrayList<>();
            emparejado = false;
            serialPort = (SerialPort) portId.open(this.getClass().getName(), 20000);
            // set port parameters
            serialPort.setSerialPortParams(9600,
            SerialPort.DATABITS_8,
            SerialPort.STOPBITS_1,
            SerialPort.PARITY_NONE);
            
            con = Database.conectar();
            in = new Scanner(serialPort.getInputStream());
            out = new DataOutputStream(serialPort.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(ArduinoClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ArduinoClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ArduinoClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void run() {
        try {
            
            System.out.println("Thread corriendo");
            
            while(!Thread.currentThread().isInterrupted()){
                System.out.println("escuchando");
                
                if(emparejado == false){
                    emparejarID();
                }else{
                    manejarAcciones();
                }
                Thread.sleep(1000);
            }
            serialPort.close();
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
            serialPort.close();
        }
    }
    private void manejarAcciones(){
        System.out.println("Escuchando acciones");
        try {
            /*
            userData.get(0) ---> Canvas img name
            userData.get(1) ---> Floor number
            userData.get(2) ---> id
            */
            String mensajeEntrada = in.nextLine(); //Entrada de datos
            System.out.println(mensajeEntrada);
            String urlImgNueva = "";
            PreparedStatement ps = con.prepareStatement("call updateLugarStatus(?,?);");
            ps.setInt(1, IdNumLugar);
            if(mensajeEntrada.equals("available")){
                ps.setInt(2, 1); //Se modifica el estado en la DB
                ps.executeUpdate();
            }else if(mensajeEntrada.equals("busy")){
                ps.setInt(2, 2); //Se modifica el estado en la DB
                ps.executeUpdate();
            }else if(mensajeEntrada.equals("FueraDeServicio")){
                ps.setInt(2, 3); //Se modifica el estado en la DB
                ps.executeUpdate();
            }else if(mensajeEntrada.equals("unknownStatus")){
                ps.setInt(2, 0); //Se modifica el estado en la DB
                ps.executeUpdate();
            }
            //Se reconstruye el idString con los nuevos datos
            
        } catch (SQLException ex) {
            Logger.getLogger(VerEnVivoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void emparejarID(){
        try {
            System.out.println("Emparejando...");
            PreparedStatement ps = con.prepareStatement("call parkplatz.getMisLugares(?);");
            ps.setInt(1, est.getIdEstacionamiento());
            ResultSet rs = ps.executeQuery();
           lista.clear();
            while(rs.next()){
                
                lista.add(rs.getInt(1));
            }
            String mensajeEntrada = in.nextLine(); //Entrada de datos
            
            System.out.println(mensajeEntrada);
            for(int i =0; i<lista.size(); i++){
                
                if(String.valueOf(lista.get(i)).equals(mensajeEntrada)){
                    emparejado = true;
                   IdNumLugar = lista.get(i);
                    out.write('E');
                    break;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
  
    private static Esquema getEsquema(ParkPlatzWSC.Estacionamiento estacionamiento) {
        ParkPlatzWSC.ParkPlatzWS_Service service = new ParkPlatzWSC.ParkPlatzWS_Service();
        ParkPlatzWSC.ParkPlatzWS port = service.getParkPlatzWSPort();
        return port.getEsquema(estacionamiento);
    }

    private static String saveEsquema(ParkPlatzWSC.Estacionamiento estacionamiento, ParkPlatzWSC.Esquema esquema) {
        ParkPlatzWSC.ParkPlatzWS_Service service = new ParkPlatzWSC.ParkPlatzWS_Service();
        ParkPlatzWSC.ParkPlatzWS port = service.getParkPlatzWSPort();
        return port.saveEsquema(estacionamiento, esquema);
    }
    
}
