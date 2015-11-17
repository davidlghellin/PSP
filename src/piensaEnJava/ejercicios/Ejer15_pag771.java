/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piensaEnJava.ejercicios;

import java.util.logging.Level;
import java.util.logging.Logger;
import sun.org.mozilla.javascript.ObjArray;

/**
 *
 * @author David López González
 */
public class Ejer15_pag771
{
    public static void main(String[] args) throws InterruptedException
    {
        Object obj=new Object();
        
        ClaseConSeccionesCriticas771 c1= new ClaseConSeccionesCriticas771(obj);
        ClaseConSeccionesCriticas771 c2= new ClaseConSeccionesCriticas771(obj);
        ClaseConSeccionesCriticas771 c3= new ClaseConSeccionesCriticas771(obj);
    
        Thread h1=new Thread(c1);
        Thread h2=new Thread(c2);
        Thread h3=new Thread(c3);
        
        h1.start();
        h2.start();
        h3.start();
        h1.join();h2.join();h3.join();
        System.out.println(c1.getN());
    }
}
class ClaseConSeccionesCriticas771 implements Runnable
{
    Object obj;
    ClaseConSeccionesCriticas771(Object obj){
        this.obj=obj;
    }
    private static int n=0;

    public int getN(){return n;}
    public void setN(int n){this.n = n;}
     
    public void inc()
    {
        synchronized (obj)
        {
            n++;
        }
    }

    public void dec()
    {
        synchronized (obj)
        {
            n--;
        }
    }

    public void doble()
    {
        synchronized (obj)
        {
            n = n * 2;
        }
    }

    @Override
    public void run()
    {
        try
        {
            Thread.sleep(1000);
        } catch (InterruptedException ex)
        {
            Logger.getLogger(ClaseConSeccionesCriticas771.class.getName()).log(Level.SEVERE, null, ex);
        }
        inc();
    }
}