package unidad02.ejercicios.hoja1;


import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David López González
 */
public class ejer02
{
    /*
    Crear un programa que pida al usuario un número mayor de
    10. Lanzar dos hilos: el primero imprimirá por pantalla los números del 0
    al número introducido; el segundo imprimirá por pantalla los números del
    número introducido al 0. Tiempo de retardo máximo 1 segundo.
    */
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
