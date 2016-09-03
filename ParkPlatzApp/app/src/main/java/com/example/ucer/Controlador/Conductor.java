package com.example.ucer.Controlador;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by UCER on 03/04/16.
 */
public class Conductor implements Parcelable {
    private String nombre;
    private String aPaterno;
    private String aMaterno;
    private String correo;
    private String contraseña;

    public Conductor(){ }

    public Conductor(String n, String ap, String am, String c){
        this.nombre = n;
        this.aPaterno = ap;
        this.aMaterno = am;
        this.correo = c;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getaPaterno() {
        return aPaterno;
    }

    public void setaPaterno(String aPaterno) {
        this.aPaterno = aPaterno;
    }

    public String getaMaterno() {
        return aMaterno;
    }

    public void setaMaterno(String aMaterno) {
        this.aMaterno = aMaterno;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(aPaterno);
        dest.writeString(aMaterno);
        dest.writeString(correo);
        dest.writeString(contraseña);
    }

    private Conductor(Parcel in){
        this.nombre = in.readString();
        this.aPaterno = in.readString();
        this.aMaterno = in.readString();
        this.correo = in.readString();
        this.contraseña = in.readString();
    }

    public static final Parcelable.Creator<Conductor> CREATOR = new Parcelable.Creator<Conductor>() {

        @Override
        public Conductor createFromParcel(Parcel source) {
            return new Conductor(source);
        }

        @Override
        public Conductor[] newArray(int size) {
            return new Conductor[size];
        }
    };
}
