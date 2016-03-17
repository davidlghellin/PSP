package unidad03.ejercicios.ejercicio01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David López González
 */
public class Cliente
{

    /*
    Construir un servidor en Java que actúe como un servidor echo. El servidor recibe del cliente una cadena de
    texto y se la vuelve a enviar al cliente. El cliente se desconecta al introducir la cadena “Bye”. Construir
    también un cliente para probar el servidor.
     */
    private Socket clienteSocket;              // Socket de entrada
    private int puerto = 9000;          // Puerto con el que trabajaremos
    private DataOutputStream salida;    // Flujo que enviaremos al cliente
    private DataInputStream entrada;     // Flujo que leeremos del cliente
    private String ip = "127.0.0.1";
    private InetSocketAddress direccion;

    public Cliente()
    {
        try
        {
            clienteSocket = new Socket();       // Creamos la instancia del socket
            direccion = new InetSocketAddress(ip, puerto);
            clienteSocket.connect(direccion);

            salida = new DataOutputStream(clienteSocket.getOutputStream());
            entrada = new DataInputStream(clienteSocket.getInputStream());

            Scanner teclado = new Scanner(System.in);
            String texto = null;
            String aux = null;
            do
            {
                System.out.println("Soy cliente mandando, introduzca Bye para terminar");
                texto = teclado.nextLine();
                salida.writeUTF(texto);
                // if(!texto.equals("Bye"))
                aux = entrada.readUTF();
                System.out.println("Esto me lo envia el server: " + aux);
            } while (clienteSocket.isConnected() && !texto.equalsIgnoreCase("Bye"));
            System.out.println("Socket con el server cerrado");
            salida.close();
            entrada.close();
            clienteSocket.close();
        } catch (IOException ex)
        {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
