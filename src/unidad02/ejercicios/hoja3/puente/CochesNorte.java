/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidad02.ejercicios.hoja3.puente;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David López González
 */
public class CochesNorte implements Runnable
{
    int nCoches;
    MonitorPuente monitor;

    public CochesNorte(int nCoches, MonitorPuente monitor)
    {
        this.nCoches = nCoches;
        this.monitor = monitor;
    }
    
    @Override
    public void run()
    {   
        
        try
        {
            monitor.entrarCocheDelNorte();
            Thread.sleep((long) (Math.random() * 3000));//tiempo en recorrer el puente
            monitor.salirCocheNorte();
        } catch (InterruptedException ex)
        {
            Logger.getLogger(CochesNorte.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
