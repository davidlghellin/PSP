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
import unidad01.InfoDisco;

/**
 *
 * @author David López González
 */
public class ejer04
{

    public static void main(String[] args) throws UnsupportedEncodingException
    {
        if (args.length < 1)
        {
            System.out.println("SE NECESITA UN ARGUMENTO");
            System.exit(1);
        }
        ProcessBuilder pb = new ProcessBuilder();
        //pb.directory(new File("/home/wizord/Documentos/DAM/2ºDAM/PSP/"));
        pb.directory(new File(args[0]));

        pb.command("/bin/bash");
        // pb.command("/bin/bash h.sh");
        //ejecutar comandos complejos

        Process p = null;
        try
        {
            p = pb.start();
            // p es mi proceso arrancado, a p le paso un PrintWriter 
            //que será la entrada y se usa el outputStream
            PrintWriter pw = new PrintWriter(p.getOutputStream(), true);
            // lo que le paso
            pw.println("ls -la");
            pw.flush();
        } catch (IOException ex)
        {
            Logger.getLogger(ejer04.class.getName()).log(Level.SEVERE, null, ex);
        }
        InputStream is = p.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        String linea;
        int caracter;
        char[] bf = new char[1024];

        try
        {
            //int car = br.read(bf);
            //System.out.println("Caracteres:"+car);

            while ((caracter = br.read()) != 0)
            {
                System.out.print("" + (char) caracter);
                //          System.out.println(String.copyValueOf(bf, 0, car-1));

            }
            System.out.println("1");

        } catch (IOException ex)
        {
            Logger.getLogger(ejer04.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
