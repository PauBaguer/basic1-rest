package edu.upc.dsa.pedidos;

import java.util.HashMap;
import java.util.List;

public class PedidoBuilder {
    String usuarioName;
    List<String> productNameList;

    public PedidoBuilder() {
    }

    public PedidoBuilder(String usuarioName, List<String> productNameList) {
        this();
        this.usuarioName = usuarioName;
        this.productNameList = productNameList;
    }

    public Pedido buildPedido(){
        HashMap<Product, Integer> q = new HashMap<>();
        this.productNameList.forEach(productName ->{
            Product p=null;
            for(Product prod : ProductManagerImpl.getInstance().getListaProductos()){
                if(prod.getNombre().equals(productName)){
                    p=prod;
                    break;
                }
            }
            if(q.keySet().stream().noneMatch(product -> product.getNombre().equals(productName))){
                q.put(p,1);
            }else {
                q.put(p,q.get(p) +1 );
            }
        });

        Pedido p = new Pedido(ProductManagerImpl.getInstance().getUsuarioHashMap().get(usuarioName), q);
        return p;
    }

    public String getUsuarioName() {
        return usuarioName;
    }

    public void setUsuarioName(String usuarioName) {
        this.usuarioName = usuarioName;
    }

    public List<String> getProductNameList() {
        return productNameList;
    }

    public void setProductNameList(List<String> productNameList) {
        this.productNameList = productNameList;
    }
}
