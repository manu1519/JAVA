package VW.presentacion;

import VW.DAO.EstudianteDAO;
import VW.dominio.Estudiante;

import java.util.Scanner;

public class SistemaEstudiantesApp {
    public static void main(String[] args) {

        var salir = false;
        var consola = new Scanner(System.in);

        var estudianteDao = new EstudianteDAO();

        while (!salir) {
            try {
                mostrarMenu();
                salir = ejecutarOp(consola, estudianteDao);

            } catch (Exception e) {
                System.out.println("Hubo un error con la operación" + e.getMessage());
            }
        }// Fin while
    }

    private static void mostrarMenu(){
        System.out.print("""
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

    private static boolean ejecutarOp(Scanner consola, EstudianteDAO estudianteDAO){
        var op = Integer.parseInt(consola.nextLine());
        var salir = false;

        switch (op){
            case 1 -> { // Agregar estudiante
                System.out.println("Agregar Estudiante; ");
                System.out.print("Nombre: ");
                var nombre = consola.nextLine();
                System.out.print("Apellido: ");
                var apellido = consola.nextLine();
                System.out.print("Telefono: ");
                var telefono = consola.nextLine();
                System.out.print("Email: ");
                var email = consola.nextLine();
                // Crear objeto
                var estudiante = new Estudiante(nombre,apellido,telefono,email);
                var agregado = estudianteDAO.agregarEstudiante(estudiante);
                if (agregado)
                    System.out.println("Estudiante agregado: " + estudiante);
                else
                    System.out.println("Estudiante no agregado: " + estudiante);

            }
            case 2 -> { // Listar estudiantes
                System.out.println("Listado de estudiantes: ");
                var estudiante = estudianteDAO.listarEstudiantes();
                estudiante.forEach(System.out::println);
            }
            case 3 -> { // Buscar estudiante
                System.out.print("Proporcione el ID del estudiante: ");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                var estudiante = new Estudiante(idEstudiante);
                var encontrado = estudianteDAO.buscarEstudiantePorId(estudiante);
                if(encontrado){
                    System.out.println("Estudiante encontrado: "+ estudiante);
                }
                else {
                    System.out.println("No se encontró al estudiante: " + estudiante);
                }
            }
            case 4 -> { // Modificar estudiante
                System.out.println("Modificar Estudiante: ");
                System.out.println("ID Estudiante: ");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                System.out.print("Nombre: ");
                var nombre = consola.nextLine();
                System.out.print("Apellido: ");
                var apellido = consola.nextLine();
                System.out.print("Telefono: ");
                var telefono = consola.nextLine();
                System.out.print("Email: ");
                var email = consola.nextLine();
                // Creamos el objeto estudiante
                var estudiante = new Estudiante(idEstudiante,nombre,apellido,telefono,email);
                var modificado = estudianteDAO.modificarEstudiantes(estudiante);
                if(modificado)
                    System.out.println("El estudiante se ha modificado: " + estudiante);
                else
                    System.out.println("El estudiante no se ha modificado: " + estudiante);
            }
            case 5 -> { // Eliminar estudiante
                System.out.println("Estudiante a eliminar: ");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                // Creamos el objeto estudiante
                var estudiante = new Estudiante(idEstudiante);
                var eliminado = estudianteDAO.eliminar(estudiante);
                if (eliminado)
                    System.out.println("El estudiante se ha eliminado: "+ estudiante);
                else
                    System.out.println("El estudiante no se ha eliminado: " +estudiante);
            }
            case 6 -> {
                System.out.println("Hasta pronto...");
                salir = true;
            }
            default -> System.out.println("Seleccione una opción correcta");
        } return salir;
    }

}