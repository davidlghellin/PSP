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
public class Productor implements Runnable
{

    private MonitorBuffer buf;

    public Productor(MonitorBuffer buf)
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
                buf.insertar(++item);
                System.out.println("Produciendo: " + item);
            } catch (InterruptedException ex)
            {
                Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
