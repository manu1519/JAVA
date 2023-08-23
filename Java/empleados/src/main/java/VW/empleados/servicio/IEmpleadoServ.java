package VW.empleados.servicio;

import VW.empleados.modelo.Empleado;

import java.util.List;

public interface IEmpleadoServ {
    public List<Empleado> listaremp();
    public Empleado buscarEmpleadoID(Integer idEmpleado);
    public void guardarEmp(Empleado empleado);
    public void eliminarEmp(Empleado empleado);
}
