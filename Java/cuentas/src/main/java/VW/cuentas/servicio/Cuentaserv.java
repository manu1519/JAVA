package VW.cuentas.servicio;

import VW.cuentas.modelo.Cuenta;
import VW.cuentas.repo.Cuentarepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Cuentaserv implements ICuentaserv{

    @Autowired
    private Cuentarepo cuentarepo;

    @Override
    public List<Cuenta> listCuenta() {

        return cuentarepo.findAll();
    }

    @Override
    public Cuenta buscarcuentaid(Integer idCuenta) {
        Cuenta cuenta=cuentarepo.findById(idCuenta).orElse(null);
        return cuenta;
    }

    @Override
    public void guardar(Cuenta cuenta) {
        cuentarepo.save(cuenta);
    }

    @Override
    public void eliminar(Cuenta cuenta) {
        cuentarepo.delete(cuenta);
    }
}
