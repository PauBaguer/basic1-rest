package edu.upc.dsa.pedidos;

import java.util.LinkedList;
import java.util.List;

public class Usuario {
    private String nombre;


    public Usuario() {
    }

    public Usuario(String nombre) {
        this();
        this.nombre = nombre;

    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getNombre() {
        return nombre;
    }


}
