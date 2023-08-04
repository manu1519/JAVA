package VW.estudiantes.servicio;

import VW.estudiantes.modelo.Estudiante;

import java.util.List;

public interface IEstudianteServicio {
    public List<Estudiante> listarEstudiante();

    public Estudiante buscarestudianteporID(Integer idEstudiante);

    public void guardarEstudiante(Estudiante estudiante);

    public void eliminiarEstudiante(Estudiante estudiante);

}
