/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.lang.reflect.InvocationTargetException;
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
        final String mnsg = mensajeAMostrar;
        SwingUtilities.invokeLater(new Runnable() {
	    @Override
	    public void run() {
		JOptionPane.showMessageDialog(null, mnsg, "Error", ERROR_MESSAGE);
	    }
	});
    }
    public static void infoDialog(String mensajeAMostrar){
        final String mnsg = mensajeAMostrar;
        SwingUtilities.invokeLater(new Runnable() {
	    @Override
	    public void run() {
		JOptionPane.showMessageDialog(null, mnsg, "Aviso", INFORMATION_MESSAGE);
	    }
	});
    }
    public static void successDialog(String mensaje){
        final String mnsg = mensaje;
        SwingUtilities.invokeLater(new Runnable() {
	    @Override
	    public void run() {
		JOptionPane.showMessageDialog(null, mnsg, "Éxito",INFORMATION_MESSAGE);
	    }
	});
    }
}
