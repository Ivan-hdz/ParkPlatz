package com.example.ucer.parkplatzapp;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ActionMenuView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ucer.WebService.WebService;

import java.util.Timer;
import java.util.TimerTask;

public class Inicio extends AppCompatActivity {

    private Intent newAct;
    private ProgressBar barra_progre;
    private Ini tarea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);



        barra_progre = (ProgressBar) findViewById(R.id.progressBar);

        tarea = new Ini();
        tarea.execute();

    }


    public void verificar() {
        boolean is;
        SharedPreferences preferencias = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        is =  preferencias.getBoolean("isLog", false);

        if(!is){
            newAct = new Intent(this, Login.class);
        }
        else {
            newAct = new Intent(this, Main_Window.class);
        }
        startActivity(newAct);
        finish();
    }



    private class Ini extends AsyncTask <String, Float, Integer> {

        protected void onPreExecute() {

            barra_progre.setProgress(0);
            barra_progre.setMax(100);
        }

        protected Integer doInBackground(String... urls) {

            for (int i = 0; i < 250; i++) {

                try {Thread.sleep(5); }
                catch (InterruptedException e) {}

                publishProgress(i/250f);
            }

            return 250;
        }

        protected void onProgressUpdate (Float... valores) {
            int p = Math.round(100*valores[0]);
            barra_progre.setProgress(p);
        }

        protected void onPostExecute(Integer bytes) {
            verificar();
        }
    }


    @Override
    public void onBackPressed(){
        super.onBackPressed();
        finish();
        tarea.cancel(true);
    }

}
