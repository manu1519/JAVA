package VW.Tareas.servicio;

import VW.Tareas.modelo.tarea;
import VW.Tareas.repo.Tarearepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TareaSer implements ITareaSer{
    @Autowired
    private Tarearepo tarearepo;
    @Override
    public List<tarea> listarTareas() {
        return tarearepo.findAll();
    }

    @Override
    public tarea buscarId(Integer idTarea) {
        tarea tarea = tarearepo.findById(idTarea).orElse(null);
        return tarea;
    }

    @Override
    public void guardar(tarea tarea) {
        tarearepo.save(tarea);
    }

    @Override
    public void eliminar(tarea tarea) {
        tarearepo.delete(tarea);
    }
}
