package VW.Inventarios.repo;

import VW.Inventarios.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Productorepo extends JpaRepository<Producto, Integer> {

}
