/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import static Controlador.LoginController.est;
import Modelo.Database;
import ParkPlatzWSC.Esquema;
import ParkPlatzWSC.ObjetoDeEsquema;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ivan
 */
public class VerEnVivoController implements Initializable {
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
    @FXML
    FlowPane toolsPane = new FlowPane();
    @FXML
    Button btn_vivo = new Button();
    public int pisonEstacionamiento = 1;
    @FXML
    Label lbl_piso = new Label("");
    protected Button btn_pisoAtras  = new Button("<=");;
    protected Button btn_pisoAdelante = new Button("=>");
    protected int totalPisos = 1;
    protected  Esquema esquemaGuardar = new Esquema();
    private Thread hiloUpdater;
    final ContextMenu cm = new ContextMenu();
    private Connection con;
    private Runnable updater;
    public static boolean alive = false;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    try{    
         con = Database.conectar();
        cargarAssets();
         cargarEsquema();
        setListenersToButtons();
        setUpContextMenu();
        startUpdater(pane_esq.getChildren());
    } catch (Exception ex) {
        Logger.getLogger(Mi_EsquemaController.class.getName()).log(Level.SEVERE, null, ex);
    }
        
}
    
    //Actualizar canvas con forme a DB en otro Thread
    private void startUpdater(ObservableList listToUpdate){
        try{
            
            updater = new ArduinoCanvasUpdater(listToUpdate, est);
            hiloUpdater = new Thread(updater);
            hiloUpdater.start();
                
            alive = true;
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private void stopUpdater(){
        try{
            alive = false;
            hiloUpdater.interrupt();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    protected void setListenersToButtons(){
        if(pisonEstacionamiento == 1 && totalPisos > 1){
            toolsPane.getChildren().add(btn_pisoAdelante);
            
            
        }
        btn_pisoAdelante.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                onClickSig(event);
            }
        });
        btn_pisoAtras.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                onClickAtras(event);
            }
            
        });
    }

    /**
     *
     * @throws Exception
     */
    protected void cargarEsquema() throws Exception{
        
        try{
           esquemaGuardar =  getEsquema(est);
          totalPisos = esquemaGuardar.getTotalPisos();
            lbl_piso.setText(String.valueOf(pisonEstacionamiento)+" / "+String.valueOf(totalPisos));
           ArrayList<ObjetoDeEsquema> lista = (ArrayList<ObjetoDeEsquema>) esquemaGuardar.getObjetosEnEsquema();
          for(int i = 0; i < lista.size(); i++)
          {
              ObjetoDeEsquema datosCanvas = (ObjetoDeEsquema)lista.get(i);
              ArrayList userData = new ArrayList();
              System.out.println(datosCanvas.getId());
              System.out.println(datosCanvas.getPiso());
             if(datosCanvas.getPiso() == pisonEstacionamiento){
                    Canvas canCopy = new Canvas();
                    userData.add(0,datosCanvas.getImgNombre());
                    userData.add(1,datosCanvas.getPiso());
                    userData.add(2, datosCanvas.getId());
                    canCopy.setUserData(userData);
                    canCopy.setAccessibleHelp("Canvas Predefinido");;
                    Image imgCopy =  new Image("/Vista/Imagenes/"+datosCanvas.getImgNombre(), 48, 48, true, true);
                    canCopy.setWidth(imgCopy.getWidth());
                    canCopy.setHeight(48);
                    GraphicsContext gc1 = canCopy.getGraphicsContext2D();
                    gc1.drawImage(imgCopy, 0, 0);
                    pane_esq.getChildren().add(canCopy);
                    pane_esq.setAccessibleHelp(String.valueOf(pisonEstacionamiento));
                    canCopy.setLayoutX(datosCanvas.getCoorX());
                    canCopy.setLayoutY(datosCanvas.getCoorY());
                    String isArduino[] = datosCanvas.getId().split(";");
                    if(isArduino.length>1){
                        setMouseProperties(canCopy, imgCopy);
                        
                    }
             }
          }
        }catch(Exception e){
            e.printStackTrace();
            CuadrosDialogo.errorDialog(e.getMessage());
        }
        
        
    }
    protected void bufferEsquema(){
        try{
          
            ObservableList listaEsquema = pane_esq.getChildren();
            
            ArrayList userData;
            
            boolean existe = false;
            for(int i = 0; i<listaEsquema.size(); i++){
                Canvas can = (Canvas)listaEsquema.get(i);
                userData = (ArrayList)can.getUserData();
                ObjetoDeEsquema datosCanv = new ObjetoDeEsquema();
                datosCanv.setCoorX(can.getLayoutX());
                datosCanv.setCoorY(can.getLayoutY());
                datosCanv.setImgNombre(String.valueOf(userData.get(0)));
                datosCanv.setPiso(Integer.parseInt(String.valueOf(userData.get(1))));
                datosCanv.setId(String.valueOf(userData.get(2)));
                    
                System.out.println("<------guardando en piso --->");
                System.out.println(userData.get(2));
                System.out.println("<---------->");
               
                for(int j = 0; j<esquemaGuardar.getObjetosEnEsquema().size(); j++){
                     String id[] = esquemaGuardar.getObjetosEnEsquema().get(j).getId().split(";");
                    if(esquemaGuardar.getObjetosEnEsquema().get(j).getId().equals(userData.get(2)) || userData.get(2).toString().contains(id[0]) ){
                        esquemaGuardar.getObjetosEnEsquema().set(j,datosCanv);
                        existe = true;
                        break;
                    }else{
                        existe = false;
                    }
                }
                if(!existe){
                    
                    esquemaGuardar.getObjetosEnEsquema().add(datosCanv);
                }
               
            }
            esquemaGuardar.setTotalPisos(totalPisos);
            System.out.println("tamaño "+esquemaGuardar.getObjetosEnEsquema().size());
            saveEsquema(est, esquemaGuardar);
        }catch(Exception e){
            CuadrosDialogo.errorDialog(e.getMessage());
            e.printStackTrace();
        }
        
       
    }
    private void setUpContextMenu(){
           cm.getItems().add(new MenuItem("Introducir nuevo ID."));
          
       }
    
    private void setMouseProperties(final Canvas can, final Image img){
        
            can.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    Canvas canSource = (Canvas)event.getSource();
                    if(event.getButton() == MouseButton.SECONDARY){
                        ArrayList userData = (ArrayList)canSource.getUserData();
                        cm.show(canSource, canSource.getLayoutX()+500, canSource.getLayoutY()+50);
                        ObservableList<MenuItem> options = cm.getItems();
                        for(int i = 0; i< options.size(); i++){
                            MenuItem item = options.get(i);
                            item.setUserData(userData);
                            /*
                                userData.get(0) ---> Canvas img name
                                userData.get(1) ---> Floor number
                                userData.get(2) ---> id
                            */
                            switch(item.getText()){
                                case "Introducir nuevo ID.":{
                                    item.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent event) {
                                        MenuItem item = (MenuItem)event.getSource();
                                         setNewIdToCanvas((ArrayList)item.getUserData());
                                    }
                                });
                                    break;
                                }
                                case "Disponible.":{
                                    item.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent event) {
                                        MenuItem item = (MenuItem)event.getSource();
                                        setArduinoStatus("Disponible", (ArrayList)item.getUserData());
                                    }
                                });
                                    break;
                                }
                                case "Ocupado.":{
                                    item.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent event) {
                                        MenuItem item = (MenuItem)event.getSource();
                                        setArduinoStatus("Ocupado", (ArrayList)item.getUserData());
                                    }
                                });
                                    break;
                                }
                                case "Fuera de Servicio.":{
                                    item.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent event) {
                                        MenuItem item = (MenuItem)event.getSource();
                                        setArduinoStatus("FueraDeServicio", (ArrayList)item.getUserData());
                                    }
                                });
                                    break;
                                }
                                default:{
                                    event.consume();
                                    break;
                                }
                            }
                            
                        }
                    }
                    event.consume();
                }
            });
   }
    private void setNewIdToCanvas(ArrayList canvasData){
        final String oldID = String.valueOf(canvasData.get(2));
        String oldIDNumber[] = oldID.split(";");
        String idString = String.valueOf(canvasData.get(2));
        String[] datos = idString.split(";");
      Dialog dialog = new TextInputDialog(datos[0]);
        dialog.setTitle("Confirmación.");
        dialog.setHeaderText("Cambiar ID de Arduino.");
       dialog.setContentText("Favor de introducir el nuevo ID: ");
        Optional<String> result = dialog.showAndWait();
       
        if (result.isPresent()) {
            try {
                con = Database.conectar();
                PreparedStatement ps = con.prepareStatement("call updateLugarID(?,?);");
                System.out.println(oldIDNumber[0]);
                System.out.println(result.get());
                ps.setInt(1, Integer.parseInt(oldIDNumber[0]));
                ps.setInt(2, Integer.parseInt(result.get()));
                ps.executeUpdate();
                datos[0] = result.get();
                datos[4] = oldIDNumber[0];
                idString = "";
                for(int i = 0; i<datos.length; i++){
                    idString += datos[i]+";";
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(VerEnVivoController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(VerEnVivoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        canvasData.set(2, idString);
         
         ObservableList objects = pane_esq.getChildren();
         
         for(int i = 0; i <objects.size(); i++){
            Canvas canv = (Canvas)objects.get(i);
            ArrayList userData =  (ArrayList)canv.getUserData();
            if(userData.get(2).toString().equals(oldID)){
                canv.setUserData(canvasData);
                canv.setAccessibleText(oldID);
                objects.set(i, canv);
                break;
            }
        }
         bufferEsquema();
    }
    
    protected void setArduinoStatus(String status, ArrayList canvasData){
        String urlImgNueva = "";
        final String oldID = String.valueOf(canvasData.get(2));
        String idString = String.valueOf(canvasData.get(2));
        String[] datosDeId = idString.split(";");
        if(status.equals("Disponible")){
            datosDeId[3] = "available";
            urlImgNueva = "on.png"; //Imagen supuesta
        }else if(status.equals("Ocupado")){
            datosDeId[3] = "busy";
            urlImgNueva = "alert.png"; //Imagen supuesta
        }else if(status.equals("FueraDeServicio")){
            datosDeId[3] = "outOfOrder";
            urlImgNueva = "off.png"; //Imagen supuesta
        }else if(status.equals("unknownStatus")){
            urlImgNueva = "default";
        }
        idString = "";
        for(int i = 0; i<datosDeId.length; i++){
            idString += datosDeId[i]+";";
        }
        ObservableList objects = pane_esq.getChildren();
        for(int i = 0; i <objects.size(); i++){
            Canvas canv = (Canvas)objects.get(i);
            ArrayList userData =  (ArrayList)canv.getUserData();
            if(userData.get(2).toString().equals(oldID)){
                if(urlImgNueva.equals("default")){
                    if(datosDeId[1].endsWith("T")){
                        urlImgNueva = "arduinoT.png";
                    }else if(datosDeId[1].endsWith("B")){
                        urlImgNueva = "arduinoB.png";
                    } else if(datosDeId[1].endsWith("L")){
                        urlImgNueva = "arduinoL.png";
                    } else if(datosDeId[1].endsWith("R")){
                        urlImgNueva = "arduinoR.png";
                    }
                    canv.setUserData(canvasData);
                    GraphicsContext gc = canv.getGraphicsContext2D();
                    gc.fill();
                    Image img = new Image("/Vista/Imagenes/"+urlImgNueva, 48,48, true, true);
                    gc.drawImage(img,0,0);
                    
                }else{
                    canv.setUserData(canvasData);
                    GraphicsContext gc = canv.getGraphicsContext2D();
                    Image img = new Image("/Vista/Imagenes/"+urlImgNueva);
                    gc.drawImage(img, 0, 0, 20, 20);
                    
                }
                final ObservableList objectsFinal = objects;
                final int j = i;
                final Canvas canvFinal = canv;
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        objectsFinal.set(j, canvFinal);
                    }
                });
                break;
            }
        }
    }
    public void setArduinoStatus(String status, String ID){
         ObservableList objects = pane_esq.getChildren();
         ArrayList canvasData = new ArrayList();
        for(int i = 0; i <objects.size(); i++){
            Canvas canv = (Canvas)objects.get(i);
            ArrayList userData =  (ArrayList)canv.getUserData();
            if(userData.get(2).toString().equals(ID)){
                canvasData = (ArrayList)canv.getUserData();
                break;
            }
        }
        /*
            userData.get(0) ---> Canvas img name
            userData.get(1) ---> Floor number
            userData.get(2) ---> id
        */
        String urlImgNueva = "";
        final String oldID = String.valueOf(canvasData.get(2));
        String idString = String.valueOf(canvasData.get(2));
        String[] datosDeId = idString.split(";");
        if(status.equals("Disponible")){
            datosDeId[3] = "available";
            canvasData.set(0,  "on.png"); //Imagen supuesta
            //Se modifica el estado en la DB
        }else if(status.equals("Ocupado")){
            datosDeId[3] = "busy";
          canvasData.set(0,  "alert.png"); //Imagen supuesta
            //Se modifica el estado en la DB
        }else if(status.equals("FueraDeServicio")){
            datosDeId[3] = "outOfOrder";
            canvasData.set(0,  "off.png"); //Imagen supuesta
            //Se modifica el estado en la DB
        }
        //Se reconstruye el idString con los nuevos datos
        idString = "";
        for(int i = 0; i<datosDeId.length; i++){
            idString += datosDeId[i]+";";
        }
        
        System.out.println(idString);
        //Se tiene que actualizar el canvas afectado en el esquema
        
        for(int i = 0; i <objects.size(); i++){
            Canvas canv = (Canvas)objects.get(i);
            ArrayList userData =  (ArrayList)canv.getUserData();
            if(userData.get(2).toString().equals(oldID)){
                canv.setUserData(canvasData);
                GraphicsContext gc = canv.getGraphicsContext2D();
                Image img = new Image("/Vista/Imagenes/"+canvasData.get(0).toString());
                gc.drawImage(img, 0, 0, 20, 20);
                objects.set(i, canv);
                break;
            }
        }
    }
    
    protected void cargarAssets(){
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
        lbl_nombreEst.setText(est.getNombreEsta());
    }
    @FXML
    public void onClickSig(ActionEvent ev){
    try {
        stopUpdater();
        bufferEsquema();
        pane_esq.getChildren().remove(0, pane_esq.getChildren().size());
        pisonEstacionamiento++;
        if(!toolsPane.getChildren().contains(btn_pisoAtras)){
            toolsPane.getChildren().add(btn_pisoAtras);
        }
        if(pisonEstacionamiento == totalPisos){
            toolsPane.getChildren().remove(btn_pisoAdelante);
        }
        lbl_piso.setText(String.valueOf(pisonEstacionamiento)+" / "+String.valueOf(totalPisos));
        System.out.println(pisonEstacionamiento);
        //Manejo de botones hasta aqui
        //Inicia dibujer lugares
        cargarEsquema();
        startUpdater(pane_esq.getChildren());
    } catch (Exception ex) {
        Logger.getLogger(Mi_EsquemaController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    @FXML
    public void onClickAtras(ActionEvent ev){
    try {
        stopUpdater();
        bufferEsquema();
        pane_esq.getChildren().remove(0, pane_esq.getChildren().size());
        pisonEstacionamiento--;
        if(!toolsPane.getChildren().contains(btn_pisoAdelante)){
            toolsPane.getChildren().add(btn_pisoAdelante);
        }
        
        if(pisonEstacionamiento == 1){
            toolsPane.getChildren().remove(btn_pisoAtras);
           
        }
        lbl_piso.setText(String.valueOf(pisonEstacionamiento)+" / "+String.valueOf(totalPisos));
        System.out.println(pisonEstacionamiento);
        //Manejo de botones hasta aqui
        //Inicia dibujer lugares
        cargarEsquema();
        startUpdater(pane_esq.getChildren());
    } catch (Exception ex) {
        Logger.getLogger(Mi_EsquemaController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
        protected void buscarEliminarCanvasPorPiso(int piso){
        esquemaGuardar =  getEsquema(est);
          ArrayList<ObjetoDeEsquema> objetosToDel = new ArrayList<>();
           ArrayList<ObjetoDeEsquema> lista = (ArrayList<ObjetoDeEsquema>) esquemaGuardar.getObjetosEnEsquema();
           ArrayList<Canvas> nodesToDel = new ArrayList<>();
            for(int i = 0; i<pane_esq.getChildren().size(); i++){
                Canvas can = (Canvas)pane_esq.getChildren().get(i);
                 ArrayList userData = (ArrayList)can.getUserData();
                 if(Integer.parseInt(String.valueOf(userData.get(1))) == pisonEstacionamiento){
                     nodesToDel.add(can);
                 }
           }
            for(int i = 0; i<nodesToDel.size(); i++){
                pane_esq.getChildren().remove(nodesToDel.get(i));
            }
        
        
          for(int i = 0; i < lista.size(); i++)
          {
              ObjetoDeEsquema datosCanvas = (ObjetoDeEsquema)lista.get(i);
              
             if(datosCanvas.getPiso() == piso){
                   objetosToDel.add(datosCanvas);
                   System.out.println("Agregado");
            }
        }
        for(int i = 0; i<objetosToDel.size(); i++){
              System.out.println(lista.remove(objetosToDel.get(i)));
                System.out.println("Eliminado");
        }
        
        saveEsquema(est, esquemaGuardar);
        esquemaGuardar = getEsquema(est);
        lista =  (ArrayList<ObjetoDeEsquema>) esquemaGuardar.getObjetosEnEsquema();
        for(int i = 0; i<lista.size(); i++){
            System.out.println("<---->");
            System.out.println(lista.get(i).getPiso());
            System.out.println("<---->");
        }
    }
    protected void recorrerEnUnPisoAdelante(){
       Esquema esq = esquemaGuardar;
           for(int j = 0; j<esq.getObjetosEnEsquema().size(); j++)  {
                   esq.getObjetosEnEsquema().get(j).setPiso(esq.getObjetosEnEsquema().get(j).getPiso()+1);
        }
    }
    protected void recorrerEnUnPisoAtras(){
       Esquema esq = esquemaGuardar;
           for(int j = 0; j<esq.getObjetosEnEsquema().size(); j++){
                   esq.getObjetosEnEsquema().get(j).setPiso(esq.getObjetosEnEsquema().get(j).getPiso()-1);
        }
    }
    @FXML    
    public void onClickInfo(ActionEvent ev) throws IOException{
        stopUpdater();
        bufferEsquema();
     
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
    public void onClickInicio(ActionEvent ev) throws IOException{
        stopUpdater();
       bufferEsquema();
      
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
    public void cerrar(ActionEvent ev) throws IOException{
        stopUpdater();
        bufferEsquema();
    
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
      stopUpdater();
        bufferEsquema();

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
         stopUpdater();    
        bufferEsquema();
      
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
    public void onClickFeed(ActionEvent ev) throws IOException{
         stopUpdater();    
        bufferEsquema();

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
    public void onClickVerEnVivo(ActionEvent ev) throws IOException{
         stopUpdater();
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
    public void onClickEsquema(ActionEvent ev) throws IOException{
         stopUpdater(); 
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
    public void onDelEsq(ActionEvent ev){
        try {
            if(!CuadrosDialogo.reallyyDialog("En verdad desea eliminar su esquema?")){
                CuadrosDialogo.infoDialog( delEsquema(est));
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
  
    private static Esquema getEsquema(ParkPlatzWSC.Estacionamiento estacionamiento) {
        ParkPlatzWSC.ParkPlatzWS_Service service = new ParkPlatzWSC.ParkPlatzWS_Service();
        ParkPlatzWSC.ParkPlatzWS port = service.getParkPlatzWSPort();
        return port.getEsquema(estacionamiento);
    }

    private static String saveEsquema(ParkPlatzWSC.Estacionamiento estacionamiento, ParkPlatzWSC.Esquema esquema) {
        ParkPlatzWSC.ParkPlatzWS_Service service = new ParkPlatzWSC.ParkPlatzWS_Service();
        ParkPlatzWSC.ParkPlatzWS port = service.getParkPlatzWSPort();
        return port.saveEsquema(estacionamiento, esquema);
    }


}
