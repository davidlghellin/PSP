package unidad02.ejercicios.hoja1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David López González
 */
public class ejer03
{
    /*
    Crear un programa que lance dos hilos: el primero imprimirá por pantalla
    del 1 al 10 con un retardo de 1 segundo; el segundo hará lo mismo pero
    con 1,5 segundos de retardo
    */
    
    public static void main(String[] args)
    {
        AumentarEjer03 a1 = new AumentarEjer03();
        Aumentar2Ejer03 a15 = new Aumentar2Ejer03();

        Thread h1 = new Thread(a1);
        Thread h2 = new Thread(a15);
        h1.start();
        h2.start();
    }

}

class AumentarEjer03 implements Runnable
{

    int i = 0;

    @Override
    public void run()
    {
        while (i < 11)
        {
            try
            {
                Thread.sleep(1000);
            } catch (InterruptedException ex)
            {
                Logger.getLogger(Aumentar2Ejer03.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(i++);
        }
    }
}

class Aumentar2Ejer03 implements Runnable
{

    int i = 0;

    @Override
    public void run()
    {
        while (i < 11)
        {
            try
            {
                Thread.sleep(1500);
            } catch (InterruptedException ex)
            {
                Logger.getLogger(Aumentar2Ejer03.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(i++);
        }
    }
}
