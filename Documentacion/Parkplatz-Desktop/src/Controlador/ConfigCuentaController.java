/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import static Controlador.LoginController.est;
import ParkPlatzWSC.Estacionamiento;
import ParkPlatzWSC.NoSuchAlgorithmException_Exception;
import ParkPlatzWSC.UnsupportedEncodingException_Exception;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Alumno
 */
public class ConfigCuentaController implements Initializable {
    @FXML
    Label lbl_nombreEst = new Label();
    @FXML
    Button btn_config = new Button();
    @FXML
    TextField txt_nombre = new TextField(est.getNombre());
    @FXML 
    TextField txt_aPaterno = new TextField(est.getAPaterno());
    @FXML 
    TextField txt_aMaterno = new TextField(est.getAMaterno());
    @FXML
    PasswordField txt_pass;
    @FXML
    PasswordField txt_cPass;
    @FXML
    Label lbl_estado = new Label("");
    @FXML
    Button btn_salir = new Button();
    @FXML
    Button btn_info = new Button();
    @FXML
    ImageView imgV = new ImageView();
    @FXML
    Button btn_feed = new Button();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String img = "";
        try{
            if(est.getUrlImg()== null){
                img = "/Vista/Imagenes/autoLogin.jpg";
            }else{
               File f = new File(est.getUrlImg());
                URL ur = f.toURI().toURL();
               img = ur.toExternalForm();
            }
            imgV.setImage(new Image(img, 200, 150, true, true));
         
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        
        txt_nombre.setText(est.getNombre());
        txt_aPaterno.setText(est.getAPaterno());
        txt_aMaterno.setText(est.getAMaterno());
        lbl_nombreEst.setText(est.getNombreEsta());
    }    
     @FXML
    public void onClickEsquema(ActionEvent ev) throws IOException{
         Parent root;
       
        root = FXMLLoader.load(getClass().getResource("/Vista/Mi_Esquema.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Mi esquema.");
        stage.getIcons().add(new Image("/Vista/Imagenes/parkLogo.png"));
        stage.setScene(scene);
        stage.show();
        ((Stage)btn_config.getScene().getWindow()).close();
    }
    @FXML
    public void onClickInfo(ActionEvent ev) throws IOException{
         Parent root;
       
        root = FXMLLoader.load(getClass().getResource("/Vista/InformacionEstacionamiento.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Información de estacionamiento.");
        stage.getIcons().add(new Image("/Vista/Imagenes/parkLogo.png"));
        stage.setScene(scene);
        stage.show();
        ((Stage)btn_config.getScene().getWindow()).close();
    }
    @FXML
    public void cerrar(ActionEvent ev) throws IOException{
         Parent root;
       
        root = FXMLLoader.load(getClass().getResource("/Vista/SelectEsta.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Seleccione el estacionamiento a administrar.");
        stage.getIcons().add(new Image("/Vista/Imagenes/parkLogo.png"));
        stage.setScene(scene);
        stage.show();
        ((Stage)btn_config.getScene().getWindow()).close();
    }
    @FXML
    public void actualizar(ActionEvent ev) throws NoSuchAlgorithmException_Exception, UnsupportedEncodingException_Exception{
        if(txt_pass.getText().equals("")){
            est.setNombre(txt_nombre.getText());
            est.setAPaterno(txt_aPaterno.getText());
            est.setAMaterno(txt_aMaterno.getText());
            est.setPass("0");
            est = actualizarDatos(est);
            txt_nombre.setText(est.getNombre());
            txt_aPaterno.setText(est.getAPaterno());
            txt_aMaterno.setText(est.getAMaterno());
            CuadrosDialogo.successDialog("Operacion exitosa!");
        }else{
            if(txt_cPass.getText().equals(txt_pass.getText())){
                est.setNombre(txt_nombre.getText());
                est.setAPaterno(txt_aPaterno.getText());
                est.setAMaterno(txt_aMaterno.getText());
                est.setPass(txt_pass.getText());
                est = actualizarDatos(est);
                txt_nombre.setText(est.getNombre());
                txt_aPaterno.setText(est.getAPaterno());
                txt_aMaterno.setText(est.getAMaterno());
                CuadrosDialogo.successDialog("Operación exitosa!");
            }else{
                CuadrosDialogo.errorDialog("Las contraseñas no coinciden.");
            }
        }
    }
 
    @FXML
    public void onClickConfig(ActionEvent ev){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/Vista/configCuenta.fxml"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage()+getClass().getName());
        }
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Configurar cuenta.");
        stage.getIcons().add(new Image("/Vista/Imagenes/parkLogo.png"));
        stage.setScene(scene);
        stage.show();
        ((Stage)btn_config.getScene().getWindow()).close();
    }
    @FXML
    public void onClickServ(ActionEvent ev) throws IOException{
        
        
        Parent root = FXMLLoader.load(getClass().getResource("/Vista/Servicios_Ofertas_Crear.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Ofertas y servicios.");
        stage.getIcons().add(new Image("/Vista/Imagenes/parkLogo.png"));
        stage.setScene(scene);
        stage.show();
        ((Stage)btn_config.getScene().getWindow()).close();
    }
    @FXML
    public void onClickInicio(ActionEvent ev) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/Vista/MenuPanel.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Inicio");
        stage.getIcons().add(new Image("/Vista/Imagenes/parkLogo.png"));
        stage.setScene(scene);
        stage.show();
        ((Stage)btn_config.getScene().getWindow()).close();
    }
    @FXML
    public void onClickFeed(ActionEvent ev) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/Vista/Feedback.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Enviar Feedback.");
        stage.getIcons().add(new Image("/Vista/Imagenes/parkLogo.png"));
        stage.setScene(scene);
        stage.show();
        ((Stage)btn_config.getScene().getWindow()).close();
    }
    private static Estacionamiento actualizarDatos(ParkPlatzWSC.Estacionamiento objetoConDatosNuevos) throws NoSuchAlgorithmException_Exception, UnsupportedEncodingException_Exception {
        ParkPlatzWSC.ParkPlatzWS_Service service = new ParkPlatzWSC.ParkPlatzWS_Service();
        ParkPlatzWSC.ParkPlatzWS port = service.getParkPlatzWSPort();
        return port.actualizarDatos(objetoConDatosNuevos);
    }
}
