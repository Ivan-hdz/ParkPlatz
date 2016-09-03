package com.example.ucer.parkplatzapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ucer.Controlador.Conductor;
import com.example.ucer.WebService.WebService;
import com.example.ucer.parkplatzapp.Fragments.Amigos_Fragment;
import com.example.ucer.parkplatzapp.Fragments.Detalles_Fragment;
import com.example.ucer.parkplatzapp.Fragments.Encontrar_Fragment;
import com.example.ucer.parkplatzapp.Fragments.Favoritos_Fragment;
import com.example.ucer.parkplatzapp.Fragments.Feedback_Fragment;
import com.example.ucer.parkplatzapp.Fragments.Muro_Fragment;
import com.example.ucer.parkplatzapp.Fragments.Recientes_Fragment;
import com.example.ucer.parkplatzapp.Fragments.buscar;
import com.example.ucer.parkplatzapp.Mapas.Main_Map;

public class Main_Window extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Favoritos_Fragment.OnFragmentInteractionListener,
                    Recientes_Fragment.OnFragmentInteractionListener, Detalles_Fragment.OnFragmentInteractionListener,
                    Feedback_Fragment.OnFragmentInteractionListener, buscar.OnFragmentInteractionListener,
        Muro_Fragment.OnFragmentInteractionListener, Encontrar_Fragment.OnFragmentInteractionListener,
        Amigos_Fragment.OnFragmentInteractionListener{
    public static String IP = "192.168.43.177";
    private Conductor conductor;
    private String cadena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //configurarIp();
        setContentView(R.layout.activity_main__window);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //conductor = getIntent().getParcelableExtra("conductor");


        setTitle("ParkPlatz");

        //Transition slide = TransitionInflater.from(this).inflateTransition(R.transition.slide_inicio);
        //getWindow().setEnterTransition(slide);


    }

    private void configurarIp(){
        AlertDialog.Builder ipDialog = new AlertDialog.Builder(this);
        ipDialog.setTitle("Configurar IP");
        ipDialog.setMessage("Introduzca la ip que apunte al web service");
        final EditText txt_ip = new EditText(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        txt_ip.setText("192.168.");
        txt_ip.setLayoutParams(layoutParams);
        ipDialog.setView(txt_ip);
        ipDialog.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                IP = txt_ip.getText().toString();
                Toast.makeText(getApplicationContext(),"IP actualizada!", Toast.LENGTH_LONG).show();
            }
        });
        ipDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        ipDialog.show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragmento = null;
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (id == R.id.nav_inicio) {

        } else if (id == R.id.nav_mapa) {
            Intent act_map = new Intent(getApplicationContext(), Main_Map.class);
            startActivity(act_map);

        } else if (id == R.id.nav_favoritos) {
            fragmento = new Favoritos_Fragment();

        } else if (id == R.id.nav_recientes) {
            fragmento = new Recientes_Fragment();

        } else if (id == R.id.nav_cuenta) {
            Intent act_config = new Intent(this, ConfigCuenta.class);
            startActivity(act_config);

        } else if (id == R.id.nav_logout){
            Transition final_tran = TransitionInflater.from(this).inflateTransition(R.transition.fade_logout);
            getWindow().setExitTransition(final_tran);

            put_log();
            Intent logout = new Intent(this, Login.class);
            startActivity(logout);
            finish();
        } else if (id == R.id.nav_feedback){
            fragmento = new Feedback_Fragment();
        } else if (id == R.id.nav_search){
            fragmento = new buscar();
        } else if (id == R.id.nav_chat){
            Intent chat_int = new Intent(this, Chat.class);
            startActivity(chat_int);
        } else if (id == R.id.nav_muro) {
            fragmento = new Muro_Fragment();

        } else if (id == R.id.nav_amigos) {
            fragmento = new Amigos_Fragment();

        } else if (id == R.id.nav_encontrar) {
            fragmento = new Encontrar_Fragment();
        }
        if(fragmento != null) {
            fragmentManager.beginTransaction().replace(R.id.contenedor_principal, fragmento).commit();

        }

        setTitle(item.getTitle());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void put_log(){
        SharedPreferences preferencias = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putBoolean("isLog", false);
        editor.commit();
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }


}
