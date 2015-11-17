/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidad02;

/**
 *
 * @author David López González
 */
public class ejem01
{

    public static void main(String[] args)
    {
        long tIni,tFin;
        tIni=System.currentTimeMillis();
        for (int i = 2; i < 100000; i++)
        {
            if (Primos.isPrimo(i))
            {
                System.out.print(i + ", ");
            }
        }

        tFin = System.currentTimeMillis();
        System.out.println("\ntiempo: " + (tFin - tIni));
    }
}
