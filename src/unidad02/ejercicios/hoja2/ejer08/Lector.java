/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidad02.ejercicios.hoja2.ejer08;

import static java.lang.Thread.sleep;

/**
 *
 * @author David López González
 */
public class Lector extends Thread
{
    private Fichero f;


    public Lector(Fichero f)
    {
        this.f = f;
    }

    @Override
    public void run()
    {
        String palabra;
        try
        {
            while(true)
            {
                sleep((long) (Math.random()*2000));
                palabra=f.leer();
                System.out.println(palabra);
            }
        } catch (Exception e)
        {
        }
    }
}
