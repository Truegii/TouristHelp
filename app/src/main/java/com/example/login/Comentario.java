package com.example.login;

import java.io.Serializable;

public class Comentario implements Serializable {
    String usuario,zona,coment,califica;

    public Comentario(String usuario, String zona, String coment, String califica) {
        this.usuario = usuario;
        this.zona = zona;
        this.coment = coment;
        this.califica = califica;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getComent() {
        return coment;
    }

    public void setComent(String coment) {
        this.coment = coment;
    }

    public String getCalifica() {
        return califica;
    }

    public void setCalifica(String califica) {
        this.califica = califica;
    }
}
