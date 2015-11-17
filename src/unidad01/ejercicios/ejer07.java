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
import unidad01.InfoDisco;

/**
 *
 * @author David López González
 */
public class ejer07
{

    public static void main(String args[])
    {

        String s = null;

        // Determinar en qué SO estamos
        String so = System.getProperty("os.name");
        String comando;

        if (so.equals("Linux")) // Comando para Linux
        {
            comando = "ifconfig";
        }
        else                    // Comando para Windows
        {
            comando = "cmd /c ipconfig";
        }

        Process process = null;
        try
        {
            process = Runtime.getRuntime().exec(comando);
        } catch (IOException ex)
        {
            Logger.getLogger(ejer01.class.getName()).log(Level.SEVERE, null, ex);
        }
        // si quiero enviar al proceso que he creado, debo usar el ouputStream
        // si quiero recibir desde el proceso que he creado debo usar el inputstream
        InputStream is = process.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String linea;
        try
        {
            if (so.equals("Linux"))
                while ((linea = br.readLine()) != null)
                {
                    // nos aseguramos que es la linea de la ip, podría salir el localhost
                    if ((linea.contains("inet ")) &&
                        (linea.contains("netmask ")) &&
                        (linea.contains("broadcast "))
                       )
                        System.out.println(linea);
                }
            else
                while ((linea = br.readLine()) != null)
                {
                    if ((linea.contains("IPv4")))
                        System.out.println(linea);
                }
        } catch (IOException ex)
        {
            Logger.getLogger(InfoDisco.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
