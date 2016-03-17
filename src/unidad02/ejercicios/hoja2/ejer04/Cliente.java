package unidad02.ejercicios.hoja2.ejer04;

import static java.lang.Thread.sleep;
import java.util.concurrent.Semaphore;

/**
 *
 * @author David López González
 */
public class Cliente implements Runnable
{

    /*
    En un supermercado hay N cajas y M clientes que estarán un tiempo aleatorio
    comprando y posteriormente seleccionarán una caja aleatoria para colocarse en su cola.
    Cuando les toque el turno serán atendidos procediendo al pago correspondiente e
    ingresando en la variable Resultados del supermercado. Se deben de crear tantos
    Threads como clientes haya y los parámetros M y N deben pedirse al usuario. El valor
    de pago de cada cliente puede ser aleatorio.
    */
    Semaphore semaforo;
    int tiempoComprando;
    int tiempoPagando;
    int dineroCompra;
    public static int suma = 0;

    public Cliente(Semaphore semaforo)
    {
        this.tiempoComprando = (int) (Math.random() * 5000);
        this.tiempoPagando = (int) (Math.random() * 5000);
        this.dineroCompra = (int) (Math.random() * 100);
        this.semaforo = semaforo;
    }

    @Override
    public void run()
    {
        //estare esperando hasta que haya una caja abierta
        try
        {
            this.semaforo.acquire();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        try
        {
            sleep(tiempoComprando);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        try
        {
            sleep(tiempoPagando);
            // Bloqueamos el método
            synchronized (this)
            {
                suma += dineroCompra;
            }
            this.semaforo.release(1);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
        System.out.println("Tiempo comprando " + tiempoComprando);
        System.out.println("Tiempo pagando " + tiempoPagando);
        System.out.println("Suma de tiempos " + (tiempoPagando + tiempoComprando));
        System.out.println("Paga el cliente " + Thread.currentThread().getName() + " " + dineroCompra + "€ y sale");
        System.out.println("Total en caja: " + suma);
    }
}
