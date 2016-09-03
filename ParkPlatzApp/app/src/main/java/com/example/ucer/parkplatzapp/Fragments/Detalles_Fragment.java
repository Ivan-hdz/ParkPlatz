package com.example.ucer.parkplatzapp.Fragments;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ucer.Controlador.Estacionamiento;
import com.example.ucer.WebService.WebService;
import com.example.ucer.parkplatzapp.Main_Window;
import com.example.ucer.parkplatzapp.Mapas.Nav_Map;
import com.example.ucer.parkplatzapp.R;
import com.example.ucer.parkplatzapp.Recyclers.AdaptadorRecs;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

import static com.google.android.gms.maps.model.BitmapDescriptorFactory.*;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Detalles_Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class Detalles_Fragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private String cadena, idEsta;
    private View view;
    private Estacionamiento esta;
    private android.support.v4.app.FragmentManager fragmentManager;
    private AppCompatActivity activity;
    private boolean isRec, isFav;
    private String correo;
    private Button btn;
    String []lugares;

    private ImageView img, fav;
    private TextView tv_nombre, tv_descrip, tv_tarifa, tv_horario, tv_lugares, tv_hmax, tv_direccion, tv_lugaresD;


    public Detalles_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_detalles_, container, false);
        esta = new Estacionamiento();

        btn = (Button) view.findViewById(R.id.btn_llevame);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new tareaReciente().execute();
            }
        });
        img = (ImageView) view.findViewById(R.id.imagen_detalles);
        fav = (ImageView) view.findViewById(R.id.image_fav);
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFav){
                    new tareaEliminarFav().execute();
                }else {
                    new tareaNuevoFav().execute();
                }
            }
        });
        tv_nombre = (TextView) view.findViewById(R.id.nombre_detalles);
        tv_descrip = (TextView) view.findViewById(R.id.descripcion_detalles);
        tv_horario = (TextView) view.findViewById(R.id.horario);
        tv_tarifa = (TextView) view.findViewById(R.id.tarifa);
        tv_lugares = (TextView) view.findViewById(R.id.lugaresT);
        tv_lugaresD = (TextView) view.findViewById(R.id.lugaresD);
        tv_hmax = (TextView) view.findViewById(R.id.hMax);
        tv_direccion = (TextView) view.findViewById(R.id.direccion_detalles);

        idEsta = getActivity().getIntent().getStringExtra("id");
        isRec = getActivity().getIntent().getBooleanExtra("isRec", false);

        //activity = (AppCompatActivity) getActivity();
        //fragmentManager = activity.getSupportFragmentManager();

        tareaDetalles tarea = new tareaDetalles();
        tarea.execute();
        new tareaLugares().execute();

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }




    private class tareaDetalles extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            cadena = WebService.invokeDetallesEstaWS(idEsta);
            isFav = WebService.invokeisFav(correo, idEsta);
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

            if(isFav){
                fav.setImageResource(R.drawable.icon_fav);
            }
            else{
                fav.setImageResource(R.drawable.icon_no_fav_dos);
            }

        }

        @Override
        protected void onPreExecute() {
            SharedPreferences preferences = getActivity().getSharedPreferences("preferencias", Context.MODE_PRIVATE);
            correo = preferences.getString("correo", "");
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
            SharedPreferences preferences = getActivity().getSharedPreferences("preferencias", Context.MODE_PRIVATE);
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

        @Override
        protected void onPreExecute() {
            SharedPreferences preferencias = getActivity().getSharedPreferences("preferencias", Context.MODE_PRIVATE);
            correo =  preferencias.getString("correo", "");
        }

        @Override
        protected void onProgressUpdate(Void... values) {
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

        @Override
        protected void onPreExecute() {
            SharedPreferences preferencias = getActivity().getSharedPreferences("preferencias", Context.MODE_PRIVATE);
            correo =  preferencias.getString("correo", "");
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }

    private class tareaLugares extends AsyncTask<Void, Void, Void>{

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
