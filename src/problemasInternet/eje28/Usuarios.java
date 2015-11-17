/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problemasInternet.eje28;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David López González
 */
public class Usuarios implements Runnable
{
    
    MonitorAsignacionRecursos m;
    
    public Usuarios(MonitorAsignacionRecursos m)
    {
        this.m = m;
    }
    
    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                Thread.sleep((long) Math.random() * 3000);
                int n = (int) (Math.random() * 3 + 1);
                m.pedirRecurso(n);
                Thread.sleep((long) Math.random() * 5000);
                m.liberarRecurso(n);
            } catch (InterruptedException ex)
            {
                Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
