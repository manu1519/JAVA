package servicio;

import Dominio.Pelicula;

import java.util.ArrayList;
import java.util.List;

public class ServicioPeliculasLista implements IServicioPeliculas{

    private final List<Pelicula> peliculas;

    public ServicioPeliculasLista() {
        this.peliculas = new ArrayList<>();
    }

    @Override
    public void listarPelicula() {
        System.out.println("Listado de peliculas... ");
        peliculas.forEach(System.out::println);
    }

    @Override
    public void agregarPelicula(Pelicula pelicula) {
        peliculas.add(pelicula);
        System.out.println("Se agrego la pelicula: "+pelicula);
    }

    @Override
    public void buscarPelicula(Pelicula pelicula) {
        // Regresa el indice de la pelicula encontrada en la lista
        var indice = peliculas.indexOf(pelicula);
        if(indice == -1)
            System.out.println("No se encontro la pelicula: "+pelicula);
        else
            System.out.println("Pelicula encontrada en el indice: "+ indice);
    }

    public static void main(String[] args) {
        // Creamos objetos de tipo pelicula
        var pel1 = new Pelicula("Batman");
        var pel2 = new Pelicula("Superman");
        // Creamos servicio (patron de diseño service)
        IServicioPeliculas servicioPeliculas = new ServicioPeliculasLista();
        // Agregamos a la lista
        servicioPeliculas.agregarPelicula(pel1);
        servicioPeliculas.agregarPelicula(pel2);
        // Listamos peliculas
        servicioPeliculas.listarPelicula();
        // Buscamos una pelicula (equals y hashcode)
        servicioPeliculas.buscarPelicula(pel2);
    }
}
