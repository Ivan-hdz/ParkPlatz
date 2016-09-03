package com.example.ucer.parkplatzapp.Fragments;

import android.content.Context;
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

import com.example.ucer.WebService.WebService;
import com.example.ucer.parkplatzapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Muro_Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class Muro_Fragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private View view;
    private String msj, cad, comentarios;
    private TextView tv;
    private LinearLayout linearLayout;
    private Button btn;

    public Muro_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_muro_, container, false);
        linearLayout = (LinearLayout) view.findViewById(R.id.lista_msj);
        btn = (Button) view.findViewById(R.id.btn_comen);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et = (EditText) view.findViewById(R.id.comen);
                msj = et.getText().toString();
                if(!msj.trim().equals("")){
                    new tareaEnviar().execute();
                    linearLayout.removeAllViews();
                    et.setText("");
                    new tareaMuro().execute();
                }
                else{
                    Toast.makeText(getContext(), "Escribe algo Campeon", Toast.LENGTH_SHORT).show();
                }

            }
        });
        new tareaMuro().execute();
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

    private class tareaMuro extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... params) {
            comentarios = WebService.invokeMuro();
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            String []coments = comentarios.split("/;/");

            if(!coments[0].equals("v4zio")){
                for(int i = 0; i < coments.length; i++){
                    tv = new TextView(getContext());
                    tv.setText("Anonimo:" + coments[i]);
                    tv.setPadding(10, 10, 10, 10);
                    linearLayout.addView(tv);
                }
            }
            else{
                tv = new TextView(getContext());
                tv.setText("NO HAY COMENTARIOS");
                tv.setPadding(10, 10, 10, 10);
                linearLayout.addView(tv);
            }

        }
    }

    private class tareaEnviar extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... params) {
            cad = WebService.invokeComen(msj);
            return null;
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(getContext(), cad, Toast.LENGTH_SHORT).show();
        }
    }
}
