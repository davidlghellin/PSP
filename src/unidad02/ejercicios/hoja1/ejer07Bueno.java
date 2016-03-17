package unidad02.ejercicios.hoja1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Thread.yield;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David López González
 */
public class ejer07Bueno
{

    /*
    Asignar prioridades a los hilos del ejercicios anterior para que escriban la
    secuencia numérica de forma ascendente (de menor a mayor).
    */
    public static void main(String[] args) throws IOException, InterruptedException
    {

        FileWriter fw = new FileWriter(new File("Prueba.txt"), true);
        PrintWriter salida = new PrintWriter(fw);

        ImprimirEjer07b i1 = new ImprimirEjer07b(11, 20, salida);
        ImprimirEjer07b i2 = new ImprimirEjer07b(21, 30, salida);
        ImprimirEjer07b i3 = new ImprimirEjer07b(1, 10, salida);

        Thread h1 = new Thread(i1);
        Thread h2 = new Thread(i2);
        Thread h3 = new Thread(i3);

        h1.setPriority(5);
        h2.setPriority(Thread.MIN_PRIORITY);
        h3.setPriority(Thread.MAX_PRIORITY);
        
        h1.start();
        h2.start();
        h3.start();

        h1.join();
        h2.join();
        h3.join();
        
        salida.close();
    }
}

class ImprimirEjer07b implements Runnable
{

    int ini, fin;
    PrintWriter salida;

    public ImprimirEjer07b(int ini, int fin, PrintWriter salida) throws IOException
    {
        this.ini = ini;
        this.fin = fin;
        this.salida = salida;
    }

    @Override
    public void run()
    {
        for (int i = ini; i <= fin; i++)
        {
            try
            {
                Thread.sleep(200);
                
                synchronized (salida)
                {
                    salida.println(i);
                }
            } catch (InterruptedException ex)
            {
                Logger.getLogger(ImprimirEjer06b.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(i);
        }

    }
}
