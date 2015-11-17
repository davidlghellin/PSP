/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidad02.ejemplo.monitorProductorConsumidor;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David López González
 */
public class Consumidor implements Runnable
{

    MonitorBuffer buf;

    public Consumidor(MonitorBuffer buf)
    {
        this.buf = buf;
    }

    @Override
    public void run()
    {
        double item = 0;
        while (true)
        {
            try
            {
                Thread.sleep(1000);
                item = buf.extraer();
                System.out.println("Consumiendo: " + item);
            } catch (InterruptedException ex)
            {
                Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
