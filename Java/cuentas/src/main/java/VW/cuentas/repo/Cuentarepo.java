package VW.cuentas.repo;

import VW.cuentas.modelo.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Cuentarepo extends JpaRepository<Cuenta, Integer> {

}
