/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidad02.ejemplo.monitorSimple;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David López González
 */
public class HiloRestador implements Runnable
{

    private Monitor monitor;

    public HiloRestador(Monitor monitor)
    {
        this.monitor = monitor;
    }

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                monitor.DEC();
                System.out.println("resto y total:"+monitor.toString()+" soy "+Thread.currentThread().getName());
            } catch (InterruptedException ex)
            {
                Logger.getLogger(HiloRestador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
