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
public class Primos
{

    static boolean isPrimo(int numero)
    {
        int contador = 2;
        boolean primo = true;

        while ((primo) && (contador != numero))
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
