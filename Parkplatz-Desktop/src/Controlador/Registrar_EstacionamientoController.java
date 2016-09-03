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
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.w3c.dom.Document;
/**
 * FXML Controller class
 *
 * @author Dell
 */
public class Registrar_EstacionamientoController implements Initializable {

    WebEngine engine;
    @FXML
    WebView browser = new WebView();
    @FXML
    Button buscar = new Button();
    @FXML
    TextField calle = new TextField();
    @FXML
    TextField colonia = new TextField();
    @FXML
    TextField estado = new TextField();
    @FXML
    TextField del_muni = new TextField();
    @FXML
    Button btn_registrar = new Button();
    @FXML
    Button btn_cancelar = new Button();
    @FXML
    TextField nombre = new TextField();
    @FXML
    TextField nombreEst = new TextField();
    @FXML
    TextField aPaterno = new TextField();
    @FXML
    TextField aMaterno = new TextField();
    @FXML
    PasswordField pass = new PasswordField();
    @FXML
    PasswordField Cpass = new PasswordField();
    @FXML
    TextField correo = new TextField();
    @FXML
    Pane pane_status = new Pane();
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       browser.setDisable(true);
       engine = browser.getEngine();
       engine.load("http://localhost/Parkplatz-web/assets/JSP/GMap.jsp?direccion=1&editable=si");
    }    
    @FXML
    public void cancelar(ActionEvent ev) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/Vista/vLogin.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Iniciar sesión.");
        stage.getIcons().add(new Image("/Vista/Imagenes/parkLogo.png"));
        stage.setScene(scene);
        stage.show();
        ((Stage)correo.getScene().getWindow()).close();
    }
    @FXML
    public void buscar(ActionEvent ev){
        browser.setDisable(false);
        String direccion;
        direccion = calle.getText()+", "+colonia.getText()+", "+del_muni.getText()+", "+estado.getText();
        engine.load("http://localhost/Parkplatz-web/assets/JSP/GMap.jsp?direccion="+direccion+"&editable=si");
        
    }
    @FXML
    public void registrar(ActionEvent ev) throws IOException{
        
        
        try {
            if(!pass.getText().equals(Cpass.getText())){
                CuadrosDialogo.errorDialog("Las contraseñas que ha introducido no coinciden, favor de verificarlas.");
                pass.requestFocus();
                pass.setStyle("-fx-border-color: darkred");
                Cpass.setStyle("-fx-border-color: darkred");
            }else{
                String limpiandoCoors = "";
                System.out.println(engine.getTitle());
                Estacionamiento est = new Estacionamiento();
                est.setNombre(nombre.getText());
                est.setAPaterno(aPaterno.getText());
                est.setAMaterno(aMaterno.getText());
                est.setCalle(calle.getText());
                est.setColonia(colonia.getText());
                est.setCorreo(correo.getText());
                est.setDelMuni(del_muni.getText());
                est.setEstado(estado.getText());
                est.setNombreEsta(nombreEst.getText());
                est.setPass(pass.getText());
                String titulo = engine.getTitle();
                for(int i = 0; i<titulo.length(); i++){
                    if(titulo.charAt(i) == '(' || titulo.charAt(i) == ')' || titulo.charAt(i) == ' ' ){

                    }else{
                        limpiandoCoors += String.valueOf(titulo.charAt(i));
                    }
                }
                String coors[] = limpiandoCoors.split(",");
                est.setCordenadaX(Double.parseDouble(coors[0]));
                est.setCordenadaY(Double.parseDouble(coors[1]));
                boolean paso = registrarEstacionamiento(est);
                
                if(paso == true){
                    pane_status.setStyle("-fx-background-color: green");
                    cancelar(ev);
                }else{
                    CuadrosDialogo.errorDialog("Ocurrio un error favor de intentar mas tarde.");
                }
            }
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static boolean registrarEstacionamiento(ParkPlatzWSC.Estacionamiento objetoLleno) throws NoSuchAlgorithmException_Exception, UnsupportedEncodingException_Exception {
        ParkPlatzWSC.ParkPlatzWS_Service service = new ParkPlatzWSC.ParkPlatzWS_Service();
        ParkPlatzWSC.ParkPlatzWS port = service.getParkPlatzWSPort();
        return port.registrarEstacionamiento(objetoLleno);
    }
}
