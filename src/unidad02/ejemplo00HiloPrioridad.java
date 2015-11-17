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
public class ejemplo00HiloPrioridad extends Thread
{

    String text;

    ejemplo00HiloPrioridad()
    {

    }

    public ejemplo00HiloPrioridad(String i)
    {
        text = i;
    }

    public void run()
    {
        for (int i = 0; i < 10; i++)
        {
            try
            {
                sleep(100);
            } catch (InterruptedException ex)
            {
                Logger.getLogger(ejemplo00HiloPrioridad.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(i+"=i: "+text);
        }
    }

    public static void main(String[] args)
    {
        ejemplo00HiloPrioridad[] tabla = new ejemplo00HiloPrioridad[10];
        for (int i = 0; i < 10; i++)
        {
            tabla[i] = new ejemplo00HiloPrioridad("Soy el hilo" + i);
            tabla[i].start();
        }
        

    }
}
