/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidad02;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David López González
 */
public class ejem01Thread extends Thread
{

    private int ini, fin;

    @Override
    public void run()
    {
        for (int i = ini; i < fin; i++)
        {
            if (Primos.isPrimo(i))
            {
                System.out.print(i + ", ");
            }
        }
    }

    public static void main(String[] args)
    {

        long tIni,tFin;
        tIni=System.currentTimeMillis();
        ejem01Thread uno = new ejem01Thread       (2, 25001);
        ejem01Thread dos = new ejem01Thread   (25001, 50001);
        ejem01Thread tres = new ejem01Thread  (50001, 75001);
        ejem01Thread cuatro = new ejem01Thread(75001, 100001);
        
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
            Logger.getLogger(ejem01Thread.class.getName()).log(Level.SEVERE, null, ex);
        }
        tFin = System.currentTimeMillis();
        System.out.println("\ntiempo: " + (tFin - tIni));
    }

    public ejem01Thread(int ini, int fin)
    {
        this.ini = ini;
        this.fin = fin;
    }
}
