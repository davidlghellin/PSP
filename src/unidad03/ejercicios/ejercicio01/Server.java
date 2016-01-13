package unidad03.ejercicios.ejercicio01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David López González
 */
public class Server
{

    ServerSocket server;        // Socket server
    Socket socket;              // Socket de entrada
    private int puerto = 9000;          // Puerto con el que trabajaremos
    private String ip = "127.0.0.1";      // Direción ip con la que escucharemos
    InetSocketAddress direccion;         // Puerto con el que trabajaremos
    DataOutputStream salida;    // Flujo que enviaremos al cliente
    DataInputStream entrada;     // Flujo que leeremos del cliente

    Server()
    {
        try
        {
            server = new ServerSocket();
            direccion = new InetSocketAddress(ip, puerto);
            server.bind(direccion);
            socket = new Socket();
            socket = server.accept();

            salida = new DataOutputStream(socket.getOutputStream());
            entrada = new DataInputStream(socket.getInputStream());
            String texto;

            do
            {
                texto = entrada.readUTF();
                System.out.println(texto);
                salida.writeUTF(texto);
            } while (!texto.equalsIgnoreCase("Bye"));
            salida.writeUTF("Cerrada conexion");
            salida.close();
            entrada.close();
            server.close();
            socket.close();
        } catch (IOException ex)
        {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}////////////////////
