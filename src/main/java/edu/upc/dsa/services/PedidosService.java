package edu.upc.dsa.services;

import edu.upc.dsa.models.Track;
import edu.upc.dsa.pedidos.*;
import io.swagger.annotations.Api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.javatuples.Pair;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

@Api(value = "/pedidos")
@Path("/pedidos")
public class PedidosService {

    private ProductManagerImpl productManager;

    public PedidosService() {
        this.productManager = ProductManagerImpl.getInstance();
        Product cola = new Product("cola",2,5);
        Product pepsi = new Product("pepsi",2.5,3);
        Product zumo = new Product("zumo",1.5,8);

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

    @GET
    @ApiOperation(value = "get all Pedidos", notes = "pedidos")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = PedidoTO.class, responseContainer="List"),
    })
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPedidos(){
        List<PedidoTO>pedidos = new LinkedList<>();
        ProductManagerImpl.getInstance().getColaPedidos().forEach((pedido -> {
            pedidos.add(new PedidoTO(pedido.getUsuario(), pedido.getProducts()));
        }));

        GenericEntity<List<PedidoTO>> entity = new GenericEntity<List<PedidoTO>>(pedidos){};
        return Response.status(201).entity(entity).build();

    }

    @GET
    @ApiOperation(value = "get Productos ordenados por precio", notes = "productos")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Product.class, responseContainer="List"),
    })
    @Path("/Product_price")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductsByPrice(){
        List<Product> p = new LinkedList<>(ProductManagerImpl.getInstance().sortByPrice());

        GenericEntity<List<Product>> entity = new GenericEntity<List<Product>>(p){};
        return Response.status(201).entity(entity).build();

    }

    @GET
    @ApiOperation(value = "get Productos ordenados por ventas descendientes", notes = "productos")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Product.class, responseContainer="List"),
    })
    @Path("/Product_sells")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductsBySells(){
        List<Product> p = new LinkedList<>(ProductManagerImpl.getInstance().sortByPrice());

        GenericEntity<List<Product>> entity = new GenericEntity<List<Product>>(p){};
        return Response.status(201).entity(entity).build();

    }

    @GET
    @ApiOperation(value = "get all Pedidos", notes = "pedidos")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = PedidoTO.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/pedidosUsuario/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPedidosUsuario(@PathParam("nombre") String nombre){
        Usuario u = ProductManagerImpl.getInstance().getUsuarioHashMap().get(nombre);
        if(u==null) return Response.status(404).build();
        List<PedidoTO>pedidos = new LinkedList<>();
        ProductManagerImpl.getInstance().pedidosDeUsuario(u).forEach(pedido -> pedidos.add(new PedidoTO(pedido.getUsuario(), pedido.getProducts())));

        GenericEntity<List<PedidoTO>> entity = new GenericEntity<List<PedidoTO>>(pedidos){};
        return Response.status(201).entity(entity).build();

    }


    @POST
    @ApiOperation(value = "crear pedido", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=PedidoBuilder.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newTrack(PedidoBuilder pedido) {

        if (pedido.getUsuarioName()==null || pedido.getProductNameList()==null)  return Response.status(500).entity(pedido).build();
        ProductManagerImpl.getInstance().realizarPedido(pedido.buildPedido());
        return Response.status(201).entity(pedido).build();
    }



    @DELETE
    @ApiOperation(value = "servir Producto", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
                })
    @Path("/")
    public Response deleteTrack() {
        ProductManagerImpl.getInstance().servirPedido();
        return Response.status(201).build();
    }

}
