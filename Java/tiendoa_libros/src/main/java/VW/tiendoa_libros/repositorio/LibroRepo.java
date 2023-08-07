package VW.tiendoa_libros.repositorio;

import VW.tiendoa_libros.modelo.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepo extends JpaRepository<Libro, Integer> {

}
