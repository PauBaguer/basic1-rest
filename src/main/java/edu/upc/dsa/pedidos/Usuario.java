package edu.upc.dsa.pedidos;

import java.util.LinkedList;
import java.util.List;

public class Usuario {
    private String nombre;
    private List<Pedido> listaPedidos;

    public Usuario() {
    }

    public Usuario(String nombre) {
        this();
        this.nombre = nombre;
        this.listaPedidos = new LinkedList<>();
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setListaPedidos(List<Pedido> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Pedido> getListaPedidos() {
        return listaPedidos;
    }
}
