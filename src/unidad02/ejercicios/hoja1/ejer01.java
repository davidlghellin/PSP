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
public class ejer01 implements Runnable
{

    String texto;

    ejer01(String text)
    {
        this.texto = text;
    }

    @Override
    public void run()
    {

        for (int i = 0; i < 10; i++)
        {
            try
            {
                Thread.sleep((int)(Math.random()*3000));
            } catch (InterruptedException ex)
            {
                Logger.getLogger(ejer01.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("" + texto);
        }
    }
    public static void main(String[] args)
    {
        ejer01 ej1=new ejer01("hola");
        ejer01 ej2=new ejer01("adios");
        
        Thread h1=new Thread(ej1);
        Thread h2=new Thread(ej2);

        h1.start();h2.start();
    }
}
