/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidad02.ejercicios.hoja2;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David López González
 */
public class EjerTorno2
{

    public static void main(String[] args)
    {
        Parque parque = new Parque();
        
        Torno2 t1 = new Torno2((int) (Math.random() * 10), parque);
        Torno2 t2 = new Torno2((int) (Math.random() * 10), parque);
        Torno2 t3 = new Torno2((int) (Math.random() * 10), parque);
        Torno2 t4 = new Torno2((int) (Math.random() * 10), parque);

        System.out.println("Hay " + t1.getnPersonasTorno()+ " personas en el torno 1" );
        System.out.println("Hay " + t2.getnPersonasTorno()+ " personas en el torno 2" );
        System.out.println("Hay " + t3.getnPersonasTorno()+ " personas en el torno 3");
        System.out.println("Hay " + t4.getnPersonasTorno()+ " personas en el torno 4");
        
        Thread h1 = new Thread(t1,"t1");
        Thread h2=new Thread(t2,"t2");
        Thread h3=new Thread(t3,"t3");
        Thread h4=new Thread(t4,"t4");
        
        h1.start();h2.start();h3.start();h4.start();
        
        try
        {
            h1.join();h2.join();h3.join();h4.join();
        } catch (InterruptedException ex)
        {
            Logger.getLogger(EjerTorno2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Total " + Parque.getNumPersonasParque());
        System.out.println("La suma da:" + (t1.getnPersonasTorno() + t2.getnPersonasTorno() + t3.getnPersonasTorno() + t4.getnPersonasTorno()));
    }
}

class Parque
{
    private static int numPersonasParque = 0;

    synchronized void aumentar()
    {
        numPersonasParque++; 
    }

    public static int getNumPersonasParque(){return numPersonasParque;}   
}

class Torno2 implements Runnable
{
    private int nPersonasTorno;
    private int aux;
    private Parque parque;

    Torno2(int n,Parque parque)
    {
        this.nPersonasTorno = n;
        aux = nPersonasTorno;
        this.parque = parque;
    }

    public int getnPersonasTorno(){return nPersonasTorno;}
    public void setnPersonasTorno(int nPersonasTorno){this.nPersonasTorno = nPersonasTorno;}

    @Override
    public void run()
    {
        while ((aux--) > 0)
        {
            try
            {
                sleep(50);
                parque.aumentar();
            } catch (InterruptedException ex)
            {
                Logger.getLogger(Torno2.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Pasa la " + (nPersonasTorno-aux)+" persona del torno: "+Thread.currentThread().getName());
            //System.out.println("aux: "+aux+" "+Thread.currentThread().getName());
        }
    }
}
