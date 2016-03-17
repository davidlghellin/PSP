package unidad02.ejercicios.hoja2.ejer04;

import java.util.Scanner;
import java.util.concurrent.Semaphore;

/**
 *
 * @author David López González
 */
public class ejer04
{
    /*
    En un supermercado hay N cajas y M clientes que estarán un tiempo aleatorio
    comprando y posteriormente seleccionarán una caja aleatoria para colocarse en su cola.
    Cuando les toque el turno serán atendidos procediendo al pago correspondiente e
    ingresando en la variable Resultados del supermercado. Se deben de crear tantos
    Threads como clientes haya y los parámetros M y N deben pedirse al usuario. El valor
    de pago de cada cliente puede ser aleatorio.
    */
    public static void main(String[] args)
    {
        Scanner teclado = new Scanner(System.in);
        int nCajas;
        System.out.println("Cuántas cajas?");
        nCajas = teclado.nextInt();
        Semaphore semaforo = new Semaphore(nCajas);
        
        System.out.println("Cuántos clientes?");
        int nClientes = teclado.nextInt();
        Cliente c[] = new Cliente[nClientes];

        for (int i = 0; i < c.length; i++)
        {
            c[i] = new Cliente(semaforo);
        }
        for (int i = 0; i < c.length; i++)
        {
            new Thread(c[i], "cliente " + (i + 1)).start();
        }
    }
}
