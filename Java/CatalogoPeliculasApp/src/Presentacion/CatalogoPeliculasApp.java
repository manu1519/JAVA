package Presentacion;

import Dominio.Pelicula;
import servicio.IServicioPeliculas;
import servicio.ServicioPeliculasLista;
import servicio.Serviciopeliculasarchivo;

import java.util.Scanner;

public class CatalogoPeliculasApp {
    public static void main(String[] args) {
        var salir = false;
        var consola = new Scanner(System.in);

        //Agregamos la implementación del servicio
        //IServicioPeliculas servicioPeliculas = new ServicioPeliculasLista();
        IServicioPeliculas servicioPeliculas= new Serviciopeliculasarchivo();

        while(!salir){
            try{
                mostrarMenu();
                salir = ejecutaropciones(consola,servicioPeliculas);
            } catch(Exception e){
                System.out.println("Ocurrio un error: "+e.getMessage());
            }
            System.out.println();
        }// Fin de while
    }

    private static void mostrarMenu(){
        System.out.print("""
                *** Catalogo de Peliculas ***
                1. Agregar Peliculas
                2. Listar Peliculas
                3. Buscar Peliculas
                4. Salir
                
                Elige una opción:
                """);
    }

    private static boolean ejecutaropciones(Scanner consola, IServicioPeliculas servicioPeliculas){
        var op = Integer.parseInt(consola.nextLine());
        var salir = false;

        switch (op){
            case 1 -> {
                System.out.println("Introduce el nombre de la pelicula: ");
                var nomP = consola.nextLine();
                servicioPeliculas.agregarPelicula(new Pelicula(nomP));
            }
            case 2 -> {
                servicioPeliculas.listarPelicula();
            }
            case 3 -> {
                System.out.println("Introduce la pelicuala a buscar: ");
                var buscar = consola.nextLine();
                servicioPeliculas.buscarPelicula(new Pelicula(buscar));
            }
            case 4 -> {
                System.out.println("Hasta pronto!");
                salir = true;
            }
            default -> System.out.println("Opción no reconocida: " + op);
        }
        return salir;
    }

}