/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidad01.ejercicios;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David López González
 */
public class Redirecc
{

    public static void main(String[] args)
    {
        ProcessBuilder pb = new ProcessBuilder();

        pb.directory(new File("/home/wizord/NetBeansProjects/PSP/src/unidad01/ejercicios/"));
        
        File fOut = new File("salida.txt");
        File fErr = new File("error.txt");
        File fBat = new File("fichero.txt");

        pb.redirectError(fErr);
        pb.redirectOutput(fOut);
        pb.redirectInput(fBat);
        try
        {
            pb.start();
        } catch (IOException ex)
        {
            Logger.getLogger(Redirecc.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
