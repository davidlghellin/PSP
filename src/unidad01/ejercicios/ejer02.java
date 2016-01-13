/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidad01.ejercicios;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David López González
 */
public class ejer02
{
    public static void main(String[] args)
    {
        Runtime r = Runtime.getRuntime();
        String comando = "gnome-calculator";
        Process p = null;
        try
        {
            p = r.exec(comando);
            try
            {
                p.waitFor();
            } catch (InterruptedException ex)
            {
                Logger.getLogger(ejer02.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("La aplicación se ha cerrado con éxito");
        } catch (IOException ioe)
        {
            System.err.println("Error");
        }
    }
}