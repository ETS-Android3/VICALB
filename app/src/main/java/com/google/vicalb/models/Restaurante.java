package com.google.vicalb.models;

import com.google.firebase.firestore.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties
public class Restaurante implements Serializable {

    int id;
    int image;
    String restaurante, tarjeta, efectivo;
    int cantidadReserva;
    int totalReserva;

    String descripcion;
    String horario;
    String localización;

    double lat,lon;

    public Restaurante() {
    }

    public Restaurante(int image, String restaurante, String tarjeta, String efectivo, int cantidadReserva, int totalReserva) {
        this.image = image;
        this.restaurante = restaurante;
        this.tarjeta = tarjeta;
        this.efectivo = efectivo;
        this.cantidadReserva = cantidadReserva;
        this.totalReserva = totalReserva;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public String getRestaurante() {
        return restaurante;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public String getEfectivo() {
        return efectivo;
    }

    public int getCantidadReserva() {
        return cantidadReserva;
    }

    public void setCantidadReserva(int cantidadReserva) {
        this.cantidadReserva = cantidadReserva;
    }

    public int getTotalReserva() {
        return totalReserva;
    }

    public void setTotalReserva(int totalReserva) {
        this.totalReserva = totalReserva;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getLocalización() {
        return localización;
    }

    public void setLocalización(String localización) {
        this.localización = localización;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}


