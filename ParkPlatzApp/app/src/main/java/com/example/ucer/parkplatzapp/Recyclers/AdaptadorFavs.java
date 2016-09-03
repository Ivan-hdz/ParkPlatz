package com.example.ucer.parkplatzapp.Recyclers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
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
import com.example.ucer.WebService.WebService;
import com.example.ucer.parkplatzapp.Fragments.Detalles_Fragment;
import com.example.ucer.parkplatzapp.Fragments.Favoritos_Fragment;
import com.example.ucer.parkplatzapp.Fragments.Recientes_Fragment;
import com.example.ucer.parkplatzapp.Main_Window;
import com.example.ucer.parkplatzapp.R;
import com.example.ucer.parkplatzapp.Registro;

import java.util.List;

/**
 * Created by UCER on 24/04/16.
 */
public class AdaptadorFavs extends RecyclerView.Adapter<AdaptadorFavs.FavoritoViewHolder>{

    private List<Estacionamiento> lista;
    public Context context;
    private LayoutInflater inflater;
    private AppCompatActivity activity;
    private String correo;
    private String id, lol;

    public AdaptadorFavs(Activity a, Context c, List<Estacionamiento> l){
        this.context = c;
        this.activity = (AppCompatActivity) a;
        inflater = LayoutInflater.from(c);
        this.lista = l;
    }

    @Override
    public FavoritoViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_fav, viewGroup, false);
        return new FavoritoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FavoritoViewHolder holder, int i) {
        holder.imagen.setImageResource(lista.get(i).getImg());
        //holder.imagen.setImageBitmap(lista.get(i).getBitmap());
        holder.nombre.setText(lista.get(i).getNombre());
        holder.direccion.setText(lista.get(i).getDireccion());
        holder.id_2 = lista.get(i).getId();
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class FavoritoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Campos respectivos de un item
        public ImageView imagen;
        public ImageView img_borrar;
        public TextView nombre;
        public TextView direccion;
        public Button btn_ir;
        public String id_2;

        public FavoritoViewHolder(View v) {
            super(v);


            imagen = (ImageView) v.findViewById(R.id.imagen);
            nombre = (TextView) v.findViewById(R.id.nombre);
            direccion = (TextView) v.findViewById(R.id.descripcion);
            btn_ir = (Button) v.findViewById(R.id.btn_detalles);
            btn_ir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "lol: " + nombre.getText(), Toast.LENGTH_SHORT).show();
                    id = id_2;
                    activity.getIntent().putExtra("id", id);
                    Fragment fragmento = new Detalles_Fragment();
                    FragmentManager fragmentManager = activity.getSupportFragmentManager();
                    if(fragmento != null){
                        fragmentManager.beginTransaction().replace(R.id.contenedor_principal, fragmento).addToBackStack("back3").commit();
                    }
                }
            });
            img_borrar = (ImageView) v.findViewById(R.id.imageView_borrar);
            img_borrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    id = id_2;
                    tareaEliminarFav tarea = new tareaEliminarFav();
                    tarea.execute();
                }
            });
            //visitas = (TextView) v.findViewById(R.id.visitas);
        }

        @Override
        public void onClick(View view) {
            Intent a = new Intent(context, Main_Window.class);
            context.startActivity(a);
        }
    }

    private class tareaEliminarFav extends AsyncTask<String, Void, Void>{
        @Override
        protected Void doInBackground(String... params) {
            lol = WebService.invokeBorraFavWS(correo, id);
            return null;
        }

        @Override
        protected void onPostExecute(Void result){
            Toast.makeText(context,  lol, Toast.LENGTH_SHORT).show();
            Fragment fragmento = new Favoritos_Fragment();
            FragmentManager fragmentManager = activity.getSupportFragmentManager();
            if(fragmento != null){
                fragmentManager.beginTransaction().replace(R.id.contenedor_principal, fragmento).commit();
            }
        }

        @Override
        protected void onPreExecute() {
            SharedPreferences preferencias = activity.getSharedPreferences("preferencias", Context.MODE_PRIVATE);
            correo =  preferencias.getString("correo", "");
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }

}
