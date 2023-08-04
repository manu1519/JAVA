package mundopc.modelo;

public class Raton extends DispositivoDeEntrada{
    private final int idRaton;
    private static int conta;

    // Constructor
    public Raton(String tipoDeEntrada, String marca){
        super(tipoDeEntrada, marca);
        idRaton = ++conta;
    }

    @Override
    public String toString() {
        return "Raton{" +
                "idRaton=" + idRaton +
                "} " + super.toString();
    }
}
