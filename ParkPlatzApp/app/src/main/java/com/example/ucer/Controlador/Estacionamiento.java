package com.example.ucer.Controlador;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.example.ucer.parkplatzapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by UCER on 24/04/16.
 */
public class Estacionamiento {
    private String nombre;
    private String direccion;
    private String Fecha;
    private String horario;
    private String id;
    private String coorX;
    private String coorY;
    private String hMax;

    public String getHorario() {
        return horario;
    }

    private String tarifa;
    private String descripcion;


    private Bitmap bitmap;
    private int img;


    public String getId() {
        return id;
    }

    public Estacionamiento(){

    }


    public Estacionamiento(String n, String d, String id){
        this.nombre = n;
        this.direccion = d;
        this.id = id;
        this.img = R.drawable.icon_default_uno;
    }

    public Estacionamiento(String n, String d, String f, String id){
        this.nombre = n;
        this.direccion = d;
        this.Fecha = f;
        this.id = id;
        this.img = R.drawable.icon_default_uno;
    }

    public Estacionamiento(String n, String d, Bitmap b){
        this.nombre = n;
        this.direccion = d;
        this.bitmap = b;
    }



    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getImg() {
        return img;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTarifa() {
        return tarifa;
    }

    public void setTarifa(String tarifa) {
        this.tarifa = tarifa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getCoorX() {
        return coorX;
    }

    public void setCoorX(String coorX) {
        this.coorX = coorX;
    }

    public String getCoorY() {
        return coorY;
    }

    public void setCoorY(String coorY) {
        this.coorY = coorY;
    }

    public String gethMax() {
        return hMax;
    }

    public void sethMax(String hMax) {
        this.hMax = hMax;
    }
}
