package servicio;

import Dominio.Pelicula;

import java.io.*;

public class Serviciopeliculasarchivo implements IServicioPeliculas{

    private final String nombrearchivo = "peliculas.txt";

    public Serviciopeliculasarchivo(){
        var archivo = new File(nombrearchivo);
        try{
            // Si ya existe no se vuelve a crear
            if(archivo.exists()){
                System.out.println("El archivo ya existe!");
            }
            else {
                // Si no existe, se crea uno
                var salida = new PrintWriter(new FileWriter(archivo));
                salida.close();
                System.out.println("Se ha creado el archivo correctamente");
            }

        }catch(Exception e){
            System.out.println("Ocurrio un error al abrir el archivo: "+ e.getMessage());
        }
    }

    @Override
    public void listarPelicula() {
        // volvemos a abrir el archivo
        var archivo = new File(nombrearchivo);
        try{
            // Abrir archvio para lectura
            var entrada = new BufferedReader(new FileReader(archivo));
            // Leemos linea a linea el archivo
            String linea;
            linea = entrada.readLine();
            // Leemos todas las lineas
            while(linea != null){
                var peli = new Pelicula(linea);
                System.out.println(peli);
                // antes de terminar se vuelve a leer lo siguiente
                linea = entrada.readLine();
            }
            // Cerrar el archivo
            entrada.close();
        }catch (Exception e){
            System.out.println("Ocurrio un error al leer el archivo" + e.getMessage());
        }
    }

    @Override
    public void agregarPelicula(Pelicula pelicula) {
        boolean anexar = false;
        var archivo = new File(nombrearchivo);
        try {
            // Revisamos si existe
            anexar = archivo.exists();
            var salida = new PrintWriter(new FileWriter(archivo, anexar));
            // Agregar (toString)
            salida.println(pelicula);
            salida.close();
            System.out.println("Se agregado al archivo la pelicula: " + pelicula);
        }catch (Exception e){
            System.out.println("Ocurrio un error al agregar pelicula: " +e.getMessage());
        }
    }

    @Override
    public void buscarPelicula(Pelicula pelicula) {
        var archivo = new File(nombrearchivo);
        try{
            // Abrimos el archivo para lectura
            var entrada = new BufferedReader(new FileReader(archivo));
            String lineaTexto;
            lineaTexto = entrada.readLine();
            var indice = 1;
            var encontrado = false;
            var peliculaBuscar = pelicula.getNombre();
            while(lineaTexto != null){
                // Buscamos sin importar mayusculas y minusculas
                if (peliculaBuscar != null && peliculaBuscar.equalsIgnoreCase(lineaTexto)){
                    encontrado = true;
                    break;
                }
                // Leemos la siguiente linea antes de la siguiente iteracion
                lineaTexto = entrada.readLine();
                indice++;
            } // fin while
            // imprimimos los resultado de la busqueda
            if(encontrado) {
                System.out.println("Pelicula " + lineaTexto + " encontrada - linea " + indice);
            }
            else {
                System.out.println("No se ha encontrado la pelicula: " + pelicula.getNombre());
                entrada.close();
            }
        }catch (Exception e){
            System.out.println("Ocurrio un error al buscar pelicula: " +e.getMessage());
        }
    }
}
