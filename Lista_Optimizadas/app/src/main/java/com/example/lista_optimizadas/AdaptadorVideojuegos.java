package com.example.lista_optimizadas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdaptadorVideojuegos extends RecyclerView.Adapter<AdaptadorVideojuegos.VideojuegosViewHolder> {

    private Videojuegos[] listaVideojuegos;
    private Context context; // aqui inserto mi imagen :)

    public AdaptadorVideojuegos(Videojuegos[] listaVideojuegos, Context context) {
        this.listaVideojuegos = listaVideojuegos;
        this.context = context;
    }


    public static class VideojuegosViewHolder extends RecyclerView.ViewHolder {
        public TextView tituloView;
        public ImageView portadaView;


        public VideojuegosViewHolder(View view) {
            super(view);


            tituloView = view.findViewById(R.id.textoView);
            portadaView = view.findViewById(R.id.imageView);
        }

        public void BindVideojuegos(Videojuegos videojuego, Context context) {
            tituloView.setText(videojuego.getTitulo());
            portadaView.setImageResource(context.getResources().getIdentifier(
                    videojuego.getImagen(), "drawable", context.getPackageName()));
        }
    }

    @NonNull
    @Override
    public VideojuegosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_juegos, parent, false);

        return new VideojuegosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideojuegosViewHolder holder, int position) {
        holder.BindVideojuegos(this.listaVideojuegos[position], context);
    }

    @Override
    public int getItemCount() {
        return this.listaVideojuegos.length;
    }
    }