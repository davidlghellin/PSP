package unidad03.ejercicios.ejercicio02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 *
 * @author David López González
 */
public class Server
{
    /*
    Construir un servidor en Java que ofrezca dos servicios. Por un lado, actuará como un servidor echo para
    clientes que soliciten este servicio en el puerto 9998; por otro lado, devolverá la hora del sistema a los clientes
    que soliciten la hora a través del puerto 9999.
    */
    public static void main(String[] args) throws IOException
    {
        //ServerHora sh = new ServerHora();
        ServerEcho se = new ServerEcho();
    }
}

class ServerHora 
{

    //Variables de hora
    ServerSocket serverHora;
    Socket socketHora;
    InetSocketAddress dirHora;
    DataOutputStream osHora;
    DataInputStream isHora;

    ServerHora() throws IOException
    {
        //hora
        serverHora = new ServerSocket();
        dirHora = new InetSocketAddress("localhost", 9999);
        serverHora.bind(dirHora);
        socketHora = serverHora.accept();
        isHora = new DataInputStream(socketHora.getInputStream());
        osHora = new DataOutputStream(socketHora.getOutputStream());
        String texto = null;

        do
        {
            texto = isHora.readUTF();
            System.out.println("Dice: " + texto);
            osHora.writeUTF((new Date()).toString());
        }while (!texto.equalsIgnoreCase("fin") && socketHora.isConnected());
        osHora.close();
        isHora.close();
        socketHora.close();
        serverHora.close();
        System.out.println("termine");
    }
}

class ServerEcho
{

    //Variables de hora
    ServerSocket serverEcho;
    Socket socketEcho;
    InetSocketAddress dirEcho;
    DataOutputStream osEcho;
    DataInputStream isEcho;

    ServerEcho() throws IOException
    {
        //echo
        serverEcho = new ServerSocket();
        dirEcho = new InetSocketAddress("127.0.0.1", 9998);
        serverEcho.bind(dirEcho);
        socketEcho = serverEcho.accept();
        isEcho = new DataInputStream(socketEcho.getInputStream());
        osEcho = new DataOutputStream(socketEcho.getOutputStream());
        String texto = null;

        do
        {
            texto = isEcho.readUTF();
            System.out.println("Dice: " + texto);
            osEcho.writeUTF(texto);
        }while (socketEcho.isConnected() && !texto.equalsIgnoreCase("fin"));
    }
}
