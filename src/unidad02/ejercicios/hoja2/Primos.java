/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidad02.ejercicios.hoja2;

import unidad02.*;

/**
 *
 * @author David López González
 */
public class Primos
{

    static boolean isPrimo(int numero)
    {
        int contador = 2;
        boolean primo = true;

        if (numero % 2 == 0 && numero > 2)
        {
            return false;
        }
        int raizCuadradaN=(int) Math.sqrt(numero);
        while ((primo) && (contador != numero) &&(contador<=raizCuadradaN))
        {
            if (numero % contador == 0)
            {
                primo = false;
            }
            contador++;
        }
        return primo;
    }
}
