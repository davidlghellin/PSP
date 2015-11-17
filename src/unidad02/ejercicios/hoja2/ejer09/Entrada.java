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
public class Entrada implements Runnable
{
    MonitorParking monitor;
    int n=1;

    public Entrada(MonitorParking monitor)
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
                monitor.entrar(n);
                System.out.println("Entra el "+(n++));
            } catch (InterruptedException ex)
            {
                Logger.getLogger(Entrada.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
