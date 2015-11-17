/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidad01.ejercicios;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David López González
 */
public class ejer03
{

    public static void main(String[] args)
    {
        if (args.length < 1)
        {
            System.out.println("SE NECESITA UN ARGUMENTO");
            System.exit(1);
        }
        Process process = null;
        //String comando="ls";
        try
        {
            //process = Runtime.getRuntime().exec(comando);
            process = Runtime.getRuntime().exec(args[0]);
        } catch (IOException ex)
        {
            Logger.getLogger(ejer03.class.getName()).log(Level.SEVERE, null, ex);
        }

        InputStream is = process.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String linea;
        try
        {
            while ((linea = br.readLine()) != null)
            {
                System.out.println(linea);
            }
        } catch (IOException ex)
        {
            Logger.getLogger(ejer03.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
