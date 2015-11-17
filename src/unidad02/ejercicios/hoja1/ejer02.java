package unidad02.ejercicios.hoja1;


import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author David López González
 */
public class ejer02
{
    public static void main(String[] args)
    {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Escriba un número mayor de 10");
        int n = teclado.nextInt();
        if (n > 10)
        {
            Aumentar a= new Aumentar(n);
            Thread ha=new Thread(a);
            Decrementar d= new Decrementar(n);
            Thread hb=new Thread(d);
            
            ha.start();
            hb.start();
        }
    }

    static class Aumentar implements Runnable
    {

        private int n;
        private int i = 0;

        public Aumentar(int n)
        {
            this.n = n;
        }

        @Override
        public void run()
        {
            while (i <= n)
            {
                try
                {
                    Thread.sleep(1000);
                    System.out.println(i++);
                } catch (InterruptedException ex)
                {
                    Logger.getLogger(ejer02.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

  static  class Decrementar implements Runnable
    {

        private int n;

        public Decrementar(int n)
        {
            this.n = n;
        }

        @Override
        public void run()
        {
            while (0 <= n)
            {
                try
                {
                    Thread.sleep(1000);
                    System.out.println(n--);
                } catch (InterruptedException ex)
                {
                    Logger.getLogger(ejer02.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
