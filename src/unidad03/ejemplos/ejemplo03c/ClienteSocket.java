package unidad03.ejemplos.ejemplo03c;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author David López González
 */
public class ClienteSocket
{

    Socket clienteSocket;
    private int puerto = 9000;              // Puerto con el que trabajaremos
    private String ip = "127.0.0.1";        // IP con la que conectaremos
    private InetSocketAddress direccion;
    DataOutputStream salida;
    String texto;

    public ClienteSocket() throws IOException
    {
        clienteSocket = new Socket();       // Creamos la instancia del socket
        direccion = new InetSocketAddress(ip, puerto);
        clienteSocket.connect(direccion);   // Asociamos la direccion y creamos la conexión  
        salida = new DataOutputStream(clienteSocket.getOutputStream());
    }

    public void conectar() throws IOException
    {
        Scanner teclado = new Scanner(System.in);
        do
        {
            System.out.println("Soy cliente mandando, introduzca FIN para terminar");
            texto = teclado.nextLine();
            salida.writeUTF(texto);
        } while (clienteSocket.isConnected() && !texto.equals("FIN"));
        clienteSocket.close();
        System.out.println("Socket con el server cerrado");
    }
}

