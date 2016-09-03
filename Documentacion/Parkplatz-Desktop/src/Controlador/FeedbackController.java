/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import static Controlador.LoginController.est;
import ParkPlatzWSC.ClassNotFoundException_Exception;
import ParkPlatzWSC.Feedback;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    WebView webV = new WebView();
    @FXML
    TextField msg_txt = new TextField();
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
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
            WebEngine engine = webV.getEngine();
            engine.load("http://localhost:8080/Parkplatz-web/Administrador/chat.jsp");
         /*Runnable run = new ChatHilo(conver_txt);
         Thread hilo = new Thread(run);
         hilo.start();*/
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        lbl_nombreEst.setText(est.getNombreEsta());
    }    
    @FXML
    public void onClickEnviarChat(ActionEvent ev) throws ClassNotFoundException_Exception{
        enviarMensajeChat(est.getCorreo()+" - "+est.getNombreEsta(), msg_txt.getText());
        msg_txt.setText("");
        System.out.println("Enviado");
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
    public void subirFeedback(ActionEvent ev) throws ClassNotFoundException_Exception{
        Feedback feed = new Feedback();
        feed.setDescripcion(txt_desc.getText());
        CuadrosDialogo.successDialog(enviarFeed(feed, est));
        txt_desc.setText("");
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
    public void onClickConfig(ActionEvent ev) throws IOException{
        Parent root;
       
        root = FXMLLoader.load(getClass().getResource("/Vista/configCuenta.fxml"));
        
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
        stage.setTitle("Ofertas y Servicios.");
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
        stage.setTitle("Inicio.");
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
        stage.setTitle("Información de estacionamiento.");
        stage.getIcons().add(new Image("/Vista/Imagenes/parkLogo.png"));
        stage.setScene(scene);
        stage.show();
        ((Stage)btn_config.getScene().getWindow()).close();
    }

    private static String enviarFeed(ParkPlatzWSC.Feedback feedback, ParkPlatzWSC.Estacionamiento estacionamiento) throws ClassNotFoundException_Exception {
        ParkPlatzWSC.ParkPlatzWS_Service service = new ParkPlatzWSC.ParkPlatzWS_Service();
        ParkPlatzWSC.ParkPlatzWS port = service.getParkPlatzWSPort();
        return port.enviarFeed(feedback, estacionamiento);
    }

    private static void enviarMensajeChat(java.lang.String autor, java.lang.String mensaje) throws ClassNotFoundException_Exception {
        ParkPlatzWSC.ParkPlatzWS_Service service = new ParkPlatzWSC.ParkPlatzWS_Service();
        ParkPlatzWSC.ParkPlatzWS port = service.getParkPlatzWSPort();
        port.enviarMensajeChat(autor, mensaje);
    }

    
}
