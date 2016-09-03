package com.example.ucer.parkplatzapp;

import android.app.Activity;
import android.app.ActivityOptions;
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
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Patterns;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ucer.Controlador.Conductor;
import com.example.ucer.WebService.WebService;

public class Login extends AppCompatActivity {

    private TextInputLayout tilCorreo, tilPass;
    private TextView tv;
    private String correo, pass, finaltexto;
    private tareaLogin tarea;
    private Conductor conductor;
    private Activity act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        tilPass = (TextInputLayout) findViewById(R.id.til_pass);
        tilCorreo = (TextInputLayout) findViewById(R.id.til_correo);
        tv = (TextView) findViewById(R.id.textView2);

        String mail = getIntent().getStringExtra("email");
        tilCorreo.getEditText().setText(mail);
        act = this;
    }

    public void crearCuentaEvent(View view){
        Intent newAct = new Intent(this, Registro.class);
        startActivityForResult(newAct, 1);
    }

    public boolean validarCorreo(String s){
        s = s.trim();
        if(s.isEmpty()){
            tilCorreo.setError("Ingrese su Correo");
            return false;
        }
        else {
            if(!Patterns.EMAIL_ADDRESS.matcher(s).matches()){
                tilCorreo.setError("Correo Invalido");
                return false;
            }
            else {
                tilCorreo.setError(null);
            }
        }

        return true;
    }

    public boolean validPass(){
        if(tilPass.getEditText().getText().toString().isEmpty()){
            tilPass.setError("Ingrese Contraseña");
            return false;
        }
        else{
            tilPass.setError(null);
        }
        return true;
    }

    public void entrar(View view){


        if(validarCorreo(tilCorreo.getEditText().getText().toString()) && validPass()) {

            correo = tilCorreo.getEditText().getText().toString();
            pass = tilPass.getEditText().getText().toString();
            tarea = new tareaLogin();
            tarea.execute();
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            finish();
        }
    }

    private class tareaLogin extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            finaltexto = WebService.invokeLoginWS(correo, pass);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

        if(finaltexto.equals("Ocurrio un Error")) {
            Toast.makeText(act, "Intente mas Tarde", Toast.LENGTH_SHORT).show();
        }
        else{
            if(!finaltexto.equals("0")){
                String datos[] = finaltexto.split(",");
                conductor = new Conductor(datos[0], datos[1], datos[2], datos[3]);

                SharedPreferences preferencias = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferencias.edit();
                editor.putBoolean("isLog", true);
                editor.putString("correo", conductor.getCorreo());
                editor.putString("pass", pass);
                editor.commit();

                Toast.makeText(act, "Bienvenido " + conductor.getNombre(), Toast.LENGTH_LONG).show();

                Intent act_ini = new Intent(act, Main_Window.class);
                act_ini.putExtra("conductor", conductor);
                startActivity(act_ini);
                finishAfterTransition();
            }
            else{
                Toast.makeText(act, "Datos Incorrectos", Toast.LENGTH_SHORT).show();
            }
        }

        }

        @Override
        protected void onPreExecute() {
            finaltexto = "01";
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }

    }

}
