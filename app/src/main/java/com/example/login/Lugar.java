package com.example.login;

import java.io.Serializable;

public class Lugar implements Serializable {
    String id;
    String nombre,descri,imgurl,departamento, direccion;
    String califica;
    String zlatitud,zlongitud;
    float distance;

    public Lugar(String id, String nombre, String descri, String imgurl, String departamento, String direccion, String califica, String zlatitud, String zlongitud) {
        this.id = id;
        this.nombre = nombre;
        this.descri = descri;
        this.imgurl = imgurl;
        this.departamento = departamento;
        this.direccion = direccion;
        this.califica = califica;
        this.zlatitud = zlatitud;
        this.zlongitud = zlongitud;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescri() {
        return descri;
    }

    public void setDescri(String descri) {
        this.descri = descri;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCalifica() {
        return califica;
    }

    public void setCalifica(String califica) {
        this.califica = califica;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getZlatitud() {
        return zlatitud;
    }

    public void setZlatitud(String zlatitud) {
        this.zlatitud = zlatitud;
    }

    public String getZlongitud() {
        return zlongitud;
    }

    public void setZlongitud(String zlongitud) {
        this.zlongitud = zlongitud;
    }
    public double getDistancia() {
        return distance;
    }

    public void setDistancia(float distance) {
        this.distance = distance;
    }
}
