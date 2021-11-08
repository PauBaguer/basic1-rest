package edu.upc.dsa.pedidos;

import java.util.List;

public class PedidoTO {
    private Usuario user;
    private List<Product> productList;

    public PedidoTO() {
    }

    public PedidoTO(Usuario user, List<Product> productList) {
        this();
        this.user = user;
        this.productList = productList;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
