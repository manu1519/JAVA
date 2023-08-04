import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListadoPersonasApp {
    public static void main(String[] args) {
        Scanner consola = new Scanner(System.in);
        // Definimos la lista fuera del ciclo While
        List<Persona> personas = new ArrayList<>();
        // Empezamos con el menu
        var sal = false;
        while (!sal) {
            mostrarMenu();
            try {
                sal = ejecutarOperacion(consola, personas);
            } catch (Exception e) {
                System.out.println("Ocurrio un error: " + e.getMessage());
            }

            System.out.println();
        }
    }

    private static void mostrarMenu(){
        // Mostrar opciones
        System.out.print("""
    **** Listado Personas ****
    1. Agregar
    2. Listar
    3. Salir
    """);

        System.out.print("Proporciona la opción: ");
    }

    private static boolean ejecutarOperacion(Scanner consola, List<Persona> personas){
        var opcion = Integer.parseInt(consola.nextLine());
        var salir = false;
        // Revisamos la opcion proporcionada
        switch (opcion){
            case 1 -> { //Agragar persona
                System.out.print("Proporciona el nombre: ");
                var nombre = consola.nextLine();
                System.out.print("Proporciona el telefono: ");
                var tele = consola.nextLine();
                System.out.print("Proporciona el email: ");
                var email = consola.nextLine();
                // Crear el objeto de tipo persona
                var Persona = new Persona(nombre,tele,email);
                // Lo agregamos a la lista de personas
                personas.add(Persona);
                System.out.println("La lista tiene: " + personas.size() + " elementos");
            } // Fin caso 1

            case 2 -> { // Listar personas
                System.out.println("Listado de personas: ");
                // Mejora usando lambda y metodo de referencia
                //personas.forEach((persona) -> System.out.println(persona));
                personas.forEach(System.out::println);
            }
            case 3 -> { //Salida
                System.out.println("Hasta pronto...");
                salir = true;
            }
            default -> System.out.println("Opción erronea: "+ opcion);
        }// fin switch
        return salir;
    }
}