package unidad03.ejemplos.ejemplo03c;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author David López González
 */
public class ServidorSocket
{
    private ServerSocket server;        // Socket server
    private Socket socket;              // Socket de entrada
    private DataInputStream entrada;    // Flujo que leeremos del cliente
    private int puerto = 9000;          // Puerto con el que trabajaremos
    private String ip = "127.0.0.1";    // Direción ip con la que escucharemos
    private InetSocketAddress direccion;
    private int id = 0;

    public void servicio() throws IOException
    {
        server = new ServerSocket();    // Servidor con puerto
        direccion = new InetSocketAddress(ip, puerto);
        server.bind(direccion);         // Enlazamos la dirección y el puerto añ socket

        System.out.println("Servidor esperando clientes");
        while (true)                    
        {
            socket = server.accept();
            System.out.println("Conexion establecida: " + socket);
            new Thread(((ServidorHiloEscucha) new ServidorHiloEscucha(socket, id++))).start();
            socket = null;
        }
    }
}
