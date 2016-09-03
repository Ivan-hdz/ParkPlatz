/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Beans;

/**
 *
 * @author honte_000
 */
public class Feedback {
    private int idFeedback;
    private String descripcion;
    private int idUsuarioRedactor;
    private String prioridad;
    private String fecha;
    private int idEstaADarAviso;
    private String correoUsuarioRedactor;
    
    public int getIdEstaADarAviso() {
        return idEstaADarAviso;
    }

    public void setIdEstaADarAviso(int idEstaADarAviso) {
        this.idEstaADarAviso = idEstaADarAviso;
    }
    /**
     * @return the idFeedback
     */
    public int getIdFeedback() {
        return idFeedback;
    }

    /**
     * @param idFeedback the idFeedback to set
     */
    public void setIdFeedback(int idFeedback) {
        this.idFeedback = idFeedback;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the idUsuarioRedactor
     */
    public int getIdUsuarioRedactor() {
        return idUsuarioRedactor;
    }

    /**
     * @param idUsuarioRedactor the idUsuarioRedactor to set
     */
    public void setIdUsuarioRedactor(int idUsuarioRedactor) {
        this.idUsuarioRedactor = idUsuarioRedactor;
    }

    /**
     * @return the prioridad
     */
    public String getPrioridad() {
        return prioridad;
    }

    /**
     * @param prioridad the prioridad to set
     */
    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the correoUsuarioRedactor
     */
    public String getCorreoUsuarioRedactor() {
        return correoUsuarioRedactor;
    }

    /**
     * @param correoUsuarioRedactor the correoUsuarioRedactor to set
     */
    public void setCorreoUsuarioRedactor(String correoUsuarioRedactor) {
        this.correoUsuarioRedactor = correoUsuarioRedactor;
    }
}
