package unidad02.ejercicios.hoja2;

import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David López González
 */
public class ejercicio01Thread extends Thread
{

    /*
    El programa sumará los números primos comprendidos entre 1 y 100000000 (usa la
    clase BigInteger). En el caso concurrente utiliza 5 hilos, cada uno para un rango de
    números diferente, acumula la suma en una variable compartida, establece mecanismos
    para evitar la condición de carrera. Toma los tiempos de ejecución en ambos programas
    para comprobar el rendimiento de cada uno.
    */
    private int ini, fin;
    BigInteger sum = BigInteger.ZERO;

    public ejercicio01Thread(int ini, int fin)
    {
        this.ini = ini;
        this.fin = fin;
    }

    @Override
    public void run()
    {
        for (int i = ini; i < fin; i++)
        {
            if (Primos.isPrimo(i))
            {
                //System.out.print(i + ", ");
                BigInteger bigInt = new BigInteger(i + "");
                sum = sum.add(bigInt);
            }
        }
    }

    public static void main(String[] args)
    {
        int total = 10000000;
        int parte = total / 4;
        long tIni, tFin;
        tIni = System.currentTimeMillis();

        BigInteger sum = BigInteger.ZERO;

        // usamos 4 hilos para dar máximo rendimiento a los 4 núcleos
        ejercicio01Thread uno = new ejercicio01Thread(2, parte);
        ejercicio01Thread dos = new ejercicio01Thread(parte + 1, parte * 2);
        ejercicio01Thread tres = new ejercicio01Thread((parte * 2) + 1, parte * 3);
        ejercicio01Thread cuatro = new ejercicio01Thread((parte * 3) + 1, parte * 4);

        Thread h1 = new Thread(uno, "hilo 1");
        Thread h2 = new Thread(dos, "hilo 2");
        Thread h3 = new Thread(tres, "hilo 3");
        Thread h4 = new Thread(cuatro, "hilo 4");

        h1.start();
        h2.start();
        h3.start();
        h4.start();

        try
        {
            h1.join();
            h2.join();
            h3.join();
            h4.join();

        } catch (InterruptedException ex)
        {
            Logger.getLogger(ejercicio01Thread.class.getName()).log(Level.SEVERE, null, ex);
        }
        sum = sum.add(uno.sum);
        sum = sum.add(dos.sum);
        sum = sum.add(tres.sum);
        sum = sum.add(cuatro.sum);
        System.out.println("La suma es:" + sum);
        tFin = System.currentTimeMillis();
        System.out.println("\ntiempo: " + (tFin - tIni));
    }

}
