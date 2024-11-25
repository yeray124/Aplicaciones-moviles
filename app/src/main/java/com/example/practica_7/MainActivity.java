package com.example.practica_7;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button empezar;
    private Button pausar;
    private TextView titulo;
    private TextView cancion;
    private MediaPlayer musica;

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
        empezar = findViewById(R.id.empezar);
        pausar = findViewById(R.id.pausar);
        titulo = findViewById(R.id.Titulo);
        cancion = findViewById(R.id.cancion);

        musica = MediaPlayer.create(this, R.raw.torete);

        empezar.setOnClickListener(this);
        pausar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.empezar) {
            if (!musica.isPlaying()) {
                musica.start();
                titulo.setVisibility(View.VISIBLE);
                cancion.setVisibility(View.VISIBLE);
            }
        }
        if (view.getId() == R.id.pausar) {
            if (musica.isPlaying()) {
                musica.pause();
            }
        }
    }
}