package com.example.ucer.parkplatzapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ucer.WebService.WebService;
import com.example.ucer.parkplatzapp.Main_Window;
import com.example.ucer.parkplatzapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Feedback_Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Feedback_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Feedback_Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String msj, cad, correo;
    private View view;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Feedback_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Feedback_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Feedback_Fragment newInstance(String param1, String param2) {
        Feedback_Fragment fragment = new Feedback_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private Button btn_feed, btn_cancel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_feedback_, container, false);
        btn_feed = (Button) view.findViewById(R.id.boton_enviar_feed);
        btn_cancel = (Button) view.findViewById(R.id.boton_cancelar_feed);

        btn_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextInputLayout til_feed;
                EditText txt_feed;
                til_feed = (TextInputLayout) view.findViewById(R.id.til_feedback);
                txt_feed = til_feed.getEditText();
                msj = txt_feed.getText().toString();

                new tareaEnviarFeed().execute();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newAct = new Intent(getContext(), Main_Window.class);
                startActivity(newAct);
                getActivity().finish();
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private class tareaEnviarFeed extends AsyncTask<Void, Void, Void>{
        @Override
        protected void onPreExecute() {
            SharedPreferences preferencias = getActivity().getSharedPreferences("preferencias", Context.MODE_PRIVATE);
            correo =  preferencias.getString("correo", "");
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if(cad.equals("Enviado con Exito")){
                Toast.makeText(getContext(), cad, Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getContext(), Main_Window.class);
                startActivity(i);
                getActivity().finish();
            }
            else{
                Toast.makeText(getContext(), cad, Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        protected Void doInBackground(Void... params) {
            cad = WebService.invokeFeedWS(correo, msj);
            return null;
        }
    }

    public void clickFeed(View view){
        TextInputLayout til_feed;
        EditText txt_feed;
        til_feed = (TextInputLayout) view.findViewById(R.id.campo_feedback);
        txt_feed = til_feed.getEditText();
        msj = txt_feed.getText().toString();

        new tareaEnviarFeed().execute();
    }

    public void cancelar_event3(View view){
        Intent newAct = new Intent(getContext(), Main_Window.class);
        startActivity(newAct);
        getActivity().finish();
    }
}
