package unidad01.ejercicios;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David López González
 */
public class P2
{

    public static void main(String[] args) throws UnsupportedEncodingException
    {
        ProcessBuilder pb = new ProcessBuilder();
        pb.directory(new File("/home/wizord/Documentos/DAM/2ºDAM/PSP/"));
        //String comando = "ls -la";
        String comando = "cat p2.txt";
        pb.command(comando.split(" "));

        Process p = null;
        //iniciamos proceso
        try
        {
            p = pb.start();

        } catch (IOException ex)
        {
            Logger.getLogger(P2.class.getName()).log(Level.SEVERE, null, ex);
        }
        // le capturamos la "salida"
        InputStream is = p.getInputStream();

        ProcessBuilder pb2 = new ProcessBuilder();

        Process p2 = null;
        InputStream is2 = null;
        String linea2;

        try
        {

            PrintWriter pw = new PrintWriter(p.getOutputStream(), true);

            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            linea2 = br.readLine();
            pb2.command(linea2);
            //si tubieramos un proceso que espera
            //pw.println(entradas al proceso nuevo)

            p2 = pb2.start();
            is2 = p2.getInputStream();
            pw.close();
        } catch (IOException ex)
        {
            Logger.getLogger(P2.class.getName()).log(Level.SEVERE, null, ex);
        }

// Lo pasamos a un  buffer de lectura
        BufferedReader br = new BufferedReader(new InputStreamReader(is2, "UTF-8"));
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
