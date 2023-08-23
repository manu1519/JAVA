package VW.Inventarios.serv;

import VW.Inventarios.modelo.Producto;
import VW.Inventarios.repo.Productorepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Productoserv implements IProductoserv{

    @Autowired
    private Productorepo productorepo;

    @Override
    public List<Producto> listaPro() {
        return this.productorepo.findAll();

    }

    @Override
    public Producto buscar(Integer idProducto) {
        Producto producto = this.productorepo.findById(idProducto).orElse(null);
        return producto;
    }

    @Override
    public Producto guardar(Producto producto) {
        return this.productorepo.save(producto);
    }

    @Override
    public void eliminar(Integer idProducto) {
        this.productorepo.deleteById(idProducto);
    }
}
