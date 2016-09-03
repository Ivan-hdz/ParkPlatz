/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import ParkPlatzWSC.ClassNotFoundException_Exception;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 *
 * @author Dell
 */
public class ChatHilo implements Runnable {
    ArrayList<String> conversa;
    Label txt_a;
    public ChatHilo(Label txt){
        conversa = new ArrayList<>();
        txt_a = txt;
    }
    @Override
    public void run() {
        String ultima = "";
        while(true){
            try {
                conversa = (ArrayList<String>) recibirMensajeChat();
                txt_a.setText("");
                for(int i = 0; i<conversa.size(); i++){
                    if(!conversa.get(i).equals(ultima)){
                        if(conversa.get(i)==null){
                            break;
                        }else{
                        txt_a.setText(txt_a.getText()+"\n"+conversa.get(i));
                        ultima = conversa.get(i);
                        }
                    }
                }
            } catch (ClassNotFoundException_Exception ex) {
                Logger.getLogger(ChatHilo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private static java.util.List<java.lang.String> recibirMensajeChat() throws ClassNotFoundException_Exception {
        ParkPlatzWSC.ParkPlatzWS_Service service = new ParkPlatzWSC.ParkPlatzWS_Service();
        ParkPlatzWSC.ParkPlatzWS port = service.getParkPlatzWSPort();
        return port.recibirMensajeChat();
    }
    
}
