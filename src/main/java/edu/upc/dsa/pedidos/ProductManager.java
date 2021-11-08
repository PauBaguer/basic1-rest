package edu.upc.dsa.pedidos;

import java.util.List;

public interface ProductManager {
    List<Product> sortByPrice();

    List<Product> sortBySells();

    void realizarPedido(Pedido p);

    void servirPedido();

    List<Pedido> pedidosDeUsuario(Usuario u);
}
