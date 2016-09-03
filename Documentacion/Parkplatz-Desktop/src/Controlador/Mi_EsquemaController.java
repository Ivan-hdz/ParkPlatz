/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import static Controlador.LoginController.est;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author honte_000
 */
public class Mi_EsquemaController implements Initializable {
@FXML
    Label lbl_nombreEst = new Label();
    @FXML
    Button btn_config = new Button();
    @FXML
    Button btn_servicio = new Button();
    @FXML
    Button btn_salir = new Button();
    @FXML
    Button btn_info = new Button();
    @FXML
    Button btn_inicio = new Button();
    @FXML
    ImageView imgV = new ImageView();
    @FXML
    Button btn_feed = new Button();
    @FXML
    SplitPane splitPane = new SplitPane();
    @FXML
    Canvas cnvs_trash = new Canvas();
    @FXML
    FlowPane pane_icons = new FlowPane();
    @FXML
    Pane pane_esq = new Pane();
    @FXML
    FlowPane paneDel = new FlowPane();
    private int contadorLugares = 0;
   
    
    /*
        <-----NOTA------>
        Pendiente falta hayar la forma de que funcione el drag and drop
        con todos los objetos y no solo con el ultimo que se inserto
    */
    
    /**
     * Initializes the controller class.
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
         
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        cargarAssets();
        dibujarBote();
        dibujarIconosPred();
        dibujarCanvasLugar("on.png");
        
    }
    //Los metodos dibujarIconosPred y dibujarCanvasLugar ocupan este metodo para hacerlos drag and drop
    private void setDragDrop(final Canvas canvas){ //<----Desde aqui es el problema
       canvas.setAccessibleHelp(null);
        canvas.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Dragboard db = canvas.startDragAndDrop(TransferMode.ANY);
                WritableImage img = canvas.snapshot(null, null);
                ClipboardContent contenido = new ClipboardContent();
                contenido.putImage(img);
                db.setContent(contenido);
                event.consume();
                
            }
        });
        pane_esq.setOnDragOver(new EventHandler<DragEvent>()  {
           
            @Override
            public void handle(DragEvent event) {
                 if(event.getDragboard().hasImage()){
                    event.acceptTransferModes(TransferMode.ANY);
                    pane_esq.setStyle("-fx-border-color: green");
                }
                event.consume();
            }
        });
        pane_esq.setOnDragExited(new EventHandler<DragEvent>(){
            @Override
            public void handle(DragEvent event) {
                pane_esq.setStyle("-fx-border-color: blue");
                event.consume();
            }
        });
        pane_esq.setOnDragDropped(new EventHandler<DragEvent>() { 
            
            @Override
            public void handle(DragEvent event) {
                if(canvas.getAccessibleHelp() == null){
                    pane_esq.getChildren().add(canvas);
                }
                canvas.setAccessibleHelp("Ya");
            
                canvas.setLayoutX(event.getX()-24);
                canvas.setLayoutY(event.getY()-24);
                
                event.setDropCompleted(true);
                event.consume();
            }
        });
      
        paneDel.setOnDragOver(new EventHandler<DragEvent>(){
           
            @Override
            public void handle(DragEvent event) {
                 if(event.getDragboard().hasImage()){
                    event.acceptTransferModes(TransferMode.ANY);
                    paneDel.setStyle("-fx-opacity:0.5;-fx-background-color: darkred;-fx-border-color: red");
                }
                event.consume();

            }
        });
        paneDel.setOnDragExited(new EventHandler<DragEvent>(){ 
           
            @Override
            public void handle(DragEvent event) {
                 paneDel.setStyle("-fx-opacity:1;-fx-background-color: none;-fx-border-color: red");
            }
        });
        paneDel.setOnDragDropped(new EventHandler<DragEvent>() {

            @Override
            public void handle(DragEvent event) {
                if(event.getDragboard().hasImage()){
                    if(canvas.getAccessibleRoleDescription().equalsIgnoreCase("0")){
                        WritableImage img = canvas.snapshot(null, null);
                        canvas.getGraphicsContext2D().clearRect(0, 0, img.getWidth(), img.getHeight());
                        canvas.setLayoutX(event.getSceneX());
                        canvas.setLayoutY(event.getSceneY());
                    }else{
                        setDragDrop(canvas);
                        pane_icons.getChildren().add(canvas);
                    }
                        event.setDropCompleted(true);
                }
                event.consume();

            }
        });
        
    }
    private void dibujarIconosPred(){
        canvasMolde("paredT.png");
        canvasMolde("paredR.png");
        canvasMolde("paredB.png");
        canvasMolde("paredL.png");
        canvasMolde("stairsR.png");
        canvasMolde("stairsL.png");
        
        
    }
    private void dibujarCanvasLugar(String imgNom){
        
        Image img = new Image("/Vista/Imagenes/"+imgNom,48,32,true,true);
        Canvas cnvs = new Canvas();
      
        cnvs.setWidth(img.getWidth());
        cnvs.setHeight(img.getHeight()+16);
        GraphicsContext gc = cnvs.getGraphicsContext2D();
        gc.drawImage(img, 0, 0);
        contadorLugares++;
        gc.fillText("Lugar "+String.valueOf(contadorLugares), 0, 40,32);
        cnvs.setAccessibleRoleDescription(String.valueOf(contadorLugares));
        setDragDrop(cnvs);
        pane_icons.getChildren().add(cnvs);
    }
    private void canvasMolde(String imgNom){
        Image img = new Image("/Vista/Imagenes/"+imgNom, 48, 48, true, true);
        Canvas cnvs = new Canvas();
        cnvs.setWidth(img.getWidth());
        cnvs.setHeight(img.getHeight());
        GraphicsContext gc = cnvs.getGraphicsContext2D();
        gc.drawImage(img, 0, 0);
        cnvs.setAccessibleRoleDescription(String.valueOf(0));
        pane_icons.getChildren().add(cnvs);
        setDragDrop(cnvs);
    }
    private void dibujarBote(){
        
        Image imgTrash = new Image("/Vista/Imagenes/trash.png",50, 50, true, true);
        cnvs_trash.setWidth(imgTrash.getWidth());
        cnvs_trash.setHeight(imgTrash.getHeight());
        GraphicsContext gc = cnvs_trash.getGraphicsContext2D();
        gc.drawImage(imgTrash, 0, 0);
    }
    private void cargarAssets(){
        String img = "";
        try{
            if(est.getUrlImg()== null){
                img = "/Vista/Imagenes/autoLogin.jpg";
            }else{
                File file = new File(est.getUrlImg());
                URL url_foto = file.toURI().toURL();
                img = url_foto.toExternalForm();
            }
            imgV.setImage(new Image(img,200, 150, true, true));
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        lbl_nombreEst.setText(est.getNombreEsta());
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
}
