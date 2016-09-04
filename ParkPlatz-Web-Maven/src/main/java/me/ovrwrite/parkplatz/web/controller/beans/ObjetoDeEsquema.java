/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.ovrwrite.parkplatz.web.controller.beans;

import java.io.Serializable;

/**
 *
 * @author Ivan
 */
public class ObjetoDeEsquema implements Serializable {
    
    
    
      private double coorX;
     private double coorY;
     private String img_nombre;
     private int piso;
     private String id; 
     
     public ObjetoDeEsquema(){
        
    }
    public ObjetoDeEsquema(double X, double Y, String img, int piso){
        coorX = X;
        coorY = Y;
        img_nombre = img;
        this.piso = piso;
        
    }
     
    /**
     * @return the coorX
     */
    public double getCoorX() {
        return coorX;
    }

    /**
     * @param coorX the coorX to set
     */
    public void setCoorX(double coorX) {
        this.coorX = coorX;
    }

    /**
     * @return the coorY
     */
    public double getCoorY() {
        return coorY;
    }

    /**
     * @param coorY the coorY to set
     */
    public void setCoorY(double coorY) {
        this.coorY = coorY;
    }

    /**
     * @return the img_nombre
     */
    public String getImg_nombre() {
        return img_nombre;
    }

    /**
     * @param img_nombre the img_nombre to set
     */
    public void setImg_nombre(String img_nombre) {
        this.img_nombre = img_nombre;
    }

    /**
     * @return the piso
     */
    public int getPiso() {
        return piso;
    }

    /**
     * @param piso the piso to set
     */
    public void setPiso(int piso) {
        this.piso = piso;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }


}
