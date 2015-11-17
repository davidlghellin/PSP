/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidad02.ejercicios.hoja2.ejer04;

import static java.lang.Thread.sleep;
import java.util.concurrent.Semaphore;

/**
 *
 * @author David López González
 */
public class Cliente implements Runnable
{

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
