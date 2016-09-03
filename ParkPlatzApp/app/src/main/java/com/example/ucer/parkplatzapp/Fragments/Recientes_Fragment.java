package com.example.ucer.parkplatzapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ucer.Controlador.Estacionamiento;
import com.example.ucer.WebService.WebService;
import com.example.ucer.parkplatzapp.Main_Window;
import com.example.ucer.parkplatzapp.R;
import com.example.ucer.parkplatzapp.Recyclers.AdaptadorFavs;
import com.example.ucer.parkplatzapp.Recyclers.AdaptadorRecs;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Recientes_Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class Recientes_Fragment extends Fragment {

    private RecyclerView recycler;
    private LinearLayoutManager layoutManager;
    private AdaptadorRecs adaptador;
    private String correo;
    private OnFragmentInteractionListener mListener;

    private List<Estacionamiento> lista;

    public Recientes_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recientes_, container, false);

        recycler = (RecyclerView) view.findViewById(R.id.reciclador_recs);
        layoutManager = new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(layoutManager);

        tareaFragmentRecs tarea = new tareaFragmentRecs();
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private class tareaFragmentRecs extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            lista = WebService.invokeRecientesWS(correo);
            return null;
        }

        @Override
        protected void onPostExecute(Void result){

            if(lista.isEmpty()){
                Toast.makeText(getContext(), "No tienes Recientes", Toast.LENGTH_LONG).show();
                Intent newAct = new Intent(getContext(), Main_Window.class);
                startActivity(newAct);
                getActivity().finish();
            }
            else{
                adaptador = new AdaptadorRecs(getActivity(), getContext(), lista);
                recycler.setAdapter(adaptador);
            }

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
}
