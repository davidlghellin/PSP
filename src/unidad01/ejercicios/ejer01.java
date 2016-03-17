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
public class ejer01
{

    /*
    Realiza un programa en Java que ejecute el comando ipconfig a través
    de la consola del sistema operativo y muestre su resultado por pantalla.
    */
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
