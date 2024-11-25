package com.example.lista_optimizadas;

public class Videojuegos {
    private String Titulo;
    private String Imagen;
    private String Genero;

    public Videojuegos(String Titulo, String Imagen, String Genero){
        this.Titulo = Titulo;
        this.Imagen = Imagen;
        this.Genero = Genero;
    }
    public String getTitulo() {
        return Titulo;
    }

    public String getImagen() {
        return Imagen;
    }

    public String getGenero() {
        return Genero;
    }
}
