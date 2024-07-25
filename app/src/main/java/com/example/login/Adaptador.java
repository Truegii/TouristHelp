package com.example.login;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder> {

    private ArrayList<Lugar> lugares;
    private Context context;
    private onVerZonaClickListener context2;

    public Adaptador(ArrayList<Lugar> lugares, Context context, onVerZonaClickListener context2) {
        this.lugares = lugares;
        this.context = context;
        this.context2 = context2;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.elemento_lista_zonas, null, true));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(lugares.get(position));
    }

    @Override
    public int getItemCount() {
        return lugares.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        View vista;

        public ViewHolder(View vista) {
            super(vista);
            this.vista = vista;
        }
        void bind(Lugar lugar){
            ImageView ivLugar = vista.findViewById(R.id.ivlistazona);
            TextView tvLugar = vista.findViewById(R.id.tvtitlezona);
            TextView tvDesc = vista.findViewById(R.id.tvdesczona);
            RatingBar rbCalifica = vista.findViewById(R.id.rblistazona);
            Picasso.get()
                    .load(lugar.getImgurl())
                    .error(R.mipmap.ic_launcher_round)
                    .into(ivLugar);
            tvLugar.setText(lugar.getNombre());
            tvDesc.setText("Distancia: "+String.format("%.2f", lugar.getDistancia())+"km");
            rbCalifica.setRating(Float.parseFloat(lugar.getCalifica()));

            ivLugar.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    //Intent intent = new Intent(context, Zonas.class);
                    //intent.putExtra("lugar",lugar);
                    //context.startActivity(intent);
                    context2.onVerZonaClick(lugar);
                }
            });
        }
    }

    public interface onVerZonaClickListener{
        void onVerZonaClick(Lugar lugar);
    }
}
