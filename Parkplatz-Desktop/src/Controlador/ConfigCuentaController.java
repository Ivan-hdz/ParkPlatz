/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import static Controlador.LoginController.est;
import Modelo.Database;
import ParkPlatzWSC.Estacionamiento;
import ParkPlatzWSC.NoSuchAlgorithmException_Exception;
import ParkPlatzWSC.UnsupportedEncodingException_Exception;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
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
    @FXML
    MenuItem btn_delEsq = new MenuItem();
    @FXML
    Button btn_vivo = new Button();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String img = "";
        try{
              if(est.getUrlImg().equals("default")){
                    img = "/Vista/Imagenes/home.jpg";
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
        stage.setTitle("IBEXSoftworks - ParkPlatz.");
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
        stage.setTitle("IBEXSoftworks - ParkPlatz.");
        stage.getIcons().add(new Image("/Vista/Imagenes/parkLogo.png"));
        stage.setScene(scene);
        stage.show();
        ((Stage)btn_config.getScene().getWindow()).close();
    }
    @FXML
    public void onClickVerEnVivo(ActionEvent ev) throws IOException{
         Parent root;
       
        root = FXMLLoader.load(getClass().getResource("/Vista/VerEnVivo.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("IBEXSoftworks - ParkPlatz.");
        stage.getIcons().add(new Image("/Vista/Imagenes/parkLogo.png"));
        stage.setScene(scene);
        stage.show();
        ((Stage)btn_config.getScene().getWindow()).close();
    }
    
    @FXML
    public void cerrar(ActionEvent ev) throws IOException{
        MenuPanelController.pararHilo();
        Parent root;
       
        root = FXMLLoader.load(getClass().getResource("/Vista/SelectEsta.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("IBEXSoftworks - ParkPlatz.");
        stage.getIcons().add(new Image("/Vista/Imagenes/parkLogo.png"));
        stage.setScene(scene);
        stage.show();
        ((Stage)btn_config.getScene().getWindow()).close();
    }
    @FXML
    public void actualizar(ActionEvent ev) throws NoSuchAlgorithmException_Exception, UnsupportedEncodingException_Exception{
        try {
            est.setPass(CuadrosDialogo.inputDialog("Favor de escribir su contraseña para confirmar la accion:"));
            est = loginEstacionamiento(est);
            if(est.isLogueado()){
                if(txt_pass.getText().equals("")){
                    est.setNombre(txt_nombre.getText());
                    est.setAPaterno(txt_aPaterno.getText());
                    est.setAMaterno(txt_aMaterno.getText());
                    est.setPass("sinCambios");
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
                    }
                }
            }else{
                CuadrosDialogo.errorDialog("Las contraseñas no coinciden.");
                ev.consume();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ConfigCuentaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(ConfigCuentaController.class.getName()).log(Level.SEVERE, null, ex);
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
        stage.setTitle("IBEXSoftworks - ParkPlatz.");
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
        stage.setTitle("IBEXSoftworks - ParkPlatz.");
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
        stage.setTitle("IBEXSoftworks - ParkPlatz.");
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
        stage.setTitle("IBEXSoftworks - ParkPlatz.");
        stage.getIcons().add(new Image("/Vista/Imagenes/parkLogo.png"));
        stage.setScene(scene);
        stage.show();
        ((Stage)btn_config.getScene().getWindow()).close();
    }
    @FXML
    public void onDelEsq(ActionEvent ev){
        try {
            if(!CuadrosDialogo.reallyyDialog("En verdad desea eliminar su esquema?")){
                CuadrosDialogo.infoDialog( delEsquema(est));
                try {
                    //Del todos de DB
                    Connection con = Database.conectar();
                    PreparedStatement ps = con.prepareStatement("call getMisLugares(?);");
                    ps.setInt(1, est.getIdEstacionamiento());
                    ResultSet rs = ps.executeQuery();
                    while(rs.next()){
                        PreparedStatement ps1 = con.prepareStatement("call bajaLugar(?);");
                        ps1.setInt(1, rs.getInt("ID"));
                        ps1.executeUpdate();
                    }
                    //fin
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ConfigCuentaController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(ConfigCuentaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                ev.consume();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ConfigCuentaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(ConfigCuentaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private static Estacionamiento actualizarDatos(ParkPlatzWSC.Estacionamiento objetoConDatosNuevos) throws NoSuchAlgorithmException_Exception, UnsupportedEncodingException_Exception {
        ParkPlatzWSC.ParkPlatzWS_Service service = new ParkPlatzWSC.ParkPlatzWS_Service();
        ParkPlatzWSC.ParkPlatzWS port = service.getParkPlatzWSPort();
        return port.actualizarDatos(objetoConDatosNuevos);
    }

    private static String delEsquema(ParkPlatzWSC.Estacionamiento delEsquema) {
        ParkPlatzWSC.ParkPlatzWS_Service service = new ParkPlatzWSC.ParkPlatzWS_Service();
        ParkPlatzWSC.ParkPlatzWS port = service.getParkPlatzWSPort();
        return port.delEsquema(delEsquema);
    }

    private static Estacionamiento loginEstacionamiento(ParkPlatzWSC.Estacionamiento objetoConUserPass) throws UnsupportedEncodingException_Exception, NoSuchAlgorithmException_Exception {
        ParkPlatzWSC.ParkPlatzWS_Service service = new ParkPlatzWSC.ParkPlatzWS_Service();
        ParkPlatzWSC.ParkPlatzWS port = service.getParkPlatzWSPort();
        return port.loginEstacionamiento(objetoConUserPass);
    }
}
