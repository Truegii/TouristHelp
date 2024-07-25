package com.example.login;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;

import android.widget.TextView;
import android.widget.Toast;

import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.login.ui.gallery.GalleryFragment;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Zonas extends AppCompatActivity implements View.OnClickListener {
    TextView zona1, zona2;
    ImageView imgZona;
    RatingBar rbZona;
    RequestQueue requestQueue;
    RecyclerView rvComentarios;
    Button btnMapas;
    Lugar lugar;
    String idusu;
    Button btnOpina;
    EditText etComent;
    RatingBar rbComentario;
    ArrayList<Comentario> listaComentarios = new ArrayList<Comentario>();
    private static final String URL1 = "https://apptouristhelp.000webhostapp.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_zonas);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        zona1 = (TextView) findViewById(R.id.text_zona1);
        zona2 = (TextView) findViewById(R.id.text_zona2);
        imgZona = (ImageView) findViewById(R.id.imgZona1);
        rbZona = (RatingBar) findViewById(R.id.rtgZona);
        requestQueue = Volley.newRequestQueue(this);

        Intent intent = getIntent();
        lugar = (Lugar) intent.getSerializableExtra("lugar");
        idusu = intent.getStringExtra("idusu");

        zona1.setText(lugar.getNombre());
        zona2.setText(lugar.getDescri());
        Picasso.get()
                .load(lugar.getImgurl())
                .error(R.mipmap.ic_launcher_round)
                .into(imgZona);
        rbZona.setRating(Float.parseFloat(lugar.getCalifica()));
        etComent = findViewById(R.id.etOpinion);
        btnOpina = findViewById(R.id.btnPublicar);
        btnOpina.setOnClickListener(this);
        rbComentario = (RatingBar) findViewById(R.id.rbComentario);
        rvComentarios = (RecyclerView) findViewById(R.id.RVComentarios);
        btnMapas = findViewById(R.id.btnRuta);
        btnMapas.setOnClickListener(this);
        listComents();
        //Toast.makeText(Zonas.this,listaComentarios.get(0).getComent(),Toast.LENGTH_SHORT).show();

        //Toast.makeText(Zonas.this,"lugar: "+lugar.getId(),Toast.LENGTH_SHORT).show();
    }

    private void createComentario(final String usuario, final String zona, final String comentario, final String calificacion) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL1+"comentar.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Toast.makeText(Zonas.this, "Gracias por comentar!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Zonas.this, Zonas.class);
                        intent.putExtra("lugar", lugar);
                        intent.putExtra("idusu", idusu);
                        Zonas.this.startActivity(intent);
                    }
                },
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Zonas.this, error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
        ) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("idusu", usuario);
                params.put("idzona", zona);
                params.put("coment", comentario);
                params.put("valor", calificacion);
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == btnMapas.getId()){
            Intent intent3 = new Intent(Zonas.this, Mapas.class);
            intent3.putExtra("zlatitud",lugar.getZlatitud());
            intent3.putExtra("zlongitud",lugar.getZlongitud());
            intent3.putExtra("znombre",lugar.getNombre());
            Zonas.this.startActivity(intent3);

        }else if(v.getId() == btnOpina.getId()){
            String comentario = etComent.getText().toString();
            String valoracion = rbComentario.getRating() + "";
            createComentario(idusu, lugar.getId(), comentario, valoracion);
        }
    }

    public void listComents() {
        String URL2 = URL1+"listacomentarios.php?zona=" + lugar.getId();

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL2, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                listaComentarios.clear();
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject object = response.getJSONObject(i);

                        String nomusu = object.getString("nombre");
                        String Strlugar = object.getString("lugar");
                        String strComentario = object.getString("comentario");
                        String fltCalifica = object.getString("estrella");

                        Comentario comentario = new Comentario(nomusu, Strlugar, strComentario, fltCalifica);
                        listaComentarios.add(comentario);
                    }

                    rvComentarios.setLayoutManager(new LinearLayoutManager(Zonas.this));
                    rvComentarios.setAdapter(new AdapterComentarios(listaComentarios, Zonas.this));

                } catch (JSONException e) {
                    Toast.makeText(Zonas.this, "Error en Zonas: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(Zonas.this, "Error en zonas: " + volleyError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(request);
    }

}