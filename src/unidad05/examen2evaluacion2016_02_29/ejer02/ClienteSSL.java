package unidad05.examen2evaluacion2016_02_29.ejer02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/**
 *
 * @author David López González
 */
public class ClienteSSL
{
    public static void main(String[] args) throws IOException
    {
        //Establecemos propiedades de los certificados
        System.setProperty("javax.net.ssl.trustStore", "/home/wizord/AlmacenClavesExamenDavid");
        System.setProperty("javax.net.ssl.trustStorePassword", "789456");
        String host = "localhost";
        int puerto = 2128;
        System.out.println("Programa cliente");
        SSLSocketFactory sfact = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket cliente = (SSLSocket) sfact.createSocket(host, puerto);
        DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());
        DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());

        Scanner teclado = new Scanner(System.in);
        String txt;
        do
        {
            System.out.println("Introduzca la pagina web a mostrar, FIN para terminar");
            txt = teclado.nextLine();
            flujoSalida.writeUTF(txt);
            
            System.out.println(flujoEntrada.readUTF());
        } while (!txt.equals("FIN")); 
        //Cerrando flujos y sockets
        flujoEntrada.close();
        flujoSalida.close();
        cliente.close();
    }
}
