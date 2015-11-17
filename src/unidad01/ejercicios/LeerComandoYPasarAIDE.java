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
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.InflaterInputStream;

/**
 *
 * @author David López González
 */
public class LeerComandoYPasarAIDE
{

    public static void main(String[] args)
    {
        String comando = "pwd";
        ProcessBuilder pb = new ProcessBuilder(comando);
        Process shell = null;
        try
        {
            shell = pb.start();
        } catch (IOException ex)
        {
            Logger.getLogger(LeerComandoYPasarAIDE.class.getName()).log(Level.SEVERE, null, ex);
        }

        InputStream is = shell.getInputStream();
        BufferedReader bf = null;
        try
        {
            bf = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        } catch (UnsupportedEncodingException ex)
        {
            Logger.getLogger(LeerComandoYPasarAIDE.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("la salida del comando " + comando + " es:");
        String linea=null;
        try
        {
            while((linea=bf.readLine())!=null)
                System.out.println(linea);
        } catch (IOException ex)
        {
            Logger.getLogger(LeerComandoYPasarAIDE.class.getName()).log(Level.SEVERE, null, ex);
        }
        try
        {
            is.close();
        } catch (IOException ex)
        {
            Logger.getLogger(LeerComandoYPasarAIDE.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
