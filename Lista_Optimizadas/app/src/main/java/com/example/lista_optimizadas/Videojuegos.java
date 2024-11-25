package com.example.lista_optimizadas;

public class Videojuegos {
    private String Titulo;
    private String Imagen;
    private String Genero;

    public Videojuegos(String Titulo, String Genero, String Imagen){
        this.Titulo = Titulo;
        this.Imagen = Imagen;
        this.Genero = Genero;
    }
    public String getTitulo() {
        return Titulo;
    }

    public String getGenero() {
        return Genero;
    }

    public String getImagen() {
        return Imagen;
    }


}
