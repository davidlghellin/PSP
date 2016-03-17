package unidad02.ejercicios.hoja2.ejer09;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David López González
 */
public class Entrada implements Runnable
{
        /*
    Un Parking con un número de plazas N recibe un número de coches M. Se crearán tantos
    Threads como coches haya. El parking dispondrá de una sola entrada y una única salida. Para
    acceder al parking debe haber plazas disponibles. El tiempo de espera dentro del parking es
    aleatorio. En el momento que un vehículo sale del parking notifica al dispositivo de control el
    número de plaza que ha dejado libre, poniéndose ésta a disposición del próximo coche que
    entre. Un vehículo que ha salido estará un tiempo aleatorio fuera del parking y después
    intentará entrar. Por tanto al parking estarán entrando y saliendo coches de forma indefinida.
    El resultado que debe mostrar debe ser algo así:
    
    ENTRADA: Coche 1 aparca en 0
    Plazas libres: 5
    Parking: [1] [0] [0] [0] [0] [0]
    ENTRADA: Coche 2 aparca en 1
    */
    
    MonitorParking monitor;
    int n=1;

    public Entrada(MonitorParking monitor)
    {
        this.monitor = monitor;
    }
   
    @Override
    public void run()
    {
        while(true)
        {
            try
            {
                Thread.sleep((long) (Math.random()*2000));
                monitor.entrar(n);
                System.out.println("Entra el "+(n++));
            } catch (InterruptedException ex)
            {
                Logger.getLogger(Entrada.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
