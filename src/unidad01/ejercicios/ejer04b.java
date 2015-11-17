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
public class ejer04b
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
        
        //String comando = "ls -la";
        String comando = "/bin/bash h.sh";
        pb.command(comando.split(" "));
        
        //pb.command("ls");
        // pb.command("/bin/bash h.sh");
        //ejecutar comandos complejos

        Process p = null;
        //iniciamos proceso
        try
        {
            p = pb.start();

        } catch (IOException ex)
        {
            Logger.getLogger(ejer04.class.getName()).log(Level.SEVERE, null, ex);
        }
        // le capturamos la "salida"
        InputStream is = p.getInputStream();
        // Lo pasamos a un  buffer de lectura
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        String linea;

        try
        {
            while ((linea = br.readLine()) != null)
            {
                System.out.println(linea);
            }
        } catch (IOException ex)

        {
            Logger.getLogger(ejer04.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Aplicacion terminada");

    }
}
