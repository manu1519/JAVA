package VW.tiendoa_libros.servicio;

import VW.tiendoa_libros.modelo.Libro;

import java.util.List;

public interface ILibro {
    public List<Libro> ListarLibros();

    public Libro buscarlibroId(Integer idLibro);

    public void guardarLibro(Libro libro);

    public void eliminar(Libro libro);
}
