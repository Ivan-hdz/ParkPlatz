package com.example.ucer.parkplatzapp.Recyclers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ucer.Controlador.Estacionamiento;
import com.example.ucer.parkplatzapp.Fragments.Detalles_Fragment;
import com.example.ucer.parkplatzapp.Fragments.Favoritos_Fragment;
import com.example.ucer.parkplatzapp.Main_Window;
import com.example.ucer.parkplatzapp.R;

import java.util.List;

/**
 * Created by UCER on 25/04/16.
 */
public class AdaptadorRecs extends RecyclerView.Adapter<AdaptadorRecs.RecienteViewHolder>{

    private List<Estacionamiento> lista;
    private Context context;
    private AppCompatActivity activity;

    private String id;

    public AdaptadorRecs(Activity a,Context c, List<Estacionamiento> l){
        this.lista = l;
        this.activity = (AppCompatActivity) a;
        this.context = c;
    }

    @Override
    public RecienteViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_rec, viewGroup, false);
        return new RecienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecienteViewHolder holder, int i) {
        holder.imagen.setImageResource(lista.get(i).getImg());
        holder.fecha.setText(lista.get(i).getFecha());
        holder.nombre.setText(lista.get(i).getNombre());
        holder.direccion.setText(lista.get(i).getDireccion());
        holder.id_2 = lista.get(i).getId();

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }


    public class RecienteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView imagen;
        public TextView nombre;
        public TextView direccion;
        public TextView fecha;
        public String id_2;
        public Button btn_ir;

        public RecienteViewHolder(View v) {
            super(v);

            imagen = (ImageView) v.findViewById(R.id.imagen);
            nombre = (TextView) v.findViewById(R.id.nombre);
            direccion = (TextView) v.findViewById(R.id.descripcion);
            fecha = (TextView) v.findViewById(R.id.fecha);



            btn_ir = (Button) v.findViewById(R.id.btn_detalles2);
            btn_ir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    id = id_2;
                    activity.getIntent().putExtra("id", id);
                    activity.getIntent().putExtra("isRec", true);
                    Fragment fragmento = new Detalles_Fragment();
                    FragmentManager fragmentManager = activity.getSupportFragmentManager();
                    if(fragmento != null){
                        fragmentManager.beginTransaction().replace(R.id.contenedor_principal, fragmento).addToBackStack("back").commit();
                    }
                }
            });
        }

        @Override
        public void onClick(View view) {
            Intent a = new Intent(context, Main_Window.class);
            context.startActivity(a);
        }

    }
}
