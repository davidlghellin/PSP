package unidad03.ejemplos.ejemplo04dataGram00;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author David López González
 */
public class ClientDatagram
{

    public ClientDatagram() throws SocketException, UnknownHostException, IOException
    {
        DatagramSocket socket = new DatagramSocket();
        byte[] abyte = new byte[1024];
        String texto = "Hola";
        abyte = texto.getBytes();
        InetAddress address = InetAddress.getByName("127.0.01");

        DatagramPacket sEnviar = new DatagramPacket(abyte, texto.length(), address, 12345);
        socket.send(sEnviar);

        texto = "Hola2";
        abyte = texto.getBytes();
        // address= InetAddress.getByName("localhost");

        sEnviar = new DatagramPacket(abyte, texto.length(), address, 12345);
        socket.send(sEnviar);
    }

    public static void main(String[] args) throws SocketException, UnknownHostException, IOException
    {
        ClientDatagram c = new ClientDatagram();

    }
}
