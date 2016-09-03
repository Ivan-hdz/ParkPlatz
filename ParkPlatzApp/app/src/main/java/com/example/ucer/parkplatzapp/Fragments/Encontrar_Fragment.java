package com.example.ucer.parkplatzapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ucer.Controlador.Conductor;
import com.example.ucer.WebService.WebService;
import com.example.ucer.parkplatzapp.Main_Window;
import com.example.ucer.parkplatzapp.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Encontrar_Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class Encontrar_Fragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    String amigos, cad, nombre;
    private String myCorreo, coAmigo, nomAmigo;
    private View view;
    private TextView tv, tv1, tv2;
    private LinearLayout linearLayout;
    private Button btn, btn_agre;
    private List<Conductor> lista;
    private Conductor conductor;

    public Encontrar_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_encontrar_, container, false);
        linearLayout = (LinearLayout) view.findViewById(R.id.lista_busq);
        btn = (Button) view.findViewById(R.id.btn_busAmigo);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new tareaBus().execute();
            }
        });
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

    private class tareaAgregar extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            cad = WebService.invokeNewAmigo(myCorreo, coAmigo, nomAmigo);
            return null;
        }

        @Override
        protected void onPreExecute() {
            SharedPreferences preferences = getActivity().getSharedPreferences("preferencias", Context.MODE_PRIVATE);
            myCorreo = preferences.getString("correo", "");
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(getContext(), cad, Toast.LENGTH_SHORT).show();

            Intent i = new Intent(getContext(), Main_Window.class);
            startActivity(i);
            getActivity().finishAfterTransition();
        }
    }

    private class tareaBus extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... params) {
            lista = WebService.invokeBusAmigo(nombre);
            return null;
        }

        @Override
        protected void onPreExecute() {
            EditText et = (EditText) view.findViewById(R.id.name_amigo);
            nombre = et.getText().toString();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if(lista.isEmpty()){
                Toast.makeText(getContext(), "Amigo no encontrado", Toast.LENGTH_SHORT).show();
            }
            else{
                for(int i = 0; i < lista.size(); i++){
                    conductor = new Conductor();
                    conductor.setCorreo(lista.get(i).getCorreo());
                    conductor.setNombre(lista.get(i).getNombre());

                    tv = new TextView(getContext());
                    tv.setText(conductor.getNombre());
                    tv.setPadding(10, 10, 10, 5);

                    tv1 = new TextView(getContext());
                    tv1.setText(conductor.getCorreo());
                    tv1.setPadding(12, 7, 10, 5);

                    btn_agre = new Button(getContext());
                    btn_agre.setText("Agregar");
                    btn_agre.setPadding(50, 7, 10, 10);
                    btn_agre.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            coAmigo = conductor.getCorreo();
                            nomAmigo = conductor.getNombre();
                            new tareaAgregar().execute();
                        }
                    });
                    linearLayout.addView(tv);
                    linearLayout.addView(tv1);
                    linearLayout.addView(btn_agre);
                }
            }
        }
    }
}
