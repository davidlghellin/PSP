/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidad01.ejercicios;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David López González
 */
public class P3b
{
    public static void main(String[] args)
    {
        
        Process p = null;
        
        // Proceso 1
        ProcessBuilder pb = new ProcessBuilder("bash","-c","ls -la; cd /home/wizord/Descargas ; pwd");
        //String comando = "ls";
       /* pb.directory(new File("/home/wizord/NetBeansProjects/PSP/src/unidad01/ejercicios"));
        String comando="java P3.java";
        pb.command(comando.split(" "));*/
        
         try
        {
            p = pb.start();

        } catch (IOException ex)
        {
            Logger.getLogger(P3.class.getName()).log(Level.SEVERE, null, ex);
        }
        // le capturamos la "salida"
        InputStream is = p.getInputStream();
        BufferedReader br=null;
        try
        {
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        } catch (UnsupportedEncodingException ex)
        {
            Logger.getLogger(P3b.class.getName()).log(Level.SEVERE, null, ex);
        }
        String linea;

        try
        {
            while ((linea = br.readLine()) != null)
            {
                System.out.println(linea);
            }
        } catch (IOException ex)

        {
            Logger.getLogger(P2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
