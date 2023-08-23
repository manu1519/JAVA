package VW.Inventarios.Controlador;

import VW.Inventarios.excepcion.Recursonoencontrado;
import VW.Inventarios.modelo.Producto;
import VW.Inventarios.serv.Productoserv;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
// Se crea un context Path:  http://localhost:8080/inventario-app
@RequestMapping("inventario-app")
// Se crea un permiso con dirección para Angular
@CrossOrigin(value = "http://localhost:4200")
public class PControl {

    private static final Logger logger = LoggerFactory.getLogger(PControl.class);

    @Autowired
    private Productoserv productoserv;

    // direccion final http://localhost:8080/inventario-app/productos
    @GetMapping("/productos")
    public List<Producto> obtPro(){
        List<Producto> productos = this.productoserv.listaPro();
        logger.info("Productos obtenidos: ");
        productos.forEach((producto) -> logger.info(producto.toString()));
        return productos;
    }

    @PostMapping("/productos")
    public Producto agregar(@RequestBody Producto producto){
        logger.info("Producto a agregar. " + producto);
        return this.productoserv.guardar(producto);
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<Producto> obtenerProducto(
            @PathVariable int id){
        Producto producto = this.productoserv.buscar(id);
        if(producto != null)
            return ResponseEntity.ok(producto);
        else
            throw new Recursonoencontrado("No se encontro el id:");
    }

    @PutMapping("/productos/{id}")
    public ResponseEntity<Producto> actualizarP(
        @PathVariable int id,
        @RequestBody Producto productoRe
    ){
        Producto producto = this.productoserv.buscar(id);
        if(producto == null)
            throw new Recursonoencontrado("No se encontro el id: " + id);
        producto.setDescrip(productoRe.getDescrip());
        producto.setPrecio(productoRe.getPrecio());
        producto.setExistencia(productoRe.getExistencia());
        this.productoserv.guardar(producto);
        return ResponseEntity.ok(producto);
    }

    @DeleteMapping("/productos/{id}")
    public ResponseEntity<Map<String, Boolean>>
    eliminar(@PathVariable int id){
        Producto producto = productoserv.buscar(id);
        if(producto == null)
            throw new Recursonoencontrado("No se encontro el id: "+ id);
        this.productoserv.eliminar(producto.getIdPro());
        Map<String, Boolean> resp = new HashMap<>();
        resp.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(resp);
    }

}
