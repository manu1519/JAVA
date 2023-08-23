package VW.empleados.servicio;

import VW.empleados.modelo.Empleado;
import VW.empleados.repositorio.EmpleadoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmpleadoServ implements IEmpleadoServ{

    @Autowired
    private EmpleadoRepo empleadoRepo;

    @Override
    public List<Empleado> listaremp() {
        return empleadoRepo.findAll();
    }

    @Override
    public Empleado buscarEmpleadoID(Integer idEmpleado) {
        Empleado empleado = empleadoRepo.findById(idEmpleado).orElse(null);
        return empleado;
    }

    @Override
    public void guardarEmp(Empleado empleado) {
        empleadoRepo.save(empleado);
    }

    @Override
    public void eliminarEmp(Empleado empleado) {
        empleadoRepo.delete(empleado);
    }
}
