package edu.upc.dsa;

import org.javatuples.Pair;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import edu.upc.dsa.pedidos.Pedido;
import edu.upc.dsa.pedidos.Product;
import edu.upc.dsa.pedidos.ProductManagerImpl;
import edu.upc.dsa.pedidos.Usuario;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class test {

    Product cola;
    Product pepsi;
    Product zumo;
    ProductManagerImpl productManager;

    @Before
    public void setUp(){
        cola = new Product("cola",2,5);
        pepsi = new Product("pepsi",2.5,3);
        zumo = new Product("zumo",1.5,8);
        productManager = ProductManagerImpl.getInstance();
        List<Product> l = new LinkedList<>();
        l.add(cola);
        l.add(pepsi);
        l.add(zumo);
        productManager.addtoListaProductos(l);

        productManager.addUser(new Usuario("Pau"));
        productManager.addUser(new Usuario("Caty"));
        productManager.addUser(new Usuario("Julia"));

        HashMap<Product, Integer> prodList = new HashMap<Product, Integer>(){{
            put(cola, 4);
            put(pepsi, 3);
            put(zumo, 8);
        }};

        productManager.realizarPedido(new Pedido(productManager.getUsuarioHashMap().get("Pau"), prodList));
    }

    @After
    public void tearDown(){
        productManager.cleanCache();
    }

    @Test
    public void testSortByPrice(){


        Product[] arr = {zumo,cola,pepsi};
        Assert.assertArrayEquals(arr,productManager.sortByPrice().toArray());
    }

    @Test
    public void testSortBySells(){
        Product[] arr = {zumo,cola,pepsi};
        Assert.assertArrayEquals(arr,productManager.sortBySells().toArray());
    }

    @Test
    public void testRealizarPedido(){
        testServirPedido();
        HashMap<Product, Integer> prodList = new HashMap<Product, Integer>(){{
            put(cola, 2);
            put(pepsi, 1);
            put(zumo, 10);
        }};

        Pedido pedido = new Pedido(productManager.getUsuarioHashMap().get("Caty"), prodList);
        productManager.realizarPedido(pedido);
        Pedido[] arr = {pedido};
        Assert.assertArrayEquals(arr, productManager.getColaPedidos().toArray());

        Assert.assertArrayEquals(arr, productManager.pedidosDeUsuario(productManager.getUsuarioHashMap().get("Caty")).toArray());
        Assert.assertEquals( 6,pedido.getProducts().get(0).getCantidadVendidos());

    }

    @Test
    public void testServirPedido(){
        productManager.servirPedido();
        Pedido[] arr = {};
        Assert.assertArrayEquals(arr,productManager.getColaPedidos().toArray());
    }
}
