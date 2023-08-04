package mundopc.modelo;

public class Monitor {
    private final int idMonitor;
    private String marca;
    private double tamano;
    private static int contM;

    // Constructor
    private Monitor(){
        idMonitor = ++contM;
    }

    public Monitor(String marca, double tamano){
        this();
        this.marca = marca;
        this.tamano = tamano;
    }

    @Override
    public String toString() {
        return "Monitor{" +
                "idMonitor=" + idMonitor +
                ", marca='" + marca + '\'' +
                ", tamano=" + tamano +
                '}';
    }
}
