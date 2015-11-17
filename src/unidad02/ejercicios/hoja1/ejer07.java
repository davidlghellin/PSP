/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class ejer07
{

    public static void main(String[] args) throws IOException, InterruptedException
    {
        ImprimirEjer07 i1 = new ImprimirEjer07(11, 20);
        ImprimirEjer07 i2 = new ImprimirEjer07(21, 30);
        ImprimirEjer07 i3 = new ImprimirEjer07(1, 10);

        Thread h1 = new Thread(i1);
        Thread h2 = new Thread(i2);
        Thread h3 = new Thread(i3);

        h1.setPriority(5);
        h2.setPriority(1);
        h3.setPriority(10);

        h1.start();
        h2.start();
        h3.start();

        h1.join();
        h2.join();
        h3.join();
    }
}

class ImprimirEjer07 implements Runnable
{

    int ini, fin;

    FileWriter fw;
    PrintWriter salida;

    public ImprimirEjer07(int ini, int fin) throws IOException
    {
        this.ini = ini;
        this.fin = fin;
        fw = new FileWriter(new File("Prueba.txt"), true);
        salida = new PrintWriter(fw);
    }

    @Override
    public void run()
    {
        try
        {
            for (int i = ini; i <= fin; i++)
            {
                salida.println(i);
            }
            salida.close();fw.close();
        } catch (IOException ex)
        {
            Logger.getLogger(ImprimirEjer07.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
}
