package VW.Inventarios.serv;

import VW.Inventarios.modelo.Producto;

import java.util.List;

public interface IProductoserv {
    public List<Producto> listaPro();
    public Producto buscar(Integer idProducto);
    public Producto guardar(Producto producto);
    public void eliminar(Integer idProducto);
}
