package mundopc.modelo;

public class Computadora {
    private int idCompu;
    private String nombre;
    private Monitor monitor;
    private Teclado teclado;
    private Raton raton;
    private static int contC;

    //Constructor
    private Computadora(){
        idCompu = ++contC;
    }

    public Computadora(String nombre, Monitor monitor, Teclado teclado, Raton raton){
        this();
        this.nombre = nombre;
        this.monitor = monitor;
        this.teclado = teclado;
        this.raton = raton;
    }

    @Override
    public String toString() {
        return "Computadora{" +
                "   idCompu=" + idCompu +
                ",  nombre='" + nombre + '\'' +
                ", \n       monitor=" + monitor +
                ", \n       teclado=" + teclado +
                ", \n       raton=" + raton +
                '}';
    }
}
