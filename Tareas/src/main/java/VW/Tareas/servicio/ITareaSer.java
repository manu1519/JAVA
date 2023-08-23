package VW.Tareas.servicio;

import VW.Tareas.modelo.tarea;

import java.util.List;

public interface ITareaSer {
    public List<tarea> listarTareas();

    public tarea buscarId(Integer idTarea);

    public void guardar(tarea tarea);

    public void eliminar(tarea tarea);
}
