package edu.upc.dsa.pedidos;

import org.apache.log4j.Logger;

import javax.xml.bind.annotation.XmlType;
import java.util.*;

public class ProductManagerImpl implements ProductManager{

    private static ProductManagerImpl instance;
    private Queue<Pedido> colaPedidos;
    private List<Product> listaProductos;
    private HashMap<String, Usuario> usuarioHashMap;
    private final static Logger logger = Logger.getLogger(ProductManagerImpl.class);

    private ProductManagerImpl() {
        colaPedidos = new LinkedList<>();
        listaProductos = new LinkedList<>();
        usuarioHashMap = new HashMap<>();
    }

    public static void setInstance(ProductManagerImpl instance) {
        ProductManagerImpl.instance = instance;
    }

    public void setColaPedidos(Queue<Pedido> colaPedidos) {
        this.colaPedidos = colaPedidos;
    }

    public List<Product> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Product> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public void setUsuarioHashMap(HashMap<String, Usuario> usuarioHashMap) {
        this.usuarioHashMap = usuarioHashMap;
    }

    //singleton
    public static ProductManagerImpl getInstance(){
        logger.info("Access singleton instance");
        if(instance==null){ instance = new ProductManagerImpl(); logger.info("New singleton Instance");}
        return instance;
    }

    public void addtoListaProductos(List<Product> l){
        logger.info("Add to lista productos");
        logger.info(l);
        listaProductos.addAll(l);
        logger.info(listaProductos);
    }

    public void addUser(Usuario u){
        logger.info("Add user");
        logger.info(u);
        usuarioHashMap.put(u.getNombre(),u);
        logger.info(usuarioHashMap);
    }

    public HashMap<String, Usuario> getUsuarioHashMap() {
        return usuarioHashMap;
    }

    public Queue<Pedido> getColaPedidos() {
        return colaPedidos;
    }

    @Override
    public List<Product> sortByPrice() {
        logger.info(listaProductos);
        List<Product> l = new LinkedList<>(listaProductos);
        l.sort(Product::compareTo);
        logger.info(l);
        return l;
    }

    @Override
    public List<Product> sortBySells() {
        logger.info(listaProductos);
        List<Product> l = new LinkedList<>(listaProductos);
        l.sort((Product o1, Product o2) -> Integer.compare(o2.getCantidadVendidos(), o1.getCantidadVendidos()));
        logger.info(l);
        return l;
    }


    @Override
    public void realizarPedido(Pedido p) {
        logger.info(p);
        p.getQuantity().forEach((k,v) -> {
            k.comprar(v);
        });

        colaPedidos.add(p);
        logger.info(usuarioHashMap.get(p.getUsuario().getNombre()));
        logger.info(colaPedidos);
    }

    @Override
    public void servirPedido() {
        logger.info(colaPedidos);
        colaPedidos.poll();
        logger.info(colaPedidos);
    }

    public Pedido getPedidoById(String id){
        List<Pedido> l = new ArrayList<>(colaPedidos);
        Pedido p = l.stream().filter(pedido -> pedido.getId().equals(id)).findFirst().orElse(null);
        return p;
    }

    @Override
    public List<Pedido> pedidosDeUsuario(Usuario u) {
        List<Pedido> l = new LinkedList<>();
        colaPedidos.forEach(pedido -> {
            if(pedido.getUsuario().equals(u)){
                l.add(pedido);
            }
        });

        return l;
    }

    public void cleanCache() {
        this.colaPedidos.clear();
        this.listaProductos.clear();
        this.usuarioHashMap.clear();
    }
}
