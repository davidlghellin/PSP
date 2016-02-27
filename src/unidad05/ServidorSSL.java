package unidad05;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

/**
 *
 * @author David López González
 * @author Fran
 */
public class ServidorSSL
{

    static class Hilo extends Thread
    {

        DataInputStream flujoEntrada = null;
        DataOutputStream flujoSalida = null;
        SSLSocket s;
        int i;

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
                //Mensaje del cliente
                System.out.println("Recibo el mensaje " + flujoEntrada.readUTF() + " del cliente " + i);
                flujoSalida = new DataOutputStream(s.getOutputStream());
                //Envio un saludo
                flujoSalida.writeUTF("Saludos desde el servidor");
                flujoEntrada.close();
                flujoSalida.close();
                s.close();
            } catch (IOException ex)
            {
                Logger.getLogger(ServidorSSL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String[] args) throws IOException
    {
        //Establecemos propiedades de los certificados
        System.setProperty("javax.net.ssl.keyStore", "/home/wizord/AlmacenClaves");
        System.setProperty("javax.net.ssl.keyStorePassword", "123456");
        int puerto = 6000;
        SSLServerSocketFactory sfact = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        SSLServerSocket servidorSSL = (SSLServerSocket) sfact.createServerSocket(puerto);
        SSLSocket clienteConectado = null;
        for (int i = 0; i < 5; i++)
        {
            System.out.println("Esperando cliente " + i);
            clienteConectado = (SSLSocket) servidorSSL.accept();
            new Hilo(clienteConectado, i).start();
        }

        servidorSSL.close();
    }

}
