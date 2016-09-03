/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import javax.swing.SwingUtilities;

/**
 *
 * @author honte_000
 */
public class CuadrosDialogo {
    static String mensaje = "";
    public static String inputDialog(String mensajeAMostrar) throws InterruptedException, InvocationTargetException{
        final String mnsg = mensajeAMostrar;
        SwingUtilities.invokeAndWait(new Runnable() {
	    @Override
	    public void run() {
		mensaje = JOptionPane.showInputDialog(mnsg);
	    }
	});
        return mensaje;
    }
    public static void errorDialog(String mensajeAMostrar){
        try {
            final String mnsg = mensajeAMostrar;
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    JOptionPane.showMessageDialog(null, mnsg, "Error", ERROR_MESSAGE);
                }
            });
        } catch (InterruptedException ex) {
            Logger.getLogger(CuadrosDialogo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(CuadrosDialogo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private static boolean really ;
    public static boolean reallyyDialog(String mensaje) throws InterruptedException, InvocationTargetException{
        final String mnsg = mensaje;
       really = false;
        SwingUtilities.invokeAndWait(new Runnable() {
	    @Override
	    public void run() {
                                int opc = 0;
                                opc = JOptionPane.showConfirmDialog(null, mnsg, "Aviso", INFORMATION_MESSAGE);
                                if(opc == 0){
                                    really = false;
                                }else{
                                    really = true;
                                }
                            }
	});
        return really;
    }
    
    public static void infoDialog(String mensajeAMostrar){
        try {
            final String mnsg = mensajeAMostrar;
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    JOptionPane.showMessageDialog(null, mnsg, "Aviso", INFORMATION_MESSAGE);
                }
            });
        } catch (InterruptedException ex) {
            Logger.getLogger(CuadrosDialogo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(CuadrosDialogo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void successDialog(String mensaje){
        try {
            final String mnsg = mensaje;
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    JOptionPane.showMessageDialog(null, mnsg, "Éxito",INFORMATION_MESSAGE);
                }
            });
        } catch (InterruptedException ex) {
            Logger.getLogger(CuadrosDialogo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(CuadrosDialogo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
