package VW.estudiantes;

import VW.estudiantes.modelo.Estudiante;
import VW.estudiantes.servicio.EstudianteServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class EstudiantesApplication implements CommandLineRunner {

	@Autowired
	private EstudianteServicio estudianteServicio;

	private static final Logger logger =
			LoggerFactory.getLogger(EstudiantesApplication.class);

	String nl = System.lineSeparator();

	public static void main(String[] args) {
		logger.info("Iniciando la aplicación...");
		SpringApplication.run(EstudiantesApplication.class, args);
		logger.info("Aplicación inicializada!");
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info(nl + "Ejecutando método run de Spring"+nl);
		var salir = false;
		var consola = new Scanner(System.in);

		while(!salir){
			mostrarMenu();
			salir = ejecutarOpciones(consola);
			logger.info(nl);
		}// Fin de while
	}
	private void mostrarMenu(){
		logger.info(nl);
		logger.info("""
                *** Aplicación Estudiantes ***
                1. Agregar estudiante
                2. Listar estudiantes
                3. Buscar estudiante
                4. Modificar estudiante
                5. Eliminar estudiante
                6. Salir
                
                Elije una opción:
                """);
	}

	private boolean ejecutarOpciones(Scanner consola){
		var op = Integer.parseInt(consola.nextLine());
		var sal = false;
		switch (op){
			case 1 ->{// Agregar estudiante
				logger.info("Agregar estudiante: ");
				logger.info(nl+"Nombre: ");
				var nombre = consola.nextLine();
				logger.info("Apellido: ");
				var apellido = consola.nextLine();
				logger.info("Telefono: ");
				var telefono = consola.nextLine();
				logger.info("Email: ");
				var email = consola.nextLine();
				// Crear objeto estudiante
				var estudiante = new Estudiante();
				estudiante.setNombre(nombre);
				estudiante.setApellido(apellido);
				estudiante.setTelefono(telefono);
				estudiante.setEmail(email);
				estudianteServicio.guardarEstudiante(estudiante);
				logger.info("Estudiante agregado: "+estudiante+nl);
			}
			case 2 -> {// Listar estudiantes
				logger.info(nl + "Listado de estudiantes: "+ nl);
				List<Estudiante> estudiantes = estudianteServicio.listarEstudiante();
				// Cambia mucho la interacción con logger.info
				estudiantes.forEach((estudiante -> logger.info(estudiante.toString() + nl)));
			}
			case 3 -> {// Buscar estudiante
				logger.info(nl+"Introduce el Id del estudiante: "+nl);
				var idEstudiante = Integer.parseInt(consola.next());
				Estudiante estudiante=estudianteServicio.buscarestudianteporID(idEstudiante);
				if(estudiante != null)
					logger.info("Estudiante encontrado: " + estudiante +nl);
				else
					logger.info("Estudiante no encontrado: " + estudiante+nl);
			}
			case 4 -> {// Modificar estudiante
				logger.info("Modificar estudiante: " + nl);
				logger.info("ID Estudiante: "+nl);
				var idEstudiante = Integer.parseInt(consola.nextLine());
				// Buscamos estudiante para modifcar
				Estudiante estudiante = estudianteServicio.buscarestudianteporID(idEstudiante);
				if(estudiante != null) {
					logger.info("Nombre: ");
					var nombre = consola.nextLine();
					logger.info("Apellido: ");
					var apellido = consola.nextLine();
					logger.info("Telefono: ");
					var telefono = consola.nextLine();
					logger.info("Email: ");
					var email = consola.nextLine();
					estudiante.setNombre(nombre);
					estudiante.setApellido(apellido);
					estudiante.setTelefono(telefono);
					estudiante.setEmail(email);
					estudianteServicio.guardarEstudiante(estudiante);
					logger.info("Estudiante modificado: "+estudiante+nl);
				}
				else {
					logger.info(nl + "El estudiante no se ha encontrado: " + idEstudiante + nl);
				}
			}
			case 5 -> {// Eliminar estudiante
				logger.info("Eliminar estudiante"+nl);
				logger.info(nl+"Introduce el Id del estudiante: "+nl);
				var idEstudiante = Integer.parseInt(consola.nextLine());
				var estudiante=estudianteServicio.buscarestudianteporID(idEstudiante);
				if (estudiante != null){
					estudianteServicio.eliminiarEstudiante(estudiante);
					logger.info(nl+"El estudiante se ha eliminado: "+estudiante+nl);
				}
				else {
					logger.info("No se pudo eliminar estudiante: "+estudiante+nl);
				}
			}
			case 6 -> {// Salir
				logger.info("Hasta pronto..."+nl);
				sal = true;
			}default -> logger.info("Seleccione una opción correcta"+nl);
		}return sal;
	}
}
