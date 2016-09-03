/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import static Controlador.LoginController.est;
import Modelo.Database;
import ParkPlatzWSC.ClassNotFoundException_Exception;
import ParkPlatzWSC.Servicios;
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
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Dialogs.DialogResponse;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
/**
 *
 * @author honte_000
 */
public class ServiciosOfertasController implements Initializable{
    @FXML
    Label lbl_nombreEst = new Label();
    @FXML
    Button btn_vivo = new Button();
    @FXML
    Button btn_inicio = new Button();
    @FXML
    Button btn_cancel = new Button();
    @FXML
    TextField txt_nombreOf = new TextField();
    @FXML
    TextArea txt_desc = new TextArea();
    @FXML
    TextField text_cost = new TextField();
    @FXML
    Button btn_config = new Button();
    @FXML
    GridPane tabla = new GridPane();
    @FXML
    Button btn_nuevo = new Button();
    @FXML
    GridPane nuevoServ = new GridPane();
    @FXML
    Pane menuPane = new Pane();
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
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lbl_nombreEst.setText(est.getNombreEsta());
        String img = "";
        try{
                  cargarAssets();
                          
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
       actualizar();
       
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
    public void mostrar(ActionEvent ev){
        if(nuevoServ.isVisible()){
            nuevoServ.setVisible(false);
            btn_nuevo.setText("Nuevo servicio u oferta");
            btn_nuevo.setStyle("-fx-background-color: #26A65B");
            ObservableList<RowConstraints> ROWS = tabla.getRowConstraints();
            RowConstraints row = ROWS.get(0);
            row.setPercentHeight(5.6);
        }else{
            nuevoServ.setVisible(true);
            btn_nuevo.setText("Cancelar");
            btn_nuevo.setStyle("-fx-background-color: red");
            ObservableList<RowConstraints> ROWS = tabla.getRowConstraints();
            RowConstraints row = ROWS.get(0);
            row.setPercentHeight(45);
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
    TextField nombre;
    TextArea desc;
    TextField cost;
    FlowPane panel;
    
    private void actualizar(){
       ArrayList<Servicios> servs = (ArrayList<Servicios>) obtenerSevicios(est.getIdEstacionamiento());
       tabla.getChildren().remove(2, tabla.getChildren().size());
       for(int i = 0; i<servs.size(); i++){
           try{
               Servicios objServ = servs.get(i);
           
                panel = new FlowPane(Orientation.HORIZONTAL);
                panel.setAlignment(Pos.CENTER);
                nombre = new TextField(objServ.getNombre());
                nombre.setDisable(true);
                
                nombre.setStyle("-fx-font-size: 14px");
                panel.getChildren().add(new Label("Nombre: "));
                panel.getChildren().add(nombre);
                desc = new TextArea(objServ.getDescripcion());
                desc.setPrefRowCount(3);
                desc.setDisable(true);
                desc.setStyle("-fx-font-size: 14px");
                panel.getChildren().add(new Label("Descripción: "));
                panel.getChildren().add(desc);
                cost = new TextField(String.valueOf(objServ.getCosto()));
                
                cost.setDisable(true);
                cost.setOnKeyPressed( new EventHandler<KeyEvent>() {
                   @Override
                   public void handle(KeyEvent event) {
                       validarCosto(event);
                   }
               });
                
                cost.setStyle("-fx-font-size: 14px");
                panel.getChildren().add(new Label("$"));
                panel.getChildren().add(cost);
                Button editar = new Button();
                final TextField txt_nombre = nombre;
                final TextArea txt_desc = desc;
                final TextField txt_cost = cost;
                final Button btnConfirm = new Button("");
                btnConfirm.setStyle("-fx-color: white");
                editar.setStyle("-fx-color: white;");
                btnConfirm.setVisible(false);
                btnConfirm.setDisable(true);
                editar.setAccessibleHelp(String.valueOf(objServ.getIdServicio()));
                editar.setOnAction(new EventHandler<ActionEvent>() {
                   @Override
                   public void handle(ActionEvent event) {
                       txt_nombre.setDisable(false);
                       txt_desc.setDisable(false);
                       txt_cost.setDisable(false);
                       btnConfirm.setText("Guardar Cambios");
                       btnConfirm.setStyle("-fx-background-color: darkgreen");
                       btnConfirm.setVisible(true);
                       btnConfirm.setDisable(false);
                       Button source = (Button)event.getSource();
                       btnConfirm.setAccessibleHelp(source.getAccessibleHelp());
                       btnConfirm.setOnAction(new EventHandler<ActionEvent>() {
                           @Override
                           public void handle(ActionEvent event) {
                               Button source = (Button)event.getSource();
                               
                               CuadrosDialogo.infoDialog(actualizarServicio(Integer.parseInt(source.getAccessibleHelp()), txt_nombre.getText(), txt_desc.getText(), Float.parseFloat(txt_cost.getText())));
                               actualizar();
                           }
                       });
                       
                   }
               });
                editar.setText("Editar");
                editar.setStyle("-fx-background-color: #F7CA18; -fx-color: white");
                Button eliminar = new Button();
                eliminar.setStyle("-fx-color: white");
                eliminar.setAccessibleHelp(String.valueOf(objServ.getIdServicio()));
                eliminar.setOnAction(new EventHandler<ActionEvent>() {
                   @Override
                   public void handle(ActionEvent event) {
                      
                        CuadrosDialogo.infoDialog(eliminarServ(Integer.parseInt((((Button)event.getSource()).getAccessibleHelp()))));
                        actualizar();

                   }
               });
                eliminar.setText("Eliminar");
                eliminar.setStyle("-fx-background-color: #D32F2F; -fx-color: white");
                panel.getChildren().add(editar);
                panel.getChildren().add(eliminar);
                panel.getChildren().add(btnConfirm);
                panel.setHgap(5);
                panel.setVgap(5);
                panel.setStyle("-fx-background-color: #52B3D9;\n" +
                            "    -fx-color: white;\n" +
                            "    -fx-border-color: #4183D7;\n" +
                            "    -fx-border-radius: 5px;");
                tabla.addColumn(1, panel);
                
                ObservableList<RowConstraints> ROWS = tabla.getRowConstraints();
                RowConstraints row = ROWS.get(0);
                row.setPercentHeight(5.6);
           }catch(Exception e){
               System.out.println("Error en el controlador de servicios: "+e.getMessage());
           } 
       }
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
    public void validarCosto(KeyEvent ev){
        String letra = ev.getCharacter();
       switch(letra){
           case "0":
           {
               break;
           }
           case "1":
           {
               break;
           }
           case "2":
           {
               break;
           }
           case "3":
           {
               break;
           }
           case "4":
           {
               break;
           }
           case "5":
           {
               break;
           }
           case "6":
           {
               break;
           }
           case "7":
           {
               break;
           }
           case "8":
           {
               break;
           }
           case "9":
           {
               break;
           }
           case ".":
           {
               break;
           }
           case ",":
           {
               break;
           }
           default:
           {
               ev.consume();
           }
       }
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
            Logger.getLogger(ServiciosOfertasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(ServiciosOfertasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    public void guardarServ(ActionEvent ev) throws ClassNotFoundException_Exception{
        String estado = "";
        String nombre = txt_nombreOf.getText();
        String desc = txt_desc.getText();
        Float cost = Float.parseFloat(text_cost.getText());
        if(nombre.equals("") || desc.equals("") ||  cost.equals(""))
        {
            CuadrosDialogo.infoDialog("Favor de llenar todos los campos");
        }
        else{
            estado = nuevoServicioOferta(nombre,desc,cost, est.getIdEstacionamiento());
            if(estado.equals("Exito")){
                CuadrosDialogo.successDialog(estado);
                nuevoServ.setVisible(false);
                btn_nuevo.setText("Nuevo servicio u oferta");
                btn_nuevo.setStyle("-fx-background-color: #26A65B");
                txt_nombreOf.setText("");
                txt_desc.setText("");
                text_cost.setText("");
                actualizar();
            }else{
                CuadrosDialogo.errorDialog(estado);
            }
        }
    }

    private static String nuevoServicioOferta(java.lang.String nombreServ, java.lang.String descServ, java.lang.Float costServ, int idEsta) throws ClassNotFoundException_Exception {
        ParkPlatzWSC.ParkPlatzWS_Service service = new ParkPlatzWSC.ParkPlatzWS_Service();
        ParkPlatzWSC.ParkPlatzWS port = service.getParkPlatzWSPort();
        return port.nuevoServicioOferta(nombreServ, descServ, costServ, idEsta);
    }

    private static java.util.List<ParkPlatzWSC.Servicios> obtenerSevicios(int idEstacionamiento) {
        ParkPlatzWSC.ParkPlatzWS_Service service = new ParkPlatzWSC.ParkPlatzWS_Service();
        ParkPlatzWSC.ParkPlatzWS port = service.getParkPlatzWSPort();
        return port.obtenerSevicios(idEstacionamiento);
    }

    private static String eliminarServ(int idServicio) {
        ParkPlatzWSC.ParkPlatzWS_Service service = new ParkPlatzWSC.ParkPlatzWS_Service();
        ParkPlatzWSC.ParkPlatzWS port = service.getParkPlatzWSPort();
        return port.eliminarServ(idServicio);
    }

    private static String actualizarServicio(int idServicio, java.lang.String nuevoNombre, java.lang.String nuevaDescripcion, float nuevoCosto) {
        ParkPlatzWSC.ParkPlatzWS_Service service = new ParkPlatzWSC.ParkPlatzWS_Service();
        ParkPlatzWSC.ParkPlatzWS port = service.getParkPlatzWSPort();
        return port.actualizarServicio(idServicio, nuevoNombre, nuevaDescripcion, nuevoCosto);
    }

    private static String delEsquema(ParkPlatzWSC.Estacionamiento delEsquema) {
        ParkPlatzWSC.ParkPlatzWS_Service service = new ParkPlatzWSC.ParkPlatzWS_Service();
        ParkPlatzWSC.ParkPlatzWS port = service.getParkPlatzWSPort();
        return port.delEsquema(delEsquema);
    }
}
