/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;


import ParkPlatzWSC.Estacionamiento;
import ParkPlatzWSC.NoSuchAlgorithmException_Exception;
import ParkPlatzWSC.UnsupportedEncodingException_Exception;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author artik
 */
public class LoginController implements Initializable{

    /**
     * Initializes the controller class.
     */
    public static Estacionamiento est;
    
    @FXML
    Hyperlink recuperaContra = new Hyperlink();
    @FXML
    TextField correo = new TextField();
    @FXML
    PasswordField contra = new PasswordField();
    @FXML
    Label lbl_estado = new Label("");
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        correo.requestFocus();
        est = new Estacionamiento();
       
    }
    @FXML
    public void onClickRecover(ActionEvent ev) throws InterruptedException, InvocationTargetException{
        
        String email = CuadrosDialogo.inputDialog("Introduzca su correo por favor:");
       
        boolean exito = recover(email);
       if(exito == true){
           CuadrosDialogo.successDialog("Se le ha enviado un correo de recuperación de contraseña.");
       }else{
           CuadrosDialogo.errorDialog("Hubo un error, favor de intentarlo más tarde.");
       }
    }
    @FXML
    public void onEnter(ActionEvent ev) throws UnsupportedEncodingException_Exception, NoSuchAlgorithmException_Exception, IOException{
        onClickIniciar(ev);
    }
    @FXML
    public void onClickRegistrar(ActionEvent ev) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Vista/Registrar_Estacionamiento.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Registrar estacionamiento.");
        stage.getIcons().add(new Image("/Vista/Imagenes/parkLogo.png"));
        stage.setScene(scene);
        stage.show();
        ((Stage)correo.getScene().getWindow()).close();
    }
    @FXML
    public void onClickIniciar(ActionEvent ev) throws UnsupportedEncodingException_Exception, NoSuchAlgorithmException_Exception, IOException{
       est.setCorreo(correo.getText());
       est.setPass(contra.getText());
        est = loginEstacionamiento(est);
        
       if(est.isLogueado()){
        Parent root = FXMLLoader.load(getClass().getResource("/Vista/SelectEsta.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Seleccione el estacionamiento a administrar.");
        stage.getIcons().add(new Image("/Vista/Imagenes/parkLogo.png"));
        stage.setScene(scene);
        stage.show();
        ((Stage)correo.getScene().getWindow()).close();
       }else{
           CuadrosDialogo.errorDialog("Correo o contraseña incorrecta.");
       }
    }
    private static Estacionamiento loginEstacionamiento(ParkPlatzWSC.Estacionamiento objetoConUserPass) throws UnsupportedEncodingException_Exception, NoSuchAlgorithmException_Exception {
        ParkPlatzWSC.ParkPlatzWS_Service service = new ParkPlatzWSC.ParkPlatzWS_Service();
        ParkPlatzWSC.ParkPlatzWS port = service.getParkPlatzWSPort();
        return port.loginEstacionamiento(objetoConUserPass);
    }

    private static boolean recover(java.lang.String correo) {
        ParkPlatzWSC.ParkPlatzWS_Service service = new ParkPlatzWSC.ParkPlatzWS_Service();
        ParkPlatzWSC.ParkPlatzWS port = service.getParkPlatzWSPort();
        return port.recover(correo);
    }

    
}
