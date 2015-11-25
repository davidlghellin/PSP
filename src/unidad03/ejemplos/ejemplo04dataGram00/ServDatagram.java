package unidad03.ejemplos.ejemplo04dataGram00;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 *
 * @author David López González
 */
public class ServDatagram
{

    public ServDatagram() throws SocketException, IOException
    {
        DatagramSocket socket= new DatagramSocket(12345);
        byte [] abyte= new byte[1024];
        DatagramPacket sRecibido= new DatagramPacket(abyte, 1024);
        socket.receive(sRecibido);
        String rec=new String(abyte).trim();
        System.out.println(rec);
        
        abyte= new byte[1024];
        sRecibido= new DatagramPacket(abyte, 1024);
        socket.receive(sRecibido);
        rec=new String(abyte).trim();
        System.out.println(rec);
    }
    public static void main(String[] args) throws SocketException, IOException
    {
        ServDatagram s= new ServDatagram();
    }
}
