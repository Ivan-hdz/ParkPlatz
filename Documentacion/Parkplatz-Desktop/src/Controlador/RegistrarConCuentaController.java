/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import static Controlador.LoginController.est;
import ParkPlatzWSC.ClassNotFoundException_Exception;
import ParkPlatzWSC.Estacionamiento;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author honte_000
 */
public class RegistrarConCuentaController implements Initializable {

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
    TextField nombreEst = new TextField();
    @FXML
    Label status = new Label();
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       browser.setDisable(true);
       engine = browser.getEngine();
       engine.load("http://localhost:8080/Parkplatz-web/assets/JSP/GMap.jsp?direccion=1&editable=si");
    }    
    @FXML
    public void cancelar(ActionEvent ev) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/Vista/SelectEsta.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Seleccione el estacionamiento a administrar.");
        stage.getIcons().add(new Image("/Vista/Imagenes/parkLogo.png"));
        stage.setScene(scene);
        stage.show();
        ((Stage)browser.getScene().getWindow()).close();
    }
    @FXML
    public void buscar(ActionEvent ev){
        browser.setDisable(false);
        String direccion;
        direccion = calle.getText()+", "+colonia.getText()+", "+del_muni.getText()+", "+estado.getText();
        System.out.println(direccion);
        
        engine.load("http://localhost:8080/Parkplatz-web/assets/JSP/GMap.jsp?direccion="+direccion+"&editable=si");
        
    }
    @FXML
    public void registrar(ActionEvent ev) throws IOException{
        
        
        try {
            
                String limpiandoCoors = "";
                System.out.println(engine.getTitle());
                Estacionamiento esta = new Estacionamiento();
                esta.setCorreo(est.getCorreo());
                esta.setCalle(calle.getText());
                esta.setColonia(colonia.getText());
                esta.setDelMuni(del_muni.getText());
                esta.setEstado(estado.getText());
                esta.setNombreEsta(nombreEst.getText());
                System.out.println(nombreEst.getText());
                String titulo = engine.getTitle();
                for(int i = 0; i<titulo.length(); i++){
                    if(titulo.charAt(i) == '(' || titulo.charAt(i) == ')' || titulo.charAt(i) == ' ' ){

                    }else{
                        limpiandoCoors += String.valueOf(titulo.charAt(i));
                    }
                }
                String coors[] = limpiandoCoors.split(",");
                esta.setCordenadaX(Double.parseDouble(coors[0]));
                esta.setCordenadaY(Double.parseDouble(coors[1]));
                boolean paso = registraUnEstacionamiento(esta);
                System.out.println(paso);
                if(paso == true){
                    status.setText("Se ha registrado con exito!");
                    status.setStyle("-fx-background-color: green");
                    cancelar(ev);
                }else{
                    status.setText("Hubo un error");
                    status.setStyle("-fx-background-color: red; -fx-color: white");
                    
                }
            
            
        } catch (NumberFormatException | ClassNotFoundException_Exception | IOException ex ) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    private static boolean registraUnEstacionamiento(ParkPlatzWSC.Estacionamiento objetoConDatosNvoEst) throws ClassNotFoundException_Exception {
        ParkPlatzWSC.ParkPlatzWS_Service service = new ParkPlatzWSC.ParkPlatzWS_Service();
        ParkPlatzWSC.ParkPlatzWS port = service.getParkPlatzWSPort();
        return port.registraUnEstacionamiento(objetoConDatosNvoEst);
    }

}
