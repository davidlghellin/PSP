package unidad04.ejemplosProfesor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author profesor
 * @author David López González
 */
public class Ejemplo1UrlCon
{

    public static void main(String[] args)
    {
        URL url = null;
        URLConnection urlCon = null;
        try
        {
            url = new URL("http://www.elaltozano.es");
            urlCon = url.openConnection();
            System.out.println("Codificación: " + urlCon.getContentEncoding());
            System.out.println("Ultima modificación: " + urlCon.getLastModified());
            System.out.println("Tipo: " + urlCon.getContentType());

            BufferedReader in;
            in = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
            String linea;
            while ((linea = in.readLine()) != null)
            {
                System.out.println(linea);
            }
            in.close();
        } catch (Exception e){}
    }
}
