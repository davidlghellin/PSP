package unidad02.ejercicios.hoja2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.math.BigInteger;
import java.util.Scanner;

/**
 *
 * @author David López González
 */
public class ejer01
{

    /**
     * Comprobamos si un número es primo 
     * @param i entero que comprobaremos si es primo
     * @return true si es primo, false si no es primo
     */
    public static boolean esPrimo(int i)
    {
        int intPrimos = 0;
        boolean bolBandera = false;
        for (int j = 2; j <= i && intPrimos <= 2; j++)
        {
            if (i % j == 0)
            {
                intPrimos++;
                bolBandera = true;
            }
        }
        return bolBandera && intPrimos == 1;
    }

    public static void main(String[] args)
    {
        //saber los primos
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduzca un número para sacar todos los primos hasta el");
        int intNum = teclado.nextInt();
        double suma = 0;
        for (int i = 2; i <= intNum; i++)
        {
            if(esPrimo(i))
                suma+=i;
        }
        System.out.println("La suma es: " + suma);
    }

}
