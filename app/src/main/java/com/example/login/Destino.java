package com.example.login;

import java.io.Serializable;

public class Destino implements Serializable {
    String id;
    String nombre,departamento, img;


    public Destino(String id, String nombre, String departamento, String img) {
        this.id = id;
        this.nombre = nombre;
        this.departamento = departamento;
        this.img = img;

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

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

}
