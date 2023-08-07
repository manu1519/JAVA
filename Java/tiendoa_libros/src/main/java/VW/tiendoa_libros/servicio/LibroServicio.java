package VW.tiendoa_libros.servicio;

import VW.tiendoa_libros.modelo.Libro;
import VW.tiendoa_libros.repositorio.LibroRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroServicio implements ILibro{

    @Autowired
    private LibroRepo libroRepo;

    @Override
    public List<Libro> ListarLibros() {
        return libroRepo.findAll();
    }

    @Override
    public Libro buscarlibroId(Integer idLibro) {
        Libro libro = libroRepo.findById(idLibro).orElse(null);
        return libro;
    }

    @Override
    public void guardarLibro(Libro libro) {
        libroRepo.save(libro);
    }

    @Override
    public void eliminar(Libro libro) {
        libroRepo.delete(libro);
    }
}
