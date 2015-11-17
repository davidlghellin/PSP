/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidad01.ejercicios;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David López González
 */
public class P1
{

    public static void main(String[] args)
    {
        // Proceso 1
        ProcessBuilder pb = new ProcessBuilder();
        pb.directory(new File("/home/wizord/Documentos/DAM/2ºDAM/PSP/"));
        String comando = "cat h.sh";
        pb.command(comando.split(" "));

        // Proceso 2
        ProcessBuilder pb2 = new ProcessBuilder();
        Process p = null, p2 = null;
        try
        {
            p = pb.start();
        } catch (IOException ex)
        {
            Logger.getLogger(P1.class.getName()).log(Level.SEVERE, null, ex);
        }
        try
        {
            p2 = pb2.start();
            PrintWriter pw = new PrintWriter(p2.getOutputStream(), true);
            // lo que le paso
            pw.println(pb);
            pw.flush();
        } catch (IOException ex)
        {
            Logger.getLogger(P1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
