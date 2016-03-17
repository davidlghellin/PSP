package unidad02.ejercicios.hoja2;

import java.math.BigInteger;

/**
 *
 * @author David López González
 */
public class ejercicio01
{

    /*
    El programa sumará los números primos comprendidos entre 1 y 100000000 (usa la
    clase BigInteger). En el caso concurrente utiliza 5 hilos, cada uno para un rango de
    números diferente, acumula la suma en una variable compartida, establece mecanismos
    para evitar la condición de carrera. Toma los tiempos de ejecución en ambos programas
    para comprobar el rendimiento de cada uno.
    */
    public static void main(String[] args)
    {
        long tIni, tFin;
        tIni = System.currentTimeMillis();
        BigInteger sum = BigInteger.ZERO;
        for (int i = 2; i < 10000000; i++)
        {
            if (Primos.isPrimo(i))
            {
                //System.out.print(i + ", ");
                BigInteger bigInt = new BigInteger(i +"");
                sum=sum.add(bigInt);
            }
        }
        System.out.println("La suma es:"+sum);
        tFin = System.currentTimeMillis();
        System.out.println("\ntiempo: " + (tFin - tIni));
    }
}
