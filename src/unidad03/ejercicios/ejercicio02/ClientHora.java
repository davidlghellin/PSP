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
public class ClientHora
{

    /*
    Construir un servidor en Java que ofrezca dos servicios. Por un lado, actuará como un servidor echo para
    clientes que soliciten este servicio en el puerto 9998; por otro lado, devolverá la hora del sistema a los clientes
    que soliciten la hora a través del puerto 9999.
     */
    Socket socket;
    InetSocketAddress dirHora;
    DataOutputStream osHora;
    DataInputStream isHora;

    public ClientHora() throws IOException
    {
        socket = new Socket();
        dirHora = new InetSocketAddress("localhost", 9999);
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
        ClientHora c = new ClientHora();
    }
}
