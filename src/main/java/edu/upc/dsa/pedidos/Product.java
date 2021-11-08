package edu.upc.dsa.pedidos;

public class Product implements Comparable<Product>{
    private String nombre;
    private double precio;
    private int cantidadVendidos;

    public Product() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setCantidadVendidos(int cantidadVendidos) {
        this.cantidadVendidos = cantidadVendidos;
    }

    public Product(String nombre, double precio, int cantidadDisponible) {
        this();
        this.nombre = nombre;
        this.precio = precio;
    }

    public int getCantidadVendidos() {
        return cantidadVendidos;
    }



    public void comprar(int cantidad){
        this.cantidadVendidos += cantidad;
    }

    @Override
    public int compareTo(Product o) {
        return Double.compare(this.precio, o.precio);
    }


}
