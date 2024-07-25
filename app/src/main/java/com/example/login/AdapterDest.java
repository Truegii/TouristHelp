package com.example.login;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdapterDest extends RecyclerView.Adapter<AdapterDest.ViewHolder>{
    private ArrayList<Destino> destinos;

    private ArrayList<Lugar> listaItem;

    private onListaLugaresClickListener context;
    RequestQueue requestQueue;

    public AdapterDest(ArrayList<Destino> destinos, onListaLugaresClickListener context) {
        this.destinos = destinos;
        this.context = context;

    }

    @NonNull
    @Override
    public AdapterDest.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterDest.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.elemento_lista_destinos, null, true));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(destinos.get(position));
    }




    @Override
    public int getItemCount() {
        return destinos.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        View vista;

        public ViewHolder(View vista) {
            super(vista);
            this.vista = vista;
        }
        void bind(Destino destino){
            ImageView ivDestino = vista.findViewById(R.id.ivlistadestino);
            TextView tvLugar = vista.findViewById(R.id.tvtitledestino);
            TextView tvDep = vista.findViewById(R.id.tvdescdestino);
            tvLugar.setText(destino.getNombre());
            tvDep.setText(destino.getDepartamento());
            Picasso.get()
                    .load(destino.getImg())
                    .error(R.mipmap.ic_launcher_round)
                    .into(ivDestino);

            ivDestino.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    //listarZonas(destino.getNombre());
                    //Toast.makeText(context," Lista llenada: "+listaItem.get(0).getNombre(),Toast.LENGTH_SHORT).show();
                    //Intent intent = new Intent(context, ListaZonas.class);
                    //intent.putExtra("lista", listaItem);

                    //context.startActivity(intent);
                    context.onListaLugaresClick(destino.getNombre());


                }
            });
        }


    }
    public interface onListaLugaresClickListener{
        void onListaLugaresClick(String depa);
    }
}
