package com.google.vicalb.models;

import com.google.firebase.firestore.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Reserva {

    private String uid;
    private Plato plato;

    public Reserva() {

    }

    public Reserva(String uid, Plato plato) {
        this.uid = uid;
        this.plato = plato;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Plato getPlato() {
        return plato;
    }

    public void setPlato(Plato plato) {
        this.plato = plato;
    }
}
