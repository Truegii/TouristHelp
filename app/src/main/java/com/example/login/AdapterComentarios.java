package com.example.login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class AdapterComentarios extends RecyclerView.Adapter<AdapterComentarios.ViewHolder> {
    private ArrayList<Comentario> comentarios;
    private Context context;

    public AdapterComentarios(ArrayList<Comentario> comentarios, Context context) {
        this.comentarios = comentarios;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterComentarios.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.elemento_lista_comentarios, null, true));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(comentarios.get(position));
    }

    @Override
    public int getItemCount() {
        return comentarios.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        View vista;

        public ViewHolder(View vista) {
            super(vista);
            this.vista = vista;
        }

        void bind(Comentario comentario) {
            TextView tvUsuario = vista.findViewById(R.id.tvnombreusu);
            RatingBar rbComentarios = vista.findViewById(R.id.rblistacomentario);
            TextView tvComen = vista.findViewById(R.id.tvcomentario);
            tvUsuario.setText(comentario.getUsuario());
            tvComen.setText(comentario.getComent());
            rbComentarios.setRating(Float.parseFloat(comentario.getCalifica()));
        }
    }


}

