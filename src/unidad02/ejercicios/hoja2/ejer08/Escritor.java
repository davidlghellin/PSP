/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidad02.ejercicios.hoja2.ejer08;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David López González
 */
public class Escritor extends Thread
{

    private Fichero f;

    public Escritor(Fichero f)
    {
        this.f = f;
    }

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                sleep((long) (Math.random()*4000));
                f.escribir((int) (Math.random()*200));
            } catch (InterruptedException ex)
            {
                Logger.getLogger(Escritor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex)
            {
                Logger.getLogger(Escritor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
