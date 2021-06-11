package com.google.vicalb.models;

import com.google.firebase.firestore.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Plato {

    private String nombre;
    private String image;
    private String descripcion;
    private String precio;
    private int idRestaurante;

    public Plato() {
    }

    public Plato(String nombre, String image, String descripcion, String precio, int idRestaurante) {
        this.nombre = nombre;
        this.image = image;
        this.descripcion = descripcion;
        this.precio = precio;
        this.idRestaurante = idRestaurante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public int getIdRestaurante() {
        return idRestaurante;
    }

    public void setIdRestaurante(int idRestaurante) {
        this.idRestaurante = idRestaurante;
    }
}
