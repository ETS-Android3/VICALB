package com.google.vicalb.models;

public class Valoracion {

    private String nombre;
    private String restaurante;
    private String valoracion;

    public Valoracion() {
    }

    public Valoracion(String nombre, String restaurante, String valoracion) {
        this.nombre = nombre;
        this.restaurante = restaurante;
        this.valoracion = valoracion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(String restaurante) {
        this.restaurante = restaurante;
    }

    public String getValoracion() {
        return valoracion;
    }

    public void setValoracion(String valoracion) {
        this.valoracion = valoracion;
    }
}
