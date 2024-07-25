package com.example.login;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListaZonas extends AppCompatActivity implements Adaptador.onVerZonaClickListener {
    TextView tvdestino;
    String idusu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_zonas);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvdestino = findViewById(R.id.tvNombreDestino);

        Intent intent = getIntent();
        ArrayList<Lugar> listaLugares = (ArrayList<Lugar>) intent.getSerializableExtra("lista");
        idusu = intent.getStringExtra("idusu");
        tvdestino.setText(listaLugares.get(0).getDepartamento()+"");
        RecyclerView rvLugares = findViewById(R.id.RVZonas1);
        rvLugares.setLayoutManager(new LinearLayoutManager(this));
        rvLugares.setAdapter(new Adaptador(listaLugares, this,this));


    }


    @Override
    public void onVerZonaClick(Lugar lugar) {
        Intent intent = new Intent(ListaZonas.this, Zonas.class);
        intent.putExtra("lugar",lugar);
        intent.putExtra("idusu",idusu);
        ListaZonas.this.startActivity(intent);
    }
}