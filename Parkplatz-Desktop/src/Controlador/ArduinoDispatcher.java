/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import static Controlador.LoginController.est;
import ParkPlatzWSC.Esquema;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;

/**
 *
 * @author Ivan
 */
public class ArduinoDispatcher implements Runnable{
     
 private static final String ON = "On";
 private static final String OFF = "Off";
 private static final String TURN_ON = "1";
 private static final String TURN_OFF = "0";
 

 SerialPort serialPort;
 private final String PORT_NAME = "COM5";
 /** Milliseconds to block while waiting for port open */
 private static final int TIME_OUT = 2000;
 /** Default bits per second for COM port. */
 private static final int DATA_RATE = 9600;
   CommPortIdentifier portId;
   Enumeration portEnum;
    long tiempo;
    ObservableList lista;
    Esquema esquemaGuardar;
    ArrayList hilos;
    public ArduinoDispatcher(){
        tiempo = 0;
         portId = null;
         portEnum = CommPortIdentifier.getPortIdentifiers();
        hilos = new ArrayList();
         // iterate through, looking for the port
            
    }
    @Override
    public void run() {
        try{
            System.out.println("Server iniciado");
            while(!Thread.currentThread().isInterrupted()){
                dispatch();
                Thread.sleep(1000);
            }
            for(int i = 0;i < hilos.size(); i++){
                Thread hilo = (Thread)hilos.get(i);
                hilo.interrupt();
            }
            throw new InterruptedException();
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
    private void dispatch(){
        while (portEnum.hasMoreElements()) {
                CommPortIdentifier currPortId = (CommPortIdentifier)portEnum.nextElement();

                if (PORT_NAME.equals(currPortId.getName())) {
                    portId = currPortId;
                    break;
                }
        }

            if (portId == null) {
                System.out.println("Could not find COM port.");
            }else{
                boolean ocupado = false;
                for(int i = 0;i < hilos.size(); i++){
                    Thread hilo = (Thread)hilos.get(i);
                    if(hilo.getName().equals(portId.toString())){
                        ocupado = true;
                    }
                }
                if(!ocupado){
                    try {
                        Runnable run = new ArduinoClient(portId);
                        Thread h = new Thread(run);
                        h.setName(portId.toString());
                        hilos.add(h);
                        h.start();
                    } catch (PortInUseException ex) {
                        Logger.getLogger(ArduinoDispatcher.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (UnsupportedCommOperationException ex) {
                        Logger.getLogger(ArduinoDispatcher.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                
            }
 
        
    }

}
