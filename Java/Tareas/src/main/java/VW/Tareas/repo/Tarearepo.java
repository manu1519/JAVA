package VW.Tareas.repo;

import VW.Tareas.modelo.tarea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Tarearepo extends JpaRepository<tarea, Integer> {
}
