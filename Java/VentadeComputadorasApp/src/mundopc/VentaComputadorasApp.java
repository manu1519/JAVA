package mundopc;

import mundopc.modelo.Computadora;
import mundopc.modelo.Monitor;
import mundopc.modelo.Raton;
import mundopc.modelo.Teclado;
import mundopc.servicio.Orden;

public class VentaComputadorasApp {
    public static void main(String[] args) {
        // Crear objeto
        Raton Lenovo = new Raton("bluetooth","Lenovo");
        //System.out.println(Lenovo);
        Teclado Leno = new Teclado("Bluetooth", "Lenovo");
        //System.out.println(Leno);
        Monitor L = new Monitor("Lenovo", 27);
        //System.out.println(L);

        // Creamos un objeto de tipo computadora Lenovo
        Computadora computadoraLenovo = new Computadora("Lenovo", L, Leno,Lenovo);
        //System.out.println(computadoraLenovo);

        // Objeto computadora Dell
        Monitor mDell = new Monitor("Dell",15);
        Teclado tDell = new Teclado("USB", "Dell");
        Raton rDell = new Raton("USB", "Dell");
        Computadora compDell = new Computadora("Compu Dell", mDell, tDell, rDell);

        // Objeto computadora MAC
        Monitor mMac = new Monitor("MAC",27);
        Teclado tMac = new Teclado("Bluetooth","MAC");
        Raton rMac = new Raton("Bluetooth", "MAC");
        Computadora cMAC = new Computadora("Compu MAC",mMac,tMac,rMac);


        // Creamos un orden
        Orden orden1 = new Orden();
        orden1.agregarComputadora(computadoraLenovo);
        orden1.agregarComputadora(compDell);
        orden1.mostrarOrden();

        Orden orden2 = new Orden();
        orden2.agregarComputadora(compDell);
        orden2.agregarComputadora(cMAC);
        orden2.mostrarOrden();

        Orden orden3 = new Orden();
        orden3.agregarComputadora(computadoraLenovo);
        orden3.agregarComputadora(compDell);
        orden3.agregarComputadora(cMAC);
        orden3.mostrarOrden();

    }
}