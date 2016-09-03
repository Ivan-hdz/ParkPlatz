package com.example.ucer.parkplatzapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ucer.Controlador.Estacionamiento;
import com.example.ucer.WebService.WebService;
import com.example.ucer.parkplatzapp.Mapas.Nav_Map;

public class Detalles_Activity extends AppCompatActivity {

    private String cadena, idEsta;
    private Estacionamiento esta;
    private android.support.v4.app.FragmentManager fragmentManager;
    private AppCompatActivity activity;
    private boolean isRec, isFav;
    private String correo;


    private ImageView img, fav;
    private TextView tv_nombre, tv_descrip, tv_lugaresD, tv_tarifa, tv_horario, tv_lugares, tv_hmax, tv_direccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_);

        esta = new Estacionamiento();

        img = (ImageView) findViewById(R.id.imagen_detalles);
        fav = (ImageView) findViewById(R.id.image_fav);
        tv_nombre = (TextView) findViewById(R.id.nombre_detalles);
        tv_descrip = (TextView) findViewById(R.id.descripcion_detalles);
        tv_horario = (TextView) findViewById(R.id.horario);
        tv_tarifa = (TextView) findViewById(R.id.tarifa);
        tv_lugares = (TextView) findViewById(R.id.lugaresT);
        tv_lugaresD = (TextView) findViewById(R.id.lugaresD);
        tv_hmax = (TextView) findViewById(R.id.hMax);
        tv_direccion = (TextView) findViewById(R.id.direccion_detalles);

        SharedPreferences preferences = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        correo = preferences.getString("correo", "");

        idEsta = getIntent().getStringExtra("id");

        tareaDetalles tarea = new tareaDetalles();
        tarea.execute();
        new tareaLugares().execute();

    }

    public void click_llevame(View view){
        new tareaReciente().execute();
    }

    public void click_Fav(View view){
        if(isFav){
            new tareaEliminarFav().execute();
        }else {
            new tareaNuevoFav().execute();
        }
    }

    private class tareaDetalles extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            cadena = WebService.invokeDetallesEstaWS(idEsta);
            isFav = WebService.invokeisFav(correo, idEsta);
            //String a = WebService.invokeLugares(idEsta);
            return null;
        }

        @Override
        protected void onPostExecute(Void result){
            String detalles[] ;
            detalles = cadena.split("//");

            esta.setId(detalles[0]);
            esta.setNombre(detalles[1]);
            esta.setCoorX(detalles[2]);
            esta.setCoorY(detalles[3]);
            esta.setDireccion(detalles[4]);
            esta.setHorario(detalles[5]);
            esta.setTarifa(detalles[6]);
            esta.sethMax(detalles[7]);
            esta.setDescripcion(detalles[8]);
            esta.setImg(R.drawable.icon_default_dos);

            tv_nombre.setText(esta.getNombre());
            tv_descrip.setText("Descripcion: " + esta.getDescripcion());
            tv_horario.setText("Horario: " + esta.getHorario());
            tv_tarifa.setText("Tarifa: " + esta.getTarifa());
            tv_hmax.setText("Altura Maxima: " + esta.gethMax());
            tv_direccion.setText("Direccion: " + esta.getDireccion());
            tv_lugares.setText("Lugares: " + "Pendiente");

            if(isFav){
                fav.setImageResource(R.drawable.icon_fav);
            }
            else{
                fav.setImageResource(R.drawable.icon_no_fav_dos);
            }
        }

    }

    private class tareaEliminarFav extends AsyncTask<String, Void, Void>{
        @Override
        protected Void doInBackground(String... params) {
            cadena = WebService.invokeBorraFavWS(correo, esta.getId());
            return null;
        }

        @Override
        protected void onPostExecute(Void result){
            fav.setImageResource(R.drawable.icon_no_fav);
            isFav = false;
        }

    }

    private class tareaNuevoFav extends AsyncTask<String, Void, Void>{
        @Override
        protected Void doInBackground(String... params) {
            cadena = WebService.invokeNuevoFavorito(correo, esta.getId());
            return null;
        }

        @Override
        protected void onPostExecute(Void result){
            fav.setImageResource(R.drawable.icon_fav);
            isFav = true;
        }

    }

    private class tareaReciente extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... params) {
            WebService.invokeNuevoReciente(correo, esta.getId());
            return null;
        }

        @Override
        protected void onPreExecute() {
            SharedPreferences preferences = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
            correo = preferences.getString("correo", "");
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Double x = Double.parseDouble(esta.getCoorX());
            Double y = Double.parseDouble(esta.getCoorY());
            Uri gmmIntentUri = Uri.parse("google.navigation:q=" + x + "," + y);
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        }
    }

    private class tareaLugares extends AsyncTask<Void, Void, Void>{
        String []lugares;
        @Override
        protected void onPreExecute() {

        }


        @Override
        protected void onPostExecute(Void aVoid) {
            tv_lugares.setText("Total de Lugares:  " + lugares[0]);
            tv_lugaresD.setText("Lugares Disponibles:  " + lugares[1]);
        }

        @Override
        protected Void doInBackground(Void... params) {
            lugares = WebService.invokeLugares(idEsta).split("//");
            return null;
        }
    }

}
