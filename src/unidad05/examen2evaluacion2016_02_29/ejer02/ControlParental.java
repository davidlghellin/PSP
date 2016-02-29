package unidad05.examen2evaluacion2016_02_29.ejer02;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David López González
 */
public class ControlParental extends Thread
{

    private Socket cliente;

    public ControlParental(Socket cliente)
    {
        this.cliente = cliente;
    }

    @Override
    public void run()
    {
        DataInputStream is = null;
        DataOutputStream os = null;
        URL url;
        URLConnection urlCon;
        ArrayList<String> urls = new ArrayList<String>();

        try
        {
            //cargamos en el array list los que estan bloqueadas
            leerFichero(urls);
        } catch (IOException ex)
        {
            Logger.getLogger(ControlParental.class.getName()).log(Level.SEVERE, null, ex);
        }

        try
        {
            // Obtenemos la entrada y la salida
            is = new DataInputStream(cliente.getInputStream());
            os = new DataOutputStream(cliente.getOutputStream());
            // Con el cliente conectado pasamos a comprobar las urls
            if (cliente.isConnected())
            {
                String textoRecibido = is.readUTF();
                System.out.println(textoRecibido);

                url = new URL("http://" + textoRecibido);

                System.out.println("Página web recibida: " + textoRecibido);
                if (!urls.contains(textoRecibido))
                {
                    urlCon = url.openConnection();
                    BufferedReader in;
                    in = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
                    String linea;
                    StringBuilder sb = new StringBuilder("");
                    while ((linea = in.readLine()) != null)
                    {
                        System.out.println(linea);
                        sb.append(linea);
                    }
                    in.close();
                    os.writeUTF(sb.toString());
                }
                else
                {
                    System.out.println("Pagina web prohibida");
                    os.writeUTF(new String("Pagina web prohibida"));
                }
            }
        } catch (IOException ex)
        {
            Logger.getLogger(ControlParental.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try
            {
                is.close();
            } catch (IOException ex)
            {
                Logger.getLogger(ControlParental.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    ///pruebas
    public static void main(String[] args) throws FileNotFoundException, IOException
    {

        ArrayList<String> urls = new ArrayList<String>();
        leerFichero(urls);
        System.out.println("----");
        for (String u : urls)
        {
            System.out.println(u);
        }

    }

    public static void leerFichero(ArrayList<String> urls) throws FileNotFoundException, IOException
    {
        String cadena;
        FileReader f = new FileReader("/home/wizord/lista");
        BufferedReader b = new BufferedReader(f);
        while ((cadena = b.readLine()) != null)
        {
            urls.add(cadena);
            System.out.println(cadena);
        }
        b.close();
    }

}
