package com.example.ucer.parkplatzapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.ucer.Controlador.Estacionamiento;
import com.example.ucer.WebService.WebService;
import com.example.ucer.parkplatzapp.Main_Window;
import com.example.ucer.parkplatzapp.R;
import com.example.ucer.parkplatzapp.Recyclers.AdaptadorFavs;

import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

public class Favoritos_Fragment extends Fragment {

    private RecyclerView recycler;
    private LinearLayoutManager layoutManager;
    private AdaptadorFavs adaptador;
    private String correo;
    private OnFragmentInteractionListener mListener;
    private String coAmi;
    private boolean fromAmi;

    private List<Estacionamiento> lista;

    public Favoritos_Fragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favoritos_, container, false);

        recycler = (RecyclerView) view.findViewById(R.id.reciclador_favs);
        recycler.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(layoutManager);

        fromAmi = getActivity().getIntent().getBooleanExtra("fromAmigo", false);

        if(fromAmi){
            correo = getActivity().getIntent().getStringExtra("coAmigo");
            Toast.makeText(getContext(), coAmi, Toast.LENGTH_SHORT).show();
            getActivity().getIntent().putExtra("fromAmigo", false);
        }else{
            SharedPreferences preferencias = getActivity().getSharedPreferences("preferencias", Context.MODE_PRIVATE);
            correo =  preferencias.getString("correo", "");
        }


        tareaFragmentFavs tarea = new tareaFragmentFavs();
        tarea.execute();

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


    private class tareaFragmentFavs extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            lista = WebService.invokeFavoritosWS(correo);
            return null;
        }

        @Override
        protected void onPostExecute(Void result){
            if(lista.isEmpty()){
                Toast.makeText(getContext(), "No tienes Favoritos", Toast.LENGTH_LONG).show();
                Intent newAct = new Intent(getContext(), Main_Window.class);
                startActivity(newAct);
                getActivity().finish();
            }else {
                adaptador = new AdaptadorFavs(getActivity(), getContext(), lista);
                recycler.setAdapter(adaptador);
            }


        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }

    public void clickBorrarFav(View view){
        tareaFragmentFavs tarea = new tareaFragmentFavs();
        tarea.execute();
    }


}
