import java.util.Scanner;

public class CalculadoraApp {
    public static void main(String[] args) {
        Scanner consola = new Scanner(System.in);
        while (true) {
            System.out.println("**** Aplicacion Calculadora ****");
            mostrarMenu();
            try {
                var op = Integer.parseInt(consola.nextLine());
                //Revisar que esté dentro de las operaciones
                if ((op >= 1) && (op <= 4)) {
                    ejecutarop(op, consola);
                } else if (op == 5) { //Salir
                    System.out.println("Hasta pronto...");
                    break;
                } else {
                    System.out.println("Función erronea: " + op);
                }
                System.out.println();
            } // Fin Try
             catch (Exception e){
                 System.out.println("Ocurrio un error: "+ e.getMessage());
             }
        }// Fin while
    }// Fin main

    private static void mostrarMenu(){
        // Mostramos el menu

        System.out.println("""
                                    1. Suma
                                    2. Resta
                                    3. Multiplicación
                                    4. División
                                    5. Salir
                        """);
        System.out.print("Operación a realizar: ");
    }

    private static void ejecutarop(int op, Scanner consola){
        System.out.print("Proporciona valor operando 1: ");
        var op1 = Double.parseDouble(consola.nextLine());
        System.out.print("Proporciona valor operando 2: ");
        var op2 = Double.parseDouble(consola.nextLine());
        double res;

        switch (op) {
            case 1 -> { //Suma
                res = op1 + op2;
                System.out.println("El resultado es: " + res);
            }
            case 2 -> { // Resta
                res = op1 - op2;
                System.out.println("El resultado es: " + res);
            }
            case 3 -> { // Multiplicación
                res = op1 * op2;
                System.out.println("El resultado es: " + res);
            }
            case 4 -> { // División
                res = op1 / op2;
                System.out.println("El resultado es: " + res);
            }
            default -> System.out.println("Opción erronea: " + op);
        }
    }

}// Fin Clase
