package com.example.lista_optimizadas;

public class Videojuegos {
    private String Titulo;
    private String Imagen;
    private String Fecha_De_Salida;

    public Videojuegos(String Titulo, String Imagen, String Fecha_De_Salida){
        this.Titulo = Titulo;
        this.Imagen = Imagen;
        this.Fecha_De_Salida = Fecha_De_Salida;
    }
    public String getTitulo() {
        return Titulo;
    }

    public String getImagen() {
        return Imagen;
    }

    public String Fecha_De_Salida() {
        return Fecha_De_Salida;
    }
}
