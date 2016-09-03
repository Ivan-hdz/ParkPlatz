package com.example.ucer.parkplatzapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ucer.Controlador.Conductor;
import com.example.ucer.WebService.WebService;

import java.util.regex.Pattern;

public class Registro extends AppCompatActivity {


    private Conductor conductor;
    private TextInputLayout til_nombre, til_aPaterno, til_aMaterno;
    private TextInputLayout til_pass, til_pass2, til_correo;
    private final Pattern patron = Pattern.compile("^[a-zA-Z ]+$");
    private String nombre, aPaterno, aMaterno, correo, contraseña, contraseña2, cadena;
    private TextView titlee;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Transition slide = TransitionInflater.from(this).inflateTransition(R.transition.slide_inicio);
        //getWindow().setEnterTransition(slide);

        conductor = new Conductor();
        til_nombre = (TextInputLayout) findViewById(R.id.til_nombre);
        til_aPaterno = (TextInputLayout) findViewById(R.id.til_aPaterno);
        til_aMaterno = (TextInputLayout) findViewById(R.id.til_aMaterno);
        til_pass = (TextInputLayout) findViewById(R.id.til_pass);
        til_pass2 = (TextInputLayout) findViewById(R.id.til_pass2);
        til_correo = (TextInputLayout) findViewById(R.id.til_correo);

        titlee = (TextView) findViewById(R.id.title);
    }


    public void registrase(View view){

        nombre = til_nombre.getEditText().getText().toString();
        aPaterno = til_aPaterno.getEditText().getText().toString();
        aMaterno = til_aMaterno.getEditText().getText().toString();
        correo = til_correo.getEditText().getText().toString();
        contraseña = til_pass.getEditText().getText().toString();
        contraseña2 = til_pass2.getEditText().getText().toString();

        if(validNombre(nombre) && validAPaterno(aPaterno) && validAMaterno(aMaterno) &&
            validCorreo(correo) && validContraseña(contraseña, contraseña2)){
            /*
                conductor.setNombre(nombre);
                conductor.setaPaterno(aPaterno);
                conductor.setaMaterno(aMaterno);
                conductor.setCorreo(correo);
                conductor.setContraseña(contraseña);

                Intent newAct = new Intent(this, Login.class);
                newAct.putExtra("obj", conductor);
                newAct.putExtra("email", correo);
                startActivity(newAct);

                SharedPreferences prefer = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefer.edit();
                editor.putString("mail", correo);
                editor.putString("pass", contraseña);
                editor.commit();

                Intent databack = new Intent();
                setResult(RESULT_OK, databack);

                Toast.makeText(this, "Exito", Toast.LENGTH_LONG).show();

                finish();
            */

            tareaRegistro tarea = new tareaRegistro();
            tarea.execute();
        }
        else{
            Toast.makeText(this, "Corrige los Datos Marcados", Toast.LENGTH_LONG).show();
        }

    }

    public void cancelar_event(View view){

        Intent newAct = new Intent(this, Login.class);
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

        if(pass1.isEmpty()){
            til_pass.setError("Complete el campo");
            return false;
        }else {
            til_pass.setError(null);
            if (pass2.isEmpty()) {
                til_pass2.setError("Complete el campo");
                return false;
            }
            else{
                if (!pass1.equals(pass2)){
                    til_pass2.setError("No Coinciden las Constraseñas");
                    return false;
                }
                else{
                    til_pass2.setError(null);
                }
            }
        }
        return true;
    }

    private class tareaRegistro extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            cadena = WebService.invokeRegistroWS(nombre, aPaterno, aMaterno, correo, contraseña);
            return null;
        }

        @Override
        protected void onPostExecute(Void result){
            if(cadena.equals("Exito")){
                Toast.makeText(getApplicationContext(), cadena, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                intent.putExtra("email", correo);
                startActivity(intent);
                finish();
            }
            else{
                Toast.makeText(getApplicationContext(), cadena, Toast.LENGTH_SHORT).show();
            }



        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }
}
