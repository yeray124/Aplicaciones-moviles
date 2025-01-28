package com.example.practica9;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.picasso.Picasso;

import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private WebView video;
    private EditText id;
    private TextView nombreMonumento;
    private TextView descripcion;
    private TextView fecha;
    private ImageView imagen;
    private TextView ubi;
    private TextView longitud;
    private TextView latitud;
    private Button comprar;
    private ControladorMonumento controlador;
    private LinearLayout caja;
    private MapView mapa;


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
        nombreMonumento = findViewById(R.id.nombre);
        descripcion = findViewById(R.id.descripcionDc);
        fecha = findViewById(R.id.Fecha);
        imagen = findViewById(R.id.Imagen);
        ubi = findViewById(R.id.Ubicacion);
        longitud = findViewById(R.id.Longitud);
        latitud = findViewById(R.id.Latitud);
        comprar = findViewById(R.id.comprar);
        caja = findViewById(R.id.caja);
        mapa = findViewById(R.id.mapa);


        MapController mapController = (MapController) mapa.getController();


        GeoPoint startPoint = new GeoPoint( 37.0245, -3.6236);
        mapa.getController().setCenter(startPoint);
        mapa.getController().setZoom(15.0);


        Configuration.getInstance().setUserAgentValue("appIdMapPractice");
        ControladorMonumento controlador = new ControladorMonumento(this);
    }

    @Override
    public void onClick(View view) {


        try {
            controlador.obtenerTodosMonumentos(new VolleyCallBack() {
                @Override
                public void onSuccess(Context context, ArrayList<Monumento> monumentos) {

                    for (Monumento monumento : monumentos) {
                        double Latitud = Double.valueOf(monumento.latitud);
                        double Longitud = Double.valueOf(monumento.longitud);

                        GeoPoint puntero = new GeoPoint(Latitud, Longitud);
                        Marker marcador = new Marker(mapa);
                        marcador.setPosition(puntero);

                        mapa.getOverlays().add(marcador);
                        mapa.invalidate();

                        marcador.setOnMarkerClickListener((marker1, mapa) -> {
                            ViewGroup.LayoutParams params = mapa.getLayoutParams();
                            params.height = 500;

                            mapa.setLayoutParams(params);

                            caja.setVisibility(View.VISIBLE);


                            // MOSTRAR INFORMACIÓN DEL CONTROLADOR
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

                            return true;
                        });
                    }
                }
            });
        } catch (ServidorPHPException e) {
            throw new RuntimeException(e);
        }
    }
}