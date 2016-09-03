/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import static Controlador.LoginController.est;
import ParkPlatzWSC.ClassNotFoundException_Exception;
import ParkPlatzWSC.Estacionamiento;
import ParkPlatzWSC.IOException_Exception;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author honte_000
 */
public class InformacionEstacionamientoController implements Initializable {
    @FXML
    Label lbl_nombreEst = new Label();
    @FXML
    Label lbl_estado = new Label();
    @FXML
    Button btn_config = new Button();
    @FXML
    Button btn_servicio = new Button();
    @FXML
    Button btn_salir = new Button();
    @FXML
    Button btn_inicio = new Button();
    @FXML
    Button btn_guardar = new Button();
    @FXML
    TextField txt_nombreEsta = new TextField();
    @FXML
    TextField txt_horario = new TextField();
    @FXML
    TextArea txt_tarifa = new TextArea();
    @FXML
    TextField txt_calle = new TextField();
    @FXML
    TextField txt_colonia = new TextField();
    @FXML
    TextField txt_muniDel = new TextField();
    @FXML
    TextField txt_estado = new TextField();
    @FXML
    TextField txt_altura = new TextField();
    @FXML
    TextArea txt_desc = new TextArea();
    @FXML
    Button btn_subir = new Button();
    @FXML
    ImageView imgV = new ImageView();
    @FXML
    Button btn_feed = new Button();
    
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
         
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        txt_altura.setOnKeyTyped(new EventHandler <KeyEvent>() {
            @Override
            public void handle(KeyEvent ev) {
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
        });
        pintarDatos();
    }    
    @FXML
    public void onClickGuardar(ActionEvent ev){
        
        est.setNombreEsta(txt_nombreEsta.getText());
        est.setAlturaMaxima(Float.parseFloat(txt_altura.getText()));
        est.setCalle(txt_calle.getText());
        est.setColonia(txt_colonia.getText());
        est.setDelMuni(txt_muniDel.getText());
        est.setDescripcion(txt_desc.getText());
        est.setEstado(txt_estado.getText());
        est.setHorario(txt_horario.getText());
        est.setTarifa(txt_tarifa.getText());
       
        est = actualizarDatosEsta(est);
        
        CuadrosDialogo.successDialog("Operación exitosa!");
        pintarDatos();
    }
    private void pintarDatos(){
        lbl_nombreEst.setText(est.getNombreEsta());
        txt_altura.setText(String.valueOf(est.getAlturaMaxima()));
        txt_calle.setText(est.getCalle());
        txt_colonia.setText(est.getColonia());
        txt_desc.setText(est.getDescripcion());
        txt_estado.setText(est.getEstado());
        txt_horario.setText(est.getHorario());
        txt_muniDel.setText(est.getDelMuni());
        txt_nombreEsta.setText(est.getNombreEsta());
        txt_tarifa.setText(est.getTarifa());
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
    public void onSubir(ActionEvent ev) throws IOException, ClassNotFoundException_Exception, IOException_Exception{
        final FileChooser explorar = new FileChooser();
        configurarExplorador(explorar);
        File archivo = explorar.showOpenDialog(btn_subir.getScene().getWindow());
        if(archivo != null){
            FileInputStream in = new FileInputStream(archivo);
            byte[] b = new byte[(int)archivo.length()];
            for(int i = 0; i<b.length; i++){
                b[i] = (byte)in.read();
            }
            est.setUrlImg(recibeFoto(b, archivo.getName(), est));
            if (!est.getUrlImg().equals("")){
                CuadrosDialogo.successDialog("Operación exitosa!");
                String img = "";
                if(est.getUrlImg()== null){
                    img = "/Vista/Imagenes/autoLogin.jpg";
                }else{
                    File file = new File(est.getUrlImg());
                    URL url_foto = file.toURI().toURL();
                    img = url_foto.toExternalForm();
                    System.out.println(img);
                }
                imgV.setImage(new Image(img,200, 150, true, true));

            }else{
                CuadrosDialogo.errorDialog("Hubo un error!");
            }
        }else{
            ev.consume();
        }
    }
   
    private void configurarExplorador(FileChooser fc){
        fc.setTitle("Eliga una imagen para su estacionamiento...");
        fc.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Un JPG, GIF o PNG ", "*.jpg","*.gif","*.png")
        );
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
        ((Stage)btn_inicio.getScene().getWindow()).close();
    }
    @FXML
    public void onClickFeed(ActionEvent ev) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/Vista/Feedback.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Enviar feedback.");
        stage.getIcons().add(new Image("/Vista/Imagenes/parkLogo.png"));
        stage.setScene(scene);
        stage.show();
        ((Stage)btn_config.getScene().getWindow()).close();
    }
    private static Estacionamiento actualizarDatosEsta(ParkPlatzWSC.Estacionamiento objetoDatosActualizados) {
        ParkPlatzWSC.ParkPlatzWS_Service service = new ParkPlatzWSC.ParkPlatzWS_Service();
        ParkPlatzWSC.ParkPlatzWS port = service.getParkPlatzWSPort();
        return port.actualizarDatosEsta(objetoDatosActualizados);
    }

    private static String recibeFoto(byte[] archivoByte, java.lang.String nombreArchivo, ParkPlatzWSC.Estacionamiento objetoUsuarioDeFoto) throws ClassNotFoundException_Exception, IOException_Exception {
        ParkPlatzWSC.ParkPlatzWS_Service service = new ParkPlatzWSC.ParkPlatzWS_Service();
        ParkPlatzWSC.ParkPlatzWS port = service.getParkPlatzWSPort();
        return port.recibeFoto(archivoByte, nombreArchivo, objetoUsuarioDeFoto);
    }

    
}
