package edu.upc.dsa.pedidos;

import edu.upc.dsa.util.RandomUtils;
import org.javatuples.Pair;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Pedido {
    private Usuario usuario;
    private List <Product> products;
    private HashMap<Product, Integer> quantity;
    private String id;

    public Pedido() {
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Pedido(Usuario usuario, HashMap<Product, Integer> quantity) {
        this();
        this.usuario = usuario;
        this.id = RandomUtils.getId();
        this.quantity = quantity;
        this.products = new LinkedList<>(quantity.keySet());
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public List<Product> getProducts() {
        return products;
    }

    public HashMap<Product, Integer> getQuantity() {
        return quantity;
    }

    public void setQuantity(HashMap<Product, Integer> quantity) {
        this.quantity = quantity;
    }
}
