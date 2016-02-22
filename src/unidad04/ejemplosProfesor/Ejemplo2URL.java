package unidad04.ejemplosProfesor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 *
 * @author David López González
 */
public class Ejemplo2URL
{

    public static void main(String[] args)
    {
        URL url = null;
        try
        {
            url = new URL("ftp://ftp.rediris.es");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        BufferedReader in;
        try
        {
            in = new BufferedReader(new InputStreamReader(url.openStream()));
            String linea;
            while ((linea = in.readLine()) != null)
            {
                System.out.println(linea);
            }
            in.close();
        } catch (Exception e){}
    }
}
