package com.example.ucer.parkplatzapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.ucer.Controlador.Conductor;
import com.example.ucer.WebService.WebService;

import java.util.regex.Pattern;

public class ConfigCuenta extends AppCompatActivity {

    private Conductor conductor;
    private TextInputLayout til_nombre, til_aPaterno, til_aMaterno;
    private TextInputLayout til_pass, til_pass2, til_correo;
    private final Pattern patron = Pattern.compile("^[a-zA-Z ]+$");
    private String nombre, aPaterno, aMaterno, correo, contraseña, contraseña2, cadena;
    private Context context;
    private boolean empyPass;
    private String datos[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_cuenta);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        conductor = new Conductor();
        til_nombre = (TextInputLayout) findViewById(R.id.til_nombre1);
        til_aPaterno = (TextInputLayout) findViewById(R.id.til_aPaterno1);
        til_aMaterno = (TextInputLayout) findViewById(R.id.til_aMaterno1);
        til_pass = (TextInputLayout) findViewById(R.id.til_pass11);
        til_pass2 = (TextInputLayout) findViewById(R.id.til_pass21);
        til_correo = (TextInputLayout) findViewById(R.id.til_correo1);

        tareaTraerDatos tarea1 = new tareaTraerDatos();
        tarea1.execute();

    }

    public void modificar_evt(View view){

        nombre = til_nombre.getEditText().getText().toString();
        aPaterno = til_aPaterno.getEditText().getText().toString();
        aMaterno = til_aMaterno.getEditText().getText().toString();
        correo = til_correo.getEditText().getText().toString();
        contraseña = til_pass.getEditText().getText().toString();
        contraseña2 = til_pass2.getEditText().getText().toString();

        if(validNombre(nombre) && validAPaterno(aPaterno) && validAMaterno(aMaterno) &&
                validCorreo(correo) && validContraseña(contraseña, contraseña2)){

            if(empyPass){
                SharedPreferences sharedPreferences = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
                contraseña = sharedPreferences.getString("pass", "");
            }
            else{

            }

            tareaModificar tarea = new tareaModificar();
            tarea.execute();
        }
        else{
            Toast.makeText(this, "Corrige los Datos Marcados", Toast.LENGTH_LONG).show();
        }

    }


    public void cancelar_event2(View view){

        Intent newAct = new Intent(this, Main_Window.class);
        startActivity(newAct);
        finish();
    }


    public boolean validNombre(String n){
        n = n.trim();
        if(n.isEmpty()){
            til_nombre.setError("Completa el Campo");
            return false;
        }
        else {
            if(!patron.matcher(n).matches() || n.length() > 30){
                til_nombre.setError("Formato Invalido");
                return false;
            }
            else{
                til_nombre.setError(null);
            }
        }

        return true;
    }

    public boolean validAPaterno(String n){
        n = n.trim();
        if(n.isEmpty()){
            til_aPaterno.setError("Completa el Campo");
            return false;
        }
        else {
            if (!patron.matcher(n).matches() || n.length() > 30) {
                til_aPaterno.setError("Formato Invalido");
                return false;
            } else {
                til_aPaterno.setError(null);
            }
        }
        return true;
    }

    public boolean validAMaterno(String n){
        n = n.trim();
        if(n.isEmpty()){
            til_aMaterno.setError("Completa el Campo");
            return false;
        }
        else {
            if (!patron.matcher(n).matches() || n.length() > 30) {
                til_aMaterno.setError("Formato Invalido");
                return false;
            } else {
                til_aMaterno.setError(null);
            }
        }
        return true;
    }

    public boolean validCorreo(String s){
        s = s.trim();
        if(s.isEmpty()){
            til_correo.setError("Completa el Campo");
            return false;
        }
        else {
            if (!Patterns.EMAIL_ADDRESS.matcher(s).matches()) {
                til_correo.setError("Correo Invalido");
                return false;
            } else {
                til_correo.setError(null);
            }
        }
        return true;
    }

    public boolean validContraseña(String pass1, String pass2){


        if(pass1.isEmpty() && pass2.isEmpty()){
            empyPass = true;
            return true;
        }else {
            if (!pass1.equals(pass2)){
                til_pass2.setError("No Coinciden las Constraseñas");
                return false;
            }
            else{
                empyPass = false;
                til_pass2.setError(null);
            }
        }
        return true;
    }

    private class tareaModificar extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            cadena = WebService.invokeModificarDatosWS(nombre, aPaterno, aMaterno, correo, contraseña);
            return null;
        }

        @Override
        protected void onPostExecute(Void result){
            if(cadena.equals("Actualizacion exitosa!")){
                SharedPreferences preferencias = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferencias.edit();
                editor.putString("correo", correo);
                editor.putString("pass", contraseña);
                editor.commit();

                Toast.makeText(getApplicationContext(),"Exito", Toast.LENGTH_SHORT).show();
                Intent newAct = new Intent(getApplicationContext(), Main_Window.class);
                startActivity(newAct);
                finish();
            }
            else{
                Toast.makeText(getApplicationContext(),"Intente mas Tarde", Toast.LENGTH_SHORT).show();
            }



        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }

    private class tareaTraerDatos extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            SharedPreferences preferences = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
            correo = preferences.getString("correo", "");
            cadena = WebService.invokeRecuperaDatosWS(correo);
            return null;
        }

        @Override
        protected void onPostExecute(Void result){
            String datos_temp[];
            datos_temp = cadena.split("//");
            til_nombre.getEditText().setText(datos_temp[0]);
            til_aPaterno.getEditText().setText(datos_temp[1]);
            til_aMaterno.getEditText().setText(datos_temp[2]);
            til_correo.getEditText().setText(correo);
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }
}
