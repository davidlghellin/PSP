package unidad05.ejercicios;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.cert.X509Certificate;
import java.util.Scanner;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/**
 *
 * @author David López González
 */
public class Ejer08ClienteSSL
{

    public static void main(String[] args) throws IOException
    {
        //Establecemos propiedades de los certificados
        System.setProperty("javax.net.ssl.trustStore", "/home/wizord/AlmacenClaves");
        System.setProperty("javax.net.ssl.trustStorePassword", "123456");
        String host = "localhost";
        int puerto = 6000;
        System.out.println("Programa cliente");
        SSLSocketFactory sfact = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket cliente = (SSLSocket) sfact.createSocket(host, puerto);
        DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());

        Scanner teclado = new Scanner(System.in);
        String txt;
        do
        {
            System.out.println("Introduzca el texto a mandar al server, FIN para terminar");
            txt = teclado.nextLine();
            flujoSalida.writeUTF(txt);
        } while (!txt.equals("FIN")); 
        //Cerrando flujos y sockets
        flujoSalida.close();
        cliente.close();
    }
}
