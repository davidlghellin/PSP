package unidad04.ejemplosProfesor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author profesor
 * @author David López González
 */
public class Ejemplo2UrlCon
{

    public static void main(String[] args)
    {
        try
        {
            URL url = new URL("http://localhost/prueba.php");
            URLConnection urlCon = url.openConnection();
            urlCon.setDoOutput(true);

            String cadena = "nombre=Pepe&apellidos=Martínez Sánchez";

            //Escribir en la URL 
            PrintWriter output = new PrintWriter(urlCon.getOutputStream());
            output.write(cadena);
            output.close();
            //Leer de la URL

            BufferedReader in;
            in = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
            String linea;
            while ((linea = in.readLine()) != null)
            {
                System.out.println(linea);
            }
            in.close();

        } catch (Exception e){e.printStackTrace();}
    }

}
