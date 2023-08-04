package mundopc.modelo;

public class DispositivoDeEntrada {
    private String tipoDeEntrada;
    private String marca;

    // Constructor
    public DispositivoDeEntrada(String tipoDeEntrada, String marca){
        this.tipoDeEntrada=tipoDeEntrada;
        this.marca=marca;
    }

    @Override
    public String toString() {
        return "DispositivoDeEntrada{" +
                "tipoDeEntrada='" + tipoDeEntrada + '\'' +
                ", marca='" + marca + '\'' +
                '}';
    }
}
