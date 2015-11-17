/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidad02;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David López González
 */
public class ejemplo00Demonio implements Runnable
{

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                Thread.sleep(500);
            } catch (InterruptedException ex)
            {
                Logger.getLogger(ejemplo00Demonio.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("demonio");
        }
    }
    
    public static void main(String[] args)
    {
        ejemplo00Demonio mi= new ejemplo00Demonio();
        
        Thread hilo = new Thread(mi);
        hilo.setDaemon(true);
        hilo.start();
        try
        {
            Thread.sleep(2000);
        } catch (InterruptedException ex)
        {
            Logger.getLogger(ejemplo00Demonio.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("fin main");
    }

}
