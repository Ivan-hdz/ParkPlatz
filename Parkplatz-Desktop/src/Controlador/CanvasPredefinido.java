/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.util.ArrayList;
import java.util.Map;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author Ivan
 */
public class CanvasPredefinido {
    private final String urlImagen;
    private FlowPane contenedor;
    private Canvas canvas;
    private GraphicsContext gc;
    private Image imgCanvs;
    private ArrayList userData = new ArrayList();
    
    public CanvasPredefinido(String urlImagen, FlowPane contenedor, String accesibleHelp){
        this.urlImagen = urlImagen;
        this.contenedor = contenedor;
        canvas = new Canvas();
        userData.add(this.getUrlImagen());
        userData.add(1);
        userData.add("not ID asigned");
        canvas.setAccessibleHelp(accesibleHelp);
        canvas.setUserData(userData);
        
        imgCanvs = new Image("/Vista/Imagenes/"+this.getUrlImagen(), 48, 48, true, true);
    }

    public CanvasPredefinido dibujar(){
        getCanvas().setWidth(getImgCanvs().getWidth());
        getCanvas().setHeight(getImgCanvs().getHeight());
        setGc(getCanvas().getGraphicsContext2D());
        getGc().drawImage(getImgCanvs(), 0, 0);
        getContenedor().getChildren().add(getCanvas());
        return this;
    }
 
    public CanvasPredefinido setDragDropProperties(){
        
        getCanvas().setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Dragboard db = getCanvas().startDragAndDrop(TransferMode.COPY);
                ClipboardContent cc = new ClipboardContent();
                cc.putImage(getImgCanvs());
                db.setContent(cc);
                event.consume();
            }
        });
        return this;
        
    }

    /**
     * @return the urlImagen
     */
    public String getUrlImagen() {
        return urlImagen;
    }


    /**
     * @return the contenedor
     */
    public FlowPane getContenedor() {
        return contenedor;
    }

    /**
     * @param contenedor the contenedor to set
     */
    public void setContenedor(FlowPane contenedor) {
        this.contenedor = contenedor;
    }

    /**
     * @return the canvas
     */
    public Canvas getCanvas() {
        return canvas;
    }

    /**
     * @param canvas the canvas to set
     */
    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    /**
     * @return the gc
     */
    public GraphicsContext getGc() {
        return gc;
    }

    /**
     * @param gc the gc to set
     */
    public void setGc(GraphicsContext gc) {
        this.gc = gc;
    }

    /**
     * @return the imgCanvs
     */
    public Image getImgCanvs() {
        return imgCanvs;
    }

    /**
     * @param imgCanvs the imgCanvs to set
     */
    public void setImgCanvs(Image imgCanvs) {
        this.imgCanvs = imgCanvs;
    }

    
}
