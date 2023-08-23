package VW.contactos.repo;

import VW.contactos.modelo.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactoRepo extends JpaRepository<Contacto, Integer> {

}
