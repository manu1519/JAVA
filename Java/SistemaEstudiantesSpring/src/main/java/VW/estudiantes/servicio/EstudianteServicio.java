package VW.estudiantes.servicio;

import VW.estudiantes.modelo.Estudiante;
import VW.estudiantes.repositorio.EstudianteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.util.List;

@Service
public class EstudianteServicio implements IEstudianteServicio{

    @Autowired
    private EstudianteRepositorio estudianteRepositorio;

    @Override
    public List<Estudiante> listarEstudiante() {
        List<Estudiante> estudiantes= estudianteRepositorio.findAll();
        return estudiantes;
    }

    @Override
    public Estudiante buscarestudianteporID(Integer idEstudiante) {
        Estudiante estudiante = estudianteRepositorio.findById(idEstudiante).orElse(null);
        return estudiante;
    }

    @Override
    public void guardarEstudiante(Estudiante estudiante) {
        estudianteRepositorio.save(estudiante);
    }

    @Override
    public void eliminiarEstudiante(Estudiante estudiante) {
        estudianteRepositorio.delete(estudiante);
    }
}
