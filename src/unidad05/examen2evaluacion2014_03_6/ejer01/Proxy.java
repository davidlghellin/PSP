package unidad05.examen2evaluacion2014_03_6.ejer01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author aRojo24 <-- git
 * @author David López González
 */
public class Proxy extends Thread
{

    private Socket cliente;

    public Proxy(Socket cliente)
    {
        this.cliente = cliente;
    }

    @Override
    public void run()
    {
        DataInputStream is = null;
        URL url;
        URLConnection urlCon;
        ArrayList<String> urls = new ArrayList<String>();
        try
        {
            // Obtenemos la entrada y la salida
            is = new DataInputStream(cliente.getInputStream());
            DataOutputStream os = new DataOutputStream(cliente.getOutputStream());
            // Con el cliente conectado pasamos a comprobar las urls
            if (cliente.isConnected())
            {
                String textoRecibido = is.readUTF();
                url = new URL(textoRecibido);
                urlCon = url.openConnection();
                System.out.println("Texto recibido: " + textoRecibido);
                if (!urls.contains(textoRecibido) && (urlCon.getLastModified() >= urlCon.getDate()))
                {
                    byte[] content = IOUtils.toByteArray(urlCon.getInputStream());
                    urls.add(Arrays.toString(content));
                    os.writeUTF(new String(content));
                }
                else
                {
                    byte[] content = IOUtils.toByteArray(urlCon.getInputStream());
                    os.writeUTF(new String(content));
                    for (int i = 0; i < urls.size(); i++)
                    {
                        System.out.println(urls.get(i));
                    }
                }
            }
        } catch (IOException ex)
        {
            Logger.getLogger(Proxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try
            {
                is.close();
            } catch (IOException ex)
            {
                Logger.getLogger(Proxy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
