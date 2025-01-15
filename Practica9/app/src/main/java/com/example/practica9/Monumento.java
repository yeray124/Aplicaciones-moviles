package com.example.practica9;

public class Monumento {
    public String id;
    public String nombre;
    public String descripcion;
    public String fecha;
    public String latitud;
    public String longitud;
    public String ciudad;
    public String visitable;
    public String precio;
    public String moneda;
    public String imagen;
    public String video;

    public Monumento() {
        this.imagen = "";
        this.moneda = "";
        this.precio = "";
        this.visitable = "";
        this.ciudad = "";
        this.longitud = "";
        this.latitud = "";
        this.fecha = "";
        this.descripcion = "";
        this.nombre = "";
        this.id = "";
        this.video = "";
    }
    public Monumento(String id, String nombre, String descripcion, String fecha, String latitud, String longitud, String ciudad, String visitable, String precio, String moneda, String imagen,String video) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.latitud = latitud;
        this.longitud = longitud;
        this.ciudad = ciudad;
        this.visitable = visitable;
        this.precio = precio;
        this.moneda = moneda;
        this.imagen = imagen;
        this.video = video;
    }
}
