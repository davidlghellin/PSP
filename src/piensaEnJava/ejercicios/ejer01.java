/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piensaEnJava.ejercicios;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David López González
 */
public class ejer01 implements Runnable
{

    private static int contador = 0;
    private int id = contador++;
    private static int num = 0;

    public ejer01()
    {
        System.out.println("ID = " + id);
    }

    @Override
    public void run()
    {
        System.out.println("Run()" + id);
        try
        {
            for (int i = 0; i < 10; i++)
            {
                num++;
            }
            Thread.sleep(1000);
            Thread.yield();

        } catch (InterruptedException ex)
        {
            Logger.getLogger(ejer01.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static int getNum()
    {
        return num;
    }

    public static void main(String[] args) throws InterruptedException
    {
        ejer01 a = new ejer01();
        ejer01 b = new ejer01();
        Thread h1 = new Thread(a);
        Thread h2 = new Thread(b);
        h1.start();
        h2.start();
        System.out.println(a.getNum());
        Thread.sleep(100);
        System.out.println(a.getNum());
    }

}
