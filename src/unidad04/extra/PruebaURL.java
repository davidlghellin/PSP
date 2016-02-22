package unidad04.extra;

import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author David López González
 */
public class PruebaURL
{

    URL url;

    public PruebaURL()
    {
        try
        {
            url = new URL("http://x.mty.itesm.mx/java#referencia");
            despliega(url);
            url = new URL("http", "x.mty.itesm.mx", 80, "/java");
            despliega(url);
            url = new URL("http", "x.mty.itesm.mx", "/java");
            despliega(url);
            URL urlBase = new URL("http://x.mty.itesm.mx/");
            url = new URL(urlBase, "/pdiaz/java/index.htm");
            despliega(url);
        } catch (MalformedURLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args)
    {
        new PruebaURL();
    }

    void despliega(URL url)
    {
        System.out.println("Protocolo:\t" + url.getProtocol());
        System.out.println("Host:\t\t" + url.getHost());
        System.out.println("Puerto:\t\t" + url.getPort());
        System.out.println("Ruta:\t\t" + url.getPath());
        System.out.println("Usuario:\t"+url.getUserInfo());
        System.out.println(url.getRef());
        System.out.println(url.toString());
        System.out.println("+++++++++++++++++++++++++++++++++");
    }
}
