/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidad00;

/**
 *
 * @author David López González
 */
public class p01 implements Runnable
{

    static int valor = 0;

    @Override
    public void run()
    {
        for (int i = 0; i < 100; i++)
        {
            valor++;
        }
    }

    public static void main(String[] args)
    {
        p01[] vect = new p01[5];
        Thread[] hilos = new Thread[5];
        for (int i = 0; i < vect.length; i++)
        {
            vect[i] = new p01();
            hilos[i] = new Thread(vect[i], "hilo" + i);
            hilos[i].start();
        }
        System.out.println(p01.valor);
        System.out.println("Debería dar 500, pero al trabajar con una variable "
                + "compartida no sabemos el orden y la exlusión mutua");

    }
}
