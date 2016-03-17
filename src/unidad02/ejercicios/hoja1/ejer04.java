/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidad02.ejercicios.hoja1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David López González
 */
public class ejer04
{
    /*
    Crea un programa que lance un hilo que muestre la serie de Fibonacci (1
    1 2 3 5 8 13 ...).
    */

    public static void main(String[] args)
    {
        SerieFiboThread s = new SerieFiboThread();
        Thread hSerie = new Thread(s);
        hSerie.start();
    }
}

class SerieFiboThread implements Runnable
{

    private void serie()
    {
        long n1 = 1;
        long n2 = 1;
        long aux;
        int i = 0;
        System.out.println(n1 + "\n" + n2);
        //pondremos por ejemplo que haga 200
        while (i < 200)
        {
            aux = n1 + n2;
            n1 = n2;
            n2 = aux;
            try
            {
                Thread.sleep(300);
            } catch (InterruptedException ex)
            {
                Logger.getLogger(SerieFiboThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(aux);
        }

    }

    @Override
    public void run()
    {
        serie();
    }
}
