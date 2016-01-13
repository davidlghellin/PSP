package unidad03.ejercicios.ejercicio02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author David López González
 */
public class ClienteEcho
{

    Socket socket;
    InetSocketAddress dirHora;
    DataOutputStream osHora;
    DataInputStream isHora;

    public ClienteEcho() throws IOException
    {
        socket = new Socket();
        dirHora = new InetSocketAddress("127.0.0.1", 9998);
        socket.connect(dirHora);
        isHora = new DataInputStream(socket.getInputStream());
        osHora = new DataOutputStream(socket.getOutputStream());

        String texto = null;
        Scanner teclado = new Scanner(System.in);
        String textoServer;
        do
        {
            texto = teclado.nextLine();
            osHora.writeUTF(texto);
            textoServer = isHora.readUTF();
            System.out.println("Server Responde: " + textoServer);
        } while (!texto.equalsIgnoreCase("fin") && socket.isConnected());
        osHora.close();
        isHora.close();
        socket.close();
        System.out.println("Cliente Hora terminado");
    }

    public static void main(String[] args) throws IOException
    {
        ClienteEcho c = new ClienteEcho();
    }
}
