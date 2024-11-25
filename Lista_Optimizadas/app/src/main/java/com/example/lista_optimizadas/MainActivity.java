package com.example.lista_optimizadas;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdaptadorVideojuegos adaptador;
    private ArrayList<Videojuegos> listaVideojuegos;

    private EditText NombreID;
    private EditText TextUrl;
    private Button buttonInsertar;
    private EditText textGenero;

    private DB_juegos dbVideojuegos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.recView), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recView);
        NombreID = findViewById(R.id.NombreID);
        TextUrl = findViewById(R.id.TextUrl);
        buttonInsertar = findViewById(R.id.buttonInsertar);
        textGenero = findViewById(R.id.textGenero);

      //  Videojuegos[] videojuegos = new Videojuegos[2];
        //videojuegos[0] = new Videojuegos("Elden ring", "elden", "2022-02-25");
       // videojuegos[1] = new Videojuegos("Mario Bros", "mario", "1985-09-13");

        dbVideojuegos = new DB_juegos(this);

        listaVideojuegos = dbVideojuegos.obetenerTodosLosVideojuegos();

        adaptador = new AdaptadorVideojuegos(listaVideojuegos, this);

        // adaptador = new AdaptadorVideojuegos(listaVideojuegos, this);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adaptador);



        buttonInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = NombreID.getText().toString();
                String url = TextUrl.getText().toString();
                String genero = textGenero.getText().toString();

                if (!nombre.isEmpty() && !url.isEmpty() && !genero.isEmpty()) {
                    insertarNuevoVideojuego(nombre, url, genero);
                } else {

                    System.out.println("Error: campos vacíos :))))");
                }
            }
        });
    }

    private void insertarNuevoVideojuego(String nombre, String url, String genero) {

        dbVideojuegos.insertarVideojuego(nombre, url, genero);


        listaVideojuegos = dbVideojuegos.obetenerTodosLosVideojuegos();

        adaptador = new AdaptadorVideojuegos(listaVideojuegos, this);
        recyclerView.setAdapter(adaptador);
    }
}