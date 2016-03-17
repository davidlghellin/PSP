package unidad02.ejercicios.hoja2.ejer08;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David López González
 */
public class Fichero
{

    /*
    Dos tipos de procesos (lectores y escritores) para acceder a un fichero:
    
    Lectores: consultan el fichero.
    Escritores: consultan y modifican el fichero.
    Cuando un escritor accede al fichero, es el único proceso que la puede usar.
    Varios lectores pueden acceder simultáneamente
     */

    private static File fichero;
    private static boolean noEstaEscrito = true;
    private static boolean isEscribir = false;
    private static int isLeer = 0;
    private static int lineasLeidas = 0;
    private static int lineasEscritas = 0;

    /**
     * Le pasaremos la ruta al fichero donde se escribira y leerá
     *
     * @param fichero
     */
    public Fichero(File fichero)
    {
        this.fichero = fichero;
    }

    public Fichero()
    {
        this.fichero = new File("fichLecEsc.txt");
    }

    public synchronized void escribir(int n) throws IOException
    {
        try
        {
            while (isEscribir || isLeer > 0)
            {
                System.out.println("Estan leyendo o escribiendo" + Thread.currentThread());
                wait();
            }
            isEscribir = true;
            FileWriter fw = new FileWriter(fichero, true);
            PrintWriter salida = new PrintWriter(fw);
            Thread.sleep(2000);
            System.out.println("Escribimos en fichero: " + n + " soy:" + Thread.currentThread().getName());
            salida.println(n + " soy: " + Thread.currentThread().getName());
            salida.close();
            fw.close();
            lineasEscritas++;
            noEstaEscrito = false;
            isEscribir = false;
            notifyAll();
        } catch (InterruptedException e)
        {
            Logger.getLogger(Fichero.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public synchronized String leer()
    {
        String text = null;
        try
        {
            //TODO lineas leidas y escitas estaa mal
            //quitar
            while (noEstaEscrito && lineasLeidas >= lineasEscritas)
            {
                System.out.println("Espera el lector a q escriba, soy " + Thread.currentThread().getName());
                wait();
            }
            isLeer++;
            FileReader fr = new FileReader(fichero);
            BufferedReader bf = new BufferedReader(fr);
            do
            {
                //mejorar con stringBuilder
                text = bf.readLine();
                text += text;
                //System.out.println(text);
                //System.out.println("isLeer:"+isLeer);
            } while (text != null);
            System.out.println(text);
            isLeer--;
            lineasLeidas++;
            notifyAll();

        } catch (Exception e)
        {
            Logger.getLogger(Fichero.class.getName()).log(Level.SEVERE, null, e);
        }
        return text;
    }
}
