package com.example.lista_optimizadas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdaptadorVideojuegos extends RecyclerView.Adapter<AdaptadorVideojuegos.VideojuegosViewHolder> {

    private ArrayList<Videojuegos> listaVideojuegos;
    private Context context; // aqui inserto mi imagen :)

    public AdaptadorVideojuegos(ArrayList<Videojuegos> listaVideojuegos, Context context) {
        this.listaVideojuegos = listaVideojuegos;
        this.context = context;
    }


    public static class VideojuegosViewHolder extends RecyclerView.ViewHolder {
        public TextView tituloView;
        public ImageView portadaView;
        public TextView textViewGenero;


        public VideojuegosViewHolder(View view) {
            super(view);

            textViewGenero =  view.findViewById(R.id.textViewGenero);
            tituloView = view.findViewById(R.id.textoView);
            portadaView = view.findViewById(R.id.imageView);
        }

        public void BindVideojuegos(Videojuegos videojuego, Context context) {
            tituloView.setText(videojuego.getTitulo());
            textViewGenero.setText(videojuego.getGenero());
            Picasso.get().load(videojuego.getImagen()).into(portadaView);
           // portadaView.setImageResource(context.getResources().getIdentifier(
            //        videojuego.getImagen(), "drawable", context.getPackageName()));
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
        holder.BindVideojuegos(this.listaVideojuegos.get(position), context);
    }

    @Override
    public int getItemCount() {
        return this.listaVideojuegos.size();
    }
    }