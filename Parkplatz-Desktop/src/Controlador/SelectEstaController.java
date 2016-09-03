/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import static Controlador.LoginController.est;
import ParkPlatzWSC.Estacionamiento;
import ParkPlatzWSC.ClassNotFoundException_Exception;
import ParkPlatzWSC.NoSuchAlgorithmException_Exception;
import ParkPlatzWSC.UnsupportedEncodingException_Exception;
import java.awt.Paint;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author honte_000
 */
public class SelectEstaController implements Initializable {

    @FXML
    ImageView imgV;
    @FXML
    FlowPane pane = new FlowPane();
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            carga();
              System.out.println(est.getCorreo());
            est = obtenerMisEstacionamientos(est);
            pane.setOrientation(Orientation.HORIZONTAL);
          
            pintarEstacionamientos();
        }catch(Exception e){
            System.out.println(e.getMessage()+"<---Desde selectEst en init");
        }
    }
    private void carga(){
        Image imgLoading = new Image("/Vista/Imagenes/progress.gif",100, 100, true, true);
        imgV.setFitWidth(imgLoading.getWidth());
        imgV.setFitHeight(imgLoading.getHeight());
        imgV.setImage(imgLoading);
    }
    private void pintarEstacionamientos(){
        try{
            
            ArrayList<Estacionamiento> misEstos = (ArrayList<Estacionamiento>) est.getMisEstacionamientos();
            for(int i = 0; i<misEstos.size(); i++){
                Estacionamiento miEst = misEstos.get(i);
                moldeEsta(miEst.getNombreEsta(), miEst.getIdEstacionamiento(), miEst.getUrlImg());
                
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        imgV.setVisible(false);
        imgV.setDisable(true);
        imgV.toBack();
        imgV = null;
    }
    private void moldeEsta(String nombreEst, int idEst, String img) throws MalformedURLException{
        Canvas canvas = new Canvas();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        System.out.println(img);
        if(img.equals("default")){
            img = "/Vista/Imagenes/home.jpg";
        }else{
                   File f = new File(img);
                URL url = f.toURI().toURL();
               img = url.toExternalForm();
        }
          Image imgEst = new Image(img,200, 200, true, true);
        System.out.println(nombreEst);
        canvas.setWidth(imgEst.getWidth());
        canvas.setHeight(imgEst.getHeight()+25);
        canvas.setAccessibleHelp(String.valueOf(idEst));
        canvas.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Canvas interno = (Canvas)event.getSource();
                interno.setCursor(Cursor.HAND);
            }
        });
        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            
            @Override
            public void handle(MouseEvent event) {
                try {
                    
                    Canvas interno = (Canvas)event.getSource();
                    est.setIdEstacionamiento(Integer.parseInt(interno.getAccessibleHelp()));
                    est = recuperarDatosEsta(est);
                    irInicio();
                } catch (IOException ex) {
                    Logger.getLogger(SelectEstaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        Text txt_Est = new Text(nombreEst);
        canvas.setStyle("-fx-border: 1px solid blue");
        gc.strokeRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.drawImage(imgEst,0, 0);
        gc.strokeText(txt_Est.getText(), 0, imgEst.getHeight()+15);
        pane.setHgap(20);
        pane.getChildren().add(canvas);
    }
    private void irInicio() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/Vista/MenuPanel.fxml"));
        Stage stage = (Stage)pane.getScene().getWindow();
        stage.getIcons().add(new Image("/Vista/Imagenes/parkLogo.png"));
        Scene scene = new Scene(root);
        stage.setTitle("IBEXSoftworks - ParkPlatz.");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void onDelCuenta(ActionEvent ev){
        try{
            CuadrosDialogo.infoDialog("Al eliminar su cuenta se borrara todos los datos relacionados con ella!");
        if(!CuadrosDialogo.reallyyDialog("¿En verdad desea eliminar su cuenta?")){
            est.setCorreo(est.getCorreo());
            est.setLogueado(false);
            est.setPass(CuadrosDialogo.inputDialog("Favor de escribir su contraseña para confirmar la accion:"));
            est = loginEstacionamiento(est);
            if(est.isLogueado()){
                
                ArrayList<Integer> ids = (ArrayList<Integer>)traeIdEstacionamiento(est.getCorreo());
              for(int i = 0; i<ids.size(); i++){
                  est.setIdEstacionamiento(ids.get(i));
                  delEsquema(est);
               borrameEsta(est);
              }
                
                CuadrosDialogo.infoDialog(delUser(est.getCorreo()));
                cerrar(ev);
            }else{
                CuadrosDialogo.errorDialog("Correo o contraseña incorrecta.");
            }
        }
        }catch(Exception e){
            e.printStackTrace();
            CuadrosDialogo.errorDialog(e.getMessage());
        }
    }
    @FXML
    public void registraOtro(ActionEvent ev) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/Vista/RegistrarConCuenta.fxml"));
        Stage stage = (Stage)pane.getScene().getWindow();
        stage.getIcons().add(new Image("/Vista/Imagenes/parkLogo.png"));
        Scene scene = new Scene(root);
        stage.setTitle("Registrando otro estacionamiento.");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void cerrar(ActionEvent ev) throws IOException{
        
        Parent root;
        root = FXMLLoader.load(getClass().getResource("/Vista/vLogin.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Iniciar Sesión.");
        stage.getIcons().add(new Image("/Vista/Imagenes/parkLogo.png"));
        stage.setScene(scene);
        stage.show();
        ((Stage)pane.getScene().getWindow()).close();
    }
    private static Estacionamiento recuperarDatosEsta(ParkPlatzWSC.Estacionamiento objetoConIdaRecuperar) {
        ParkPlatzWSC.ParkPlatzWS_Service service = new ParkPlatzWSC.ParkPlatzWS_Service();
        ParkPlatzWSC.ParkPlatzWS port = service.getParkPlatzWSPort();
        return port.recuperarDatosEsta(objetoConIdaRecuperar);
    }

    private static Estacionamiento obtenerMisEstacionamientos(ParkPlatzWSC.Estacionamiento objetoEst) throws ClassNotFoundException_Exception {
        ParkPlatzWSC.ParkPlatzWS_Service service = new ParkPlatzWSC.ParkPlatzWS_Service();
        ParkPlatzWSC.ParkPlatzWS port = service.getParkPlatzWSPort();
        return port.obtenerMisEstacionamientos(objetoEst);
    }

    private static Estacionamiento loginEstacionamiento(ParkPlatzWSC.Estacionamiento objetoConUserPass) throws UnsupportedEncodingException_Exception, NoSuchAlgorithmException_Exception {
        ParkPlatzWSC.ParkPlatzWS_Service service = new ParkPlatzWSC.ParkPlatzWS_Service();
        ParkPlatzWSC.ParkPlatzWS port = service.getParkPlatzWSPort();
        return port.loginEstacionamiento(objetoConUserPass);
    }

    private static String borrameEsta(ParkPlatzWSC.Estacionamiento estaToDel) throws ClassNotFoundException_Exception {
        ParkPlatzWSC.ParkPlatzWS_Service service = new ParkPlatzWSC.ParkPlatzWS_Service();
        ParkPlatzWSC.ParkPlatzWS port = service.getParkPlatzWSPort();
        return port.borrameEsta(estaToDel);
    }

    private static String delEsquema(ParkPlatzWSC.Estacionamiento delEsquema) {
        ParkPlatzWSC.ParkPlatzWS_Service service = new ParkPlatzWSC.ParkPlatzWS_Service();
        ParkPlatzWSC.ParkPlatzWS port = service.getParkPlatzWSPort();
        return port.delEsquema(delEsquema);
    }

    private static String delUser(java.lang.String correo) {
        ParkPlatzWSC.ParkPlatzWS_Service service = new ParkPlatzWSC.ParkPlatzWS_Service();
        ParkPlatzWSC.ParkPlatzWS port = service.getParkPlatzWSPort();
        return port.delUser(correo);
    }

    private static java.util.List<java.lang.Integer> traeIdEstacionamiento(java.lang.String correo) {
        ParkPlatzWSC.ParkPlatzWS_Service service = new ParkPlatzWSC.ParkPlatzWS_Service();
        ParkPlatzWSC.ParkPlatzWS port = service.getParkPlatzWSPort();
        return port.traeIdEstacionamiento(correo);
    }
}
