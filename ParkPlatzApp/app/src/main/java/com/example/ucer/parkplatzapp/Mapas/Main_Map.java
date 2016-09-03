package com.example.ucer.parkplatzapp.Mapas;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.ucer.Controlador.Estacionamiento;
import com.example.ucer.WebService.WebService;
import com.example.ucer.parkplatzapp.Detalles_Activity;
import com.example.ucer.parkplatzapp.Fragments.Detalles_Fragment;
import com.example.ucer.parkplatzapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class Main_Map extends FragmentActivity implements OnMapReadyCallback, Detalles_Fragment.OnFragmentInteractionListener {

    private GoogleMap mMap;
    private List<Estacionamiento> lista;
    private FragmentManager fragmentManager;
    private AppCompatActivity activity;
    MarkerOptions myMark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        fragmentManager = getSupportFragmentManager();

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng CDMX = new LatLng(19.435796, -99.139567);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom((CDMX), 11));
        mMap.isMyLocationEnabled();

        new tareaMiMapa().execute();

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

            Intent in = new Intent(getApplicationContext(), Detalles_Activity.class);
            in.putExtra("id", marker.getTitle());
            startActivity(in);
            Toast.makeText(getApplicationContext(), "Click" + marker.getTitle(), Toast.LENGTH_LONG).show();
            return false;
            }
        });

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private class tareaMiMapa extends AsyncTask<Void, Void, Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Double x, y;
            for(int i = 0; i < lista.size(); i++){
                x = Double.parseDouble(lista.get(i).getCoorX());
                y = Double.parseDouble(lista.get(i).getCoorY());
                myMark = new MarkerOptions();
                myMark.icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_inicio));
                myMark.anchor(0.0f, 1.0f);
                myMark.position(new LatLng(x, y));
                myMark.title(lista.get(i).getId());
                mMap.addMarker(myMark);
            }
        }

        @Override
        protected Void doInBackground(Void... params) {
            lista = WebService.invokeMiMapaWs();

            return null;
        }
    }
}
