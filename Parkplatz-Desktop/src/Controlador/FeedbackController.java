/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import static Controlador.LoginController.est;
import Modelo.Database;
import ParkPlatzWSC.ClassNotFoundException_Exception;
import ParkPlatzWSC.Feedback;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Line;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author honte_000
 */
public class FeedbackController implements Initializable {

    @FXML
    Label lbl_aviso = new Label();
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
    Label lbl_nombreEst = new Label();
    @FXML
    Button btn_inicio = new Button();
    @FXML
    TextArea txt_desc = new TextArea();
    @FXML
    Button btn_enviar = new Button();
    @FXML
    TextField msg_txt = new TextField();
    @FXML
    FlowPane pane_aviso = new FlowPane();
    @FXML
    Button btn_vivo = new Button();
    @FXML
     MenuItem btn_delEsq = new MenuItem();
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
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
         
            dibujarAviso();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
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
    public void subirFeedback(ActionEvent ev) throws ClassNotFoundException_Exception{
        Feedback feed = new Feedback();
        feed.setDescripcion(txt_desc.getText());
        CuadrosDialogo.successDialog(enviarFeed(feed, est));
        txt_desc.setText("");
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
    public void onClickConfig(ActionEvent ev) throws IOException{
        Parent root;
       
        root = FXMLLoader.load(getClass().getResource("/Vista/configCuenta.fxml"));
        
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
        ((Stage)btn_inicio.getScene().getWindow()).close();
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
            Logger.getLogger(FeedbackController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(FeedbackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void dibujarAviso(){
        ArrayList<Feedback> avisos = (ArrayList<Feedback>) avisos(est);
        for(int i = 0; i<avisos.size(); i++){
            Label lblAdmin = new Label("Administrador:");
            Feedback aviso = avisos.get(i);
            Label lblDesc = new Label(aviso.getDescripcion());
            Label lblFecha = new Label(aviso.getFecha());
            Line separador = new Line();
            separador.setEndX(360);
            pane_aviso.getChildren().addAll(lblAdmin, lblDesc, lblFecha, separador);
        }
    }
    
    private static String enviarFeed(ParkPlatzWSC.Feedback feedback, ParkPlatzWSC.Estacionamiento estacionamiento) throws ClassNotFoundException_Exception {
        ParkPlatzWSC.ParkPlatzWS_Service service = new ParkPlatzWSC.ParkPlatzWS_Service();
        ParkPlatzWSC.ParkPlatzWS port = service.getParkPlatzWSPort();
        return port.enviarFeed(feedback, estacionamiento);
    }

    private static java.util.List<ParkPlatzWSC.Feedback> avisos(ParkPlatzWSC.Estacionamiento estacionamientoActual) {
        ParkPlatzWSC.ParkPlatzWS_Service service = new ParkPlatzWSC.ParkPlatzWS_Service();
        ParkPlatzWSC.ParkPlatzWS port = service.getParkPlatzWSPort();
        return port.avisos(estacionamientoActual);
    }

    private static String delEsquema(ParkPlatzWSC.Estacionamiento delEsquema) {
        ParkPlatzWSC.ParkPlatzWS_Service service = new ParkPlatzWSC.ParkPlatzWS_Service();
        ParkPlatzWSC.ParkPlatzWS port = service.getParkPlatzWSPort();
        return port.delEsquema(delEsquema);
    }


    
}
