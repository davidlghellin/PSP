package unidad02.ejercicios.hoja1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David López González
 */
public class ejer06Bueno
{

    public static void main(String[] args) throws IOException, InterruptedException
    {

        FileWriter fw = new FileWriter(new File("Prueba.txt"), true);
        PrintWriter salida = new PrintWriter(fw);

        ImprimirEjer06b i1 = new ImprimirEjer06b(11, 20, salida);
        ImprimirEjer06b i2 = new ImprimirEjer06b(21, 30, salida);
        ImprimirEjer06b i3 = new ImprimirEjer06b(1, 10, salida);

        Thread h1 = new Thread(i1);
        Thread h2 = new Thread(i2);
        Thread h3 = new Thread(i3);

        h1.start();
        h2.start();
        h3.start();

        h1.join();
        h2.join();
        h3.join();
        
        salida.close();
    }
}

class ImprimirEjer06b implements Runnable
{

    int ini, fin;
    PrintWriter salida;

    public ImprimirEjer06b(int ini, int fin, PrintWriter salida) throws IOException
    {
        this.ini = ini;
        this.fin = fin;
        this.salida = salida;
    }

    @Override
    public void run()
    {
        synchronized(salida)// supongo que queremos escribir los números seguidos sinque se pare
        {
            for (int i = ini; i <= fin; i++)
            {
                try
                {
                    Thread.sleep((int)(Math.random() * 100));
                    //synchronized(salida)// Otra situación es dejar el recurso compartido después de escribir
                    //{
                        salida.println(i);
                    //}
                } catch (InterruptedException ex)
                {
                    Logger.getLogger(ImprimirEjer06b.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println(i);
            }
        }
    }
}
