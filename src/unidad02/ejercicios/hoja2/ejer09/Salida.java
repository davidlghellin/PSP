/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidad02.ejercicios.hoja2.ejer09;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David López González
 */
public class Salida implements Runnable
{
    MonitorParking monitor;

    public Salida(MonitorParking monitor)
    {
        this.monitor = monitor;
    }

    @Override
    public void run()
    {
        while(true)
        {
            try
            {
                Thread.sleep((long) (Math.random()*2000));
                System.out.println("Sale el coche:"+monitor.salir());
                monitor.imp();
            } catch (InterruptedException ex)
            {
                Logger.getLogger(Salida.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    }
    
}
