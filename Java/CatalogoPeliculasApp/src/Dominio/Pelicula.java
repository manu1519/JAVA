package Dominio;

import java.util.Objects;

public class Pelicula {
    private String nombre;
    public Pelicula(){

    }
    public Pelicula(String nombre){
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Se ocupa para editar el equals and Hascode
    // Se ocupa especialmente para diferenciar objetos
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pelicula pelicula = (Pelicula) o;

        return Objects.equals(nombre, pelicula.nombre);
    }

    @Override
    public int hashCode() {
        return nombre != null ? nombre.hashCode() : 0;
    }

    @Override
    public String toString() {
        return this.nombre;
    }

    public static void main(String[] args) {
        var pelicula = new Pelicula("Batman");
        var pelicual = new Pelicula("Superman");

        System.out.println(pelicual);
        System.out.println(pelicula);
    }
}