/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.LoginController;
import static Controlador.LoginController.est;
import Modelo.Database;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 *
 * @author honte_000
 */
public class MenuPanelController implements Initializable{

    @FXML
    Label lbl_nombreEst = new Label();
    @FXML
    WebView browser = new WebView();
    WebEngine engine;
    @FXML
    Button btn_config = new Button();
    @FXML
    Button btn_servicio = new Button();
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
   public static Thread hilo = new Thread();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarAssets();
        if(!hilo.isAlive()){
        iniciarHilo();    
        }
        
    }
    public static void iniciarHilo(){
        Runnable run = new ArduinoDispatcher();
        hilo = new Thread(run);
        hilo.start();
    }
    public static void pararHilo(){
        hilo.interrupt();
        
       
    }
    private void cargarAssets(){
        String img = "";
        try{
                if(est.getUrlImg().equals("default")){
                    img = "/Vista/Imagenes/home.jpg";
                }else{
                File f = new File(est.getUrlImg());
                URL url = f.toURI().toURL();
               img = url.toExternalForm();
                }
           
            imgV.setImage(new Image(img, 200, 150, true, true));
         
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        lbl_nombreEst.setText(est.getNombreEsta());
        engine = browser.getEngine();
        String direccion = LoginController.est.getCalle()+", "+LoginController.est.getColonia()+", "+LoginController.est.getEstado()+", "+LoginController.est.getDelMuni();
        String url = "http://localhost/Parkplatz-web/assets/JSP/GMap.jsp?direccion="+direccion+"&editable=no";
        engine.load(url);
        
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
    public void cerrar(ActionEvent ev) throws IOException{
        pararHilo();
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
    public void onClickConfig(ActionEvent ev) throws IOException{
        Parent root;
       
            root = FXMLLoader.load(getClass().getResource("/Vista/configCuenta.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("IBEXSoftworks - ParkPlatz.");
        stage.getIcons().add(new Image("/Vista/Imagenes/parkLogo.png"));
        stage.setScene(scene);
        stage.show();
        ((Stage)browser.getScene().getWindow()).close();
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
        ((Stage)browser.getScene().getWindow()).close();
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
    public void onClickFeed(ActionEvent ev) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/Vista/Feedback.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("IBEXSoftworks - ParkPlatz.");
        stage.getIcons().add(new Image("/Vista/Imagenes/parkLogo.png"));
        stage.setScene(scene);
        stage.show();
        ((Stage)browser.getScene().getWindow()).close();
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
            Logger.getLogger(MenuPanelController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(MenuPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static String delEsquema(ParkPlatzWSC.Estacionamiento delEsquema) {
        ParkPlatzWSC.ParkPlatzWS_Service service = new ParkPlatzWSC.ParkPlatzWS_Service();
        ParkPlatzWSC.ParkPlatzWS port = service.getParkPlatzWSPort();
        return port.delEsquema(delEsquema);
    }
  
}
