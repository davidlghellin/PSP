/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidad02.ejercicios.hoja2;

import java.math.BigInteger;

/**
 *
 * @author David López González
 */
public class ejercicio01
{

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
