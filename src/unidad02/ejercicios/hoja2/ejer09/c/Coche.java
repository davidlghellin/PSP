/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidad02.ejercicios.hoja2.ejer09.c;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David López González
 */
public class Coche implements Runnable
{

    private int id;
    MonitorParking monitor;

    public Coche(){}
    public Coche(int id,MonitorParking monitor)
    {
        this.id = id;
        this.monitor = monitor;
    }
    

    public int getId(){return id;}

    public void esperar() throws InterruptedException
    {
        Thread.sleep((long) (Math.random()*1000));
    }

    public void entrar() throws InterruptedException
    {
        monitor.entrar(this);
       
         // El siguiente synchronized es para asegurarnos que pinta bien
        synchronized (monitor)
        { 
            System.out.println("Entra el coche: " + this.getId());
            monitor.imp();
        }
    }

    public void salir() throws InterruptedException
    {
        Thread.sleep((long) (Math.random() * 5000));
        Coche co=monitor.salir(this);
        
        // El siguiente synchronized es para asegurarnos que pinta bien
        synchronized (monitor)
        {
            System.out.println("Sale el coche: " + co.getId());
            monitor.imp();
        }
    }

    @Override
    public void run()
    {
        try
        {
            esperar();
            entrar();
            salir();
        } catch (InterruptedException ex)
        {
            Logger.getLogger(Coche.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
