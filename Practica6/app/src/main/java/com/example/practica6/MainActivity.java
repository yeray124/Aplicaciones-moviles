package com.example.practica6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button Continuar;
    private EditText CajaContrasena;
    private EditText CajaTextoCorreo;
    private TextView MostrarInfo;
    private Switch Recordar;
    private TextView TextoRecordar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.diseno);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        CajaContrasena = findViewById(R.id.CajaContrasena);
        CajaTextoCorreo = findViewById(R.id.CajaTextoCorreo);
        MostrarInfo = findViewById(R.id.MostrarInfo);
        MostrarInfo = findViewById(R.id.MostrarInfo);
        Recordar = findViewById(R.id.Recordar);

        Continuar = findViewById(R.id.Continuar);

        MostrarInfo.setVisibility(View.INVISIBLE);
        Continuar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String usuario = CajaTextoCorreo.getText().toString();
        String contrasena = CajaContrasena.getText().toString();
        String usuarioCorrecto = "correo@correo.com";
        String contrasenaCorrecta = "123";
        String usuarioCorrecto2 = "prueba@correo.com";
        String contrasenaCorrecta2 = "1234";

        if (view.getId() == R.id.Continuar) {
            // Verifica ambas combinaciones de usuario y contraseña
            if ((usuario.equals(usuarioCorrecto) && contrasena.equals(contrasenaCorrecta)) ||
                    (usuario.equals(usuarioCorrecto2) && contrasena.equals(contrasenaCorrecta2))) {
                MostrarInfo.setVisibility(View.VISIBLE);
                MostrarInfo.setTextColor(getColor(R.color.verde));
                MostrarInfo.setText("Inicio de sesion correcto :)");
                Intent intentBienvenida = new Intent(getApplicationContext(), MainActivityYeray.class);
                intentBienvenida.putExtra("email", CajaTextoCorreo.getText().toString());
                startActivity(intentBienvenida);
                if (Recordar.isChecked()) {
                    MostrarInfo.setText(MostrarInfo.getText() + " / Almacenado para siguientes accesos");
                }
            } else {
                MostrarInfo.setVisibility(View.VISIBLE);
                MostrarInfo.setTextColor(getColor(R.color.rojo));
                MostrarInfo.setText("Inicio de sesion erroneo :(");
            }
        }
    }
}