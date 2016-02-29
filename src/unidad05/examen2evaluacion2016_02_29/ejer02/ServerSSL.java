package unidad05.examen2evaluacion2016_02_29.ejer02;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

/**
 *
 * @author David López González
 */
public class ServerSSL
{

    static class Hilo extends Thread
    {

        DataInputStream flujoEntrada = null;
        DataOutputStream flujoSalida = null;
        SSLSocket s;
        int i;

        URL url;
        URLConnection urlCon;
        ArrayList<String> urls = new ArrayList<String>();

        Hilo(SSLSocket s, int i)
        {
            this.s = s;
            this.i = i;
        }

        public void run()
        {
            try
            {
                flujoEntrada = new DataInputStream(s.getInputStream());
                flujoSalida = new DataOutputStream(s.getOutputStream());
                //cargamos la lista de webs distintas
                leerFichero(urls);
                //Mensaje del cliente
                String txt;
                do
                {
                    txt = flujoEntrada.readUTF();
                    url = new URL("http://" + txt);
                    System.out.println("Página web recibida: " + txt);
                    if (!urls.contains(txt))
                    {
                        urlCon = url.openConnection();
                        BufferedReader in;
                        in = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
                        String linea;
                        StringBuilder sb = new StringBuilder("");
                        while ((linea = in.readLine()) != null)
                        {
                            sb.append(linea);
                        }
                        in.close();
                        flujoSalida.writeUTF(sb.toString());
                    }
                    else
                    {
                        System.out.println("Pagina web prohibida");
                        flujoSalida.writeUTF(new String("esta prohida esa página"));
                    }

                } while (!txt.equals("FIN"));

                flujoSalida.close();
                flujoEntrada.close();
                s.close();
            } catch (IOException ex)
            {
                Logger.getLogger(ServerSSL.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        }
        b.close();
    }

    public static void main(String[] args) throws IOException
    {
        //Establecemos propiedades de los certificados
        System.setProperty("javax.net.ssl.keyStore", "/home/wizord/AlmacenClavesExamenDavid");
        System.setProperty("javax.net.ssl.keyStorePassword", "789456");
        int puerto = 2128;
        SSLServerSocketFactory sfact = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        SSLServerSocket servidorSSL = (SSLServerSocket) sfact.createServerSocket(puerto);
        SSLSocket clienteConectado = null;
        for (int i = 0; i < 5; i++)
        {
            System.out.println("Esperando cliente " + i);
            clienteConectado = (SSLSocket) servidorSSL.accept();
            new ServerSSL.Hilo(clienteConectado, i).start();
        }
        servidorSSL.close();
    }
}
