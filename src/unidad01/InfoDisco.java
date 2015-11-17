/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidad01;

import java.io.BufferedInputStream;
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
public class InfoDisco
{

    public static void main(String[] args)
    {
        Process process = null;
        try
        {
            process = Runtime.getRuntime().exec("ls -la");
        } catch (IOException ex)
        {
            Logger.getLogger(InfoDisco.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(InfoDisco.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
