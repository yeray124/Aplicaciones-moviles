package com.example.practica9;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    
    private WebView video;
    private Button buscar;
    private EditText id;
    private TextView nombreMonumento;
    private TextView descripcion;
    private TextView fecha;
    private ImageView imagen;
    private TextView ubi;
    private TextView longitud;
    private TextView latitud;
    private TextView error;
    private Button comprar;
    private ControladorMonumento controlador;
    private LinearLayout caja;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        video = findViewById(R.id.video);
        buscar = findViewById(R.id.buscar);
        id = findViewById(R.id.id);
        nombreMonumento = findViewById(R.id.nombre);
        descripcion = findViewById(R.id.descripcionDc);
        fecha = findViewById(R.id.Fecha);
        imagen = findViewById(R.id.Imagen);
        ubi = findViewById(R.id.Ubicacion);
        longitud = findViewById(R.id.Longitud);
        latitud = findViewById(R.id.Latitud);
        error = findViewById(R.id.error);
        comprar = findViewById(R.id.comprar);
        caja = findViewById(R.id.caja);

        buscar.setOnClickListener(this);

        controlador = new ControladorMonumento(this);
    }
    @Override
    public void onClick(View view) {

        String monumentId = id.getText().toString();

        if (monumentId.isEmpty()) {
            error.setText("Por favor, introduce un ID válido");
            error.setVisibility(View.VISIBLE);
        } else {
            try {
                error.setVisibility(View.GONE);
                controlador.obtenerMonumentoID(monumentId, new VolleyCallBack() {
                    @Override
                    public void onSuccess(Context context, ArrayList<Monumento> monumentos) {
                        Monumento monumento = monumentos.get(0);

                        caja.setVisibility(View.VISIBLE);
                        //MOSTRAR INFORMACION DEL CONTROLADOR

                        nombreMonumento.setText(monumento.nombre);
                        fecha.setText("Construido: " + monumento.fecha);
                        Picasso.get().load(monumento.imagen).into(imagen);
                        descripcion.setText(monumento.descripcion);
                        latitud.setText("Latitud: " + monumento.latitud);
                        longitud.setText("Longitud: " + monumento.longitud);
                        ubi.setText(monumento.ciudad);
                        comprar.setText("Comprar tu entrada desde: " + monumento.precio + "€");
                        String html = monumento.video;
                        WebSettings settings = video.getSettings();
                        settings.setJavaScriptEnabled(true);
                        video.loadData(html, "text/html", "UTF-8");
                    }
                });
            } catch (ServidorPHPException e) {
                throw new RuntimeException(e);
            }
        }
    }
}