package mundopc.modelo;

public class Teclado extends DispositivoDeEntrada{

    private final int idTeclado;
    private static int contt;

    // Constructor
    public Teclado(String tipoDeEntrada, String marca){
        super(tipoDeEntrada,marca);
        idTeclado = ++contt;
    }

    @Override
    public String toString() {
        return "Teclado{" +
                "idTeclado=" + idTeclado +
                "} " + super.toString();
    }
}
