package servicio;

import Dominio.Pelicula;

// Una interfaz no contiene la definicion de sus metodos
public interface IServicioPeliculas {
    public void listarPelicula();
    public void agregarPelicula(Pelicula pelicula);
    public void buscarPelicula(Pelicula pelicula);
}
