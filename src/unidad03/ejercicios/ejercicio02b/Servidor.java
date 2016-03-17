package unidad03.ejercicios.ejercicio02b;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David López González
 */
public class Servidor
{
    /*
    Construir un servidor en Java que ofrezca dos servicios. Por un lado, actuará como un servidor echo para
    clientes que soliciten este servicio en el puerto 9998; por otro lado, devolverá la hora del sistema a los clientes
    que soliciten la hora a través del puerto 9999.
    */
    public static void main(String[] args) throws IOException
    {
        Servidor s = new Servidor();
    }

    private ServerSocket socketEcho, socketHora;
    private InetSocketAddress dirEcho, dirHora;
    ObjectOutputStream osEcho, osHora;
    ObjectInputStream isEcho, isHora;

    Servidor() throws IOException
    {
        new ServerHora().start();
        new ServerEcho().start();
    }

    class ServerEcho extends Thread
    {

        Socket socket;
        String texto;

        ServerEcho() throws IOException
        {
            socketEcho = new ServerSocket();
            dirEcho = new InetSocketAddress("127.0.0.1", 9998);
            socketEcho.bind(dirEcho);
            socket = socketEcho.accept();
            System.out.println("Conexion extablecidoa");
            isEcho = new ObjectInputStream(socket.getInputStream());
            osEcho = new ObjectOutputStream(socket.getOutputStream());
        }

        @Override
        public void run()
        {
            do
            {
                try
                {
                    texto = isEcho.readUTF();
                    osEcho.writeObject(texto);
                } catch (IOException ex)
                {
                    Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                }
            } while (socket.isConnected() && !texto.equalsIgnoreCase("fin"));
        }

    }

    class ServerHora extends Thread
    {

        Socket socket;

        ServerHora() throws IOException
        {
            socketHora = new ServerSocket();
            dirHora = new InetSocketAddress("127.0.0.1", 9999);
            socketHora.bind(dirHora);
            socket = socketHora.accept();
            System.out.println("Conexion extablecida con el cliente");
            isHora = new ObjectInputStream(socket.getInputStream());
            osHora = new ObjectOutputStream(socket.getOutputStream());
        }

        @Override
        public void run()
        {
            String texto = null;
            do
            {
                try
                {
                    texto = isHora.readUTF();
                    System.out.println(texto);
                    osHora.writeUTF(texto);
                } catch (IOException ex)
                {
                    Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                }
            } while (!texto.equalsIgnoreCase("Bye"));
            System.out.println("cerrar");
        }

    }
}
