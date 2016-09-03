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
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
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
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;

/**
 * FXML Controller class
 *
 * @author honte_000
 */
public class Mi_EsquemaController implements Initializable, Serializable {
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
    protected Button btn_delPiso = new Button("-Piso");
    protected int totalPisos = 1;
    protected  Esquema esquemaGuardar = new Esquema();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    try{
        CuadrosDialogo.infoDialog("Los cambios se guardarán automaticamente al salir de esta ventana.");
        cargarEsquema();
        cargarAssets();
        dibujarBote();
        dragDropEsqPane();
        dragDropEliminarPane();
        setListenersToButtons();
    } catch (Exception ex) {
        Logger.getLogger(Mi_EsquemaController.class.getName()).log(Level.SEVERE, null, ex);
    }

}
    protected void setListenersToButtons(){
        if(pisonEstacionamiento == 1 && totalPisos > 1){
            toolsPane.getChildren().add(btn_pisoAdelante);
            toolsPane.getChildren().add(btn_delPiso);

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
        btn_delPiso.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                onClickDelPiso(event);
            }
        });
    }
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
                    setDragDropProperties(canCopy, imgCopy);
                    pane_esq.getChildren().add(canCopy);
                    pane_esq.setAccessibleHelp(String.valueOf(pisonEstacionamiento));
                    canCopy.setLayoutX(datosCanvas.getCoorX());
                    canCopy.setLayoutY(datosCanvas.getCoorY());
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
                    if(esquemaGuardar.getObjetosEnEsquema().get(j).getId().equals(userData.get(2))){
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
    protected void dragDropEliminarPane(){
        paneDel.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                if(event.getDragboard().hasImage()){
                    event.acceptTransferModes(TransferMode.COPY);
                    paneDel.setStyle("-fx-border-color: red");
                }
                event.consume();
            }
        });
        paneDel.setOnDragExited(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                paneDel.setStyle("-fx-border-color: none");
            }
        });
        paneDel.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                ArrayList userData;
                Canvas can = (Canvas)event.getGestureSource();

                    pane_esq.getChildren().remove(can);
                    for(int i = 0; i<esquemaGuardar.getObjetosEnEsquema().size(); i++){
                        userData = (ArrayList)can.getUserData();
                        ObjetoDeEsquema datosCanv = new ObjetoDeEsquema();
                        if(userData.get(2).toString().equals(esquemaGuardar.getObjetosEnEsquema().get(i).getId())){
                            esquemaGuardar.getObjetosEnEsquema().remove(i);
                            if(userData.get(2).toString().contains("Arduino")){
                                try {
                                    int idArduino = Integer.parseInt(userData.get(2).toString().split(";")[0]);
                                    //Baja en DB
                                    Connection con = Database.conectar();
                                    PreparedStatement ps = con.prepareStatement("call bajaLugar(?)");
                                    ps.setInt(1, idArduino);
                                    ps.executeQuery();
                                } catch (ClassNotFoundException ex) {
                                    Logger.getLogger(Mi_EsquemaController.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (SQLException ex) {
                                    Logger.getLogger(Mi_EsquemaController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }

                            break;
                        }

                    }
                    can = null;
                   if(!can.getAccessibleHelp().equals("Canvas Predefinido")){

                   }



            }
        });
    }
    protected void dragDropEsqPane() throws Exception{

       pane_esq.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                boolean primera = true;
                boolean arduino = false;
                Date fecha = new Date();
                Canvas can2 = (Canvas)event.getGestureSource();
                ArrayList userDataToCopy = (ArrayList)can2.getUserData();
                ArrayList newUserData = new ArrayList();
                for(int i = 0; i<pane_esq.getChildren().size(); i++){
                    Canvas can1 = (Canvas) pane_esq.getChildren().get(i);
                    if(can1 == can2){
                        primera = false;

                    }

                }

                if(!can2.getAccessibleHelp().equals("Canvas Predefinido")){
                        arduino = true;
                        System.out.println("Es arduino");
                }
                if(primera == true && arduino == false){

                    Canvas canCopy = new Canvas();
                    canCopy.setAccessibleHelp(can2.getAccessibleHelp());
                    newUserData.add(0, userDataToCopy.get(0));
                    newUserData.add(1, pisonEstacionamiento);
                    newUserData.add(2, fecha.getTime());
                    System.out.println("<userData>"+newUserData.get(2)+"</userData>");
                    canCopy.setUserData(newUserData);
                    Image imgCopy = event.getDragboard().getImage();
                    canCopy.setWidth(imgCopy.getWidth());
                    canCopy.setHeight(48);
                    GraphicsContext gc1 = canCopy.getGraphicsContext2D();
                    gc1.drawImage(imgCopy, 0, 0);
                    setDragDropProperties(canCopy, imgCopy);
                    canCopy.setUserData(newUserData);
                    pane_esq.getChildren().add(canCopy);
                    canCopy.setLayoutX(event.getX()-24);
                    canCopy.setLayoutY(event.getY()-24);
                    System.out.println("No es arduino y es la primera");

                }else{
                    if(primera == true && arduino == true){
                        try {
                            Random rand = new Random();
                            int numIDrand = (int)(100*(Math.pow(Math.abs(est.getCordenadaX())*rand.nextInt(100), Math.random())));
                            Canvas canCopy = new Canvas();
                            canCopy.setAccessibleHelp(can2.getAccessibleHelp());
                            newUserData.add(0, userDataToCopy.get(0));
                            newUserData.add(1, pisonEstacionamiento);
                            newUserData.add(2, numIDrand+";"+canCopy.getAccessibleHelp()+";"+"Disconnected;unknownStatus;oldID;");
                            System.out.println(newUserData.get(2));
                            canCopy.setUserData(newUserData);
                            Image imgCopy = event.getDragboard().getImage();
                            canCopy.setWidth(imgCopy.getWidth());
                            canCopy.setHeight(48);
                            GraphicsContext gc1 = canCopy.getGraphicsContext2D();
                            gc1.drawImage(imgCopy, 0, 0);
                            setDragDropProperties(canCopy, imgCopy);
                            canCopy.setUserData(newUserData);
                            pane_esq.getChildren().add(canCopy);
                            if(can2.getAccessibleHelp().equals("ArduinoL") || can2.getAccessibleHelp().equals("ArduinoR"))
                            {
                                canCopy.setLayoutX(event.getX()-25);
                                canCopy.setLayoutY(event.getY()-12);
                            }else{
                                canCopy.setLayoutX(event.getX()-12);
                                canCopy.setLayoutY(event.getY()-24);
                            }
                            System.out.println("Si es arduino y es la primera");
                            //Alta en DB ----------------------------------------------------------------------<
                            Connection con = Database.conectar();
                            PreparedStatement ps = con.prepareStatement("call nuevoLugar(?,?,?,?,?,?)");
                           ps.setInt(1, est.getIdEstacionamiento());
                            ps.setInt(2, numIDrand); // ID lugar
                            ps.setInt(3, 0); // Status por default
                            ps.setString(4, String.valueOf(canCopy.getLayoutX())); //coorX
                            ps.setString(5, String.valueOf(canCopy.getLayoutY())); //coorY
                            ps.setInt(6, pisonEstacionamiento); //piso
                            ps.executeQuery();

                            // fin alta
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(Mi_EsquemaController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(Mi_EsquemaController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }else{
                        System.out.println("No es la primera");
                        if(arduino){
                            try {
                                if(can2.getAccessibleHelp().equals("ArduinoL") || can2.getAccessibleHelp().equals("ArduinoR"))
                                {
                                    can2.setLayoutX(event.getX()-25);
                                    can2.setLayoutY(event.getY()-12);
                                }else{
                                    can2.setLayoutX(event.getX()-12);
                                    can2.setLayoutY(event.getY()-24);
                                }
                                //Actualizar las coordenadas a DB
                                ArrayList userData = (ArrayList)can2.getUserData();
                                int idLugar = Integer.parseInt(userData.get(2).toString().split(";")[0]);
                                Connection con = Database.conectar();
                                PreparedStatement ps = con.prepareStatement("call updateLugar(?,?,?,?);");
                                ps.setInt(1, idLugar);
                                ps.setInt(2, 0);
                                ps.setString(3, String.valueOf(can2.getLayoutX()));
                                ps.setString(4, String.valueOf(can2.getLayoutY()));
                                ps.executeUpdate();
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(Mi_EsquemaController.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (SQLException ex) {
                                Logger.getLogger(Mi_EsquemaController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            ///Fin actualizada a DB
                        }else{
                            can2.setLayoutX(event.getX()-24);
                            can2.setLayoutY(event.getY()-24);
                        }
                    }
                }
                event.consume();
            }
        });
       pane_esq.setOnDragExited(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                pane_esq.setStyle("-fx-border-color: none");
            }
        });
       pane_esq.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                if(db.hasImage()){
                    event.acceptTransferModes(TransferMode.COPY);
                }
                pane_esq.setStyle("-fx-border-color: blue");
                event.consume();
            }
        } );
   }
    public void setDragDropProperties(final Canvas can, final Image img){

            can.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Dragboard db = can.startDragAndDrop(TransferMode.COPY);
                ClipboardContent cc = new ClipboardContent();
                cc.putImage(img);
                db.setContent(cc);
                event.consume();
            }
        });


    }
    protected void dibujarBote(){

        Image imgTrash = new Image("/Vista/Imagenes/trash.png",50, 50, true, true);
        cnvs_trash.setWidth(imgTrash.getWidth());
        cnvs_trash.setHeight(imgTrash.getHeight());
        GraphicsContext gc = cnvs_trash.getGraphicsContext2D();
        gc.drawImage(imgTrash, 0, 0);
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

            new CanvasPredefinido("paredB.png", pane_icons, "Canvas Predefinido").setDragDropProperties().dibujar();
            new CanvasPredefinido("paredT.png", pane_icons, "Canvas Predefinido").setDragDropProperties().dibujar();
            new CanvasPredefinido("paredL.png", pane_icons, "Canvas Predefinido").setDragDropProperties().dibujar();
            new CanvasPredefinido("paredR.png", pane_icons, "Canvas Predefinido").setDragDropProperties().dibujar();
            new CanvasPredefinido("stairsL.png", pane_icons, "Canvas Predefinido").setDragDropProperties().dibujar();
            new CanvasPredefinido("stairsR.png", pane_icons, "Canvas Predefinido").setDragDropProperties().dibujar();
            new CanvasPredefinido("arduinoB.png", pane_icons, "ArduinoB").setDragDropProperties().dibujar();
            new CanvasPredefinido("arduinoT.png", pane_icons, "ArduinoT").setDragDropProperties().dibujar();
            new CanvasPredefinido("arduinoL.png", pane_icons, "ArduinoL").setDragDropProperties().dibujar();
            new CanvasPredefinido("arduinoR.png", pane_icons, "ArduinoR").setDragDropProperties().dibujar();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        lbl_nombreEst.setText(est.getNombreEsta());
    }
    @FXML
    public void onClickSig(ActionEvent ev){
    try {
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
    } catch (Exception ex) {
        Logger.getLogger(Mi_EsquemaController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    @FXML
    public void onClickAtras(ActionEvent ev){
    try {
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
    } catch (Exception ex) {
        Logger.getLogger(Mi_EsquemaController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    @FXML
    public void onClickNuevoPiso(ActionEvent ev){
       totalPisos++;
        bufferEsquema();
        pane_esq.getChildren().remove(0, pane_esq.getChildren().size());
        pisonEstacionamiento++;
         if(!toolsPane.getChildren().contains(btn_pisoAtras)){
            toolsPane.getChildren().add(btn_pisoAtras);
            toolsPane.getChildren().add(btn_delPiso);

         }

          lbl_piso.setText(String.valueOf(pisonEstacionamiento)+" / "+String.valueOf(totalPisos));

         System.out.println(totalPisos);
    }
    @FXML
    public void onClickDelPiso(ActionEvent ev){


        totalPisos--;
        buscarEliminarCanvasPorPiso(pisonEstacionamiento);
        ObservableList listaCanvas = pane_esq.getChildren();
        if(pisonEstacionamiento == 1){
            recorrerEnUnPisoAtras();
            pisonEstacionamiento--;
            onClickSig(ev);
        }else if(pisonEstacionamiento == totalPisos) {
            recorrerEnUnPisoAdelante();
            onClickAtras(ev);

        }else{
            onClickAtras(ev);
        }
        if(totalPisos == 1){
            toolsPane.getChildren().remove(btn_delPiso);
        }
        if(pisonEstacionamiento == totalPisos && toolsPane.getChildren().contains(btn_pisoAdelante)){
            toolsPane.getChildren().remove(btn_pisoAdelante);
        }
        if(totalPisos == 1 && toolsPane.getChildren().contains(btn_pisoAtras)){
            toolsPane.getChildren().remove(btn_pisoAtras);
        }

    }
    protected void buscarEliminarCanvasPorPiso(int piso){
        try {
            esquemaGuardar =  getEsquema(est);
            ArrayList<ObjetoDeEsquema> objetosToDel = new ArrayList<>();
            ArrayList<ObjetoDeEsquema> lista = (ArrayList<ObjetoDeEsquema>) esquemaGuardar.getObjetosEnEsquema();
            ArrayList<Canvas> nodesToDel = new ArrayList<>();
            Connection con = Database.conectar();
            for(int i = 0; i<pane_esq.getChildren().size(); i++){
                Canvas can = (Canvas)pane_esq.getChildren().get(i);
                ArrayList userData = (ArrayList)can.getUserData();
                if(Integer.parseInt(String.valueOf(userData.get(1))) == pisonEstacionamiento){
                    nodesToDel.add(can);
                    int idLugar = 0;
                    if(userData.get(2).toString().contains("Arduino")){
                        try {
                            idLugar = Integer.parseInt(userData.get(2).toString().split(";")[0]);
                            PreparedStatement ps = con.prepareStatement("call bajaLugar(?)");
                            ps.setInt(1, idLugar);
                            ps.executeUpdate();
                        } catch (SQLException ex) {
                            Logger.getLogger(Mi_EsquemaController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Mi_EsquemaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Mi_EsquemaController.class.getName()).log(Level.SEVERE, null, ex);
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
        MenuPanelController.pararHilo();
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
         bufferEsquema();

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
