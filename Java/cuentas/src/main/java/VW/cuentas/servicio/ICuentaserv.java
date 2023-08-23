package VW.cuentas.servicio;

import VW.cuentas.modelo.Cuenta;

import java.util.List;

public interface ICuentaserv{
    public List<Cuenta> listCuenta();
    public Cuenta buscarcuentaid(Integer idCuenta);
    public void guardar(Cuenta cuenta);
    public void eliminar(Cuenta cuenta);
}
