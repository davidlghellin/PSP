package unidad03.ejemplos.ejemplo04dataGram01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author David López González
 */
public class ClieteDatagram
{

    public static void main(String argv[])
    {
        

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        DatagramSocket socket;
        InetAddress address;
        byte[] mensaje_bytes = new byte[256];
        byte[] MensajeServidor_bytes = new byte[256];
        String mensaje = "";
        String mensajeServidor = "";
        //Paquete para enviar al servidor
        DatagramPacket paquete;
        //Creamos el objeto paquete2 de tipo DaragramPacket para recibir del servidor
        DatagramPacket paquete2;
        mensaje_bytes = mensaje.getBytes();

        try
        {

            //Creamos el socket
            socket = new DatagramSocket();

            // Leemos el primer parámetro, donde debe ir la dirección  
            // IP del servidor 
            address = InetAddress.getByName("localhost");

            mensaje = "HELLO";
            mensaje_bytes = mensaje.getBytes();
            //Creamos paquete
            paquete = new DatagramPacket(mensaje_bytes, mensaje.length(), address, 6000);
            //Mandamos el paquete
            socket.send(paquete);

            //El mensaje recibido vendrá en bytes
            MensajeServidor_bytes = new byte[256];
            //Esperamos a recibir un paquete
            paquete2 = new DatagramPacket(MensajeServidor_bytes, 256);
            socket.receive(paquete2);
            //Convertimos el mensaje recibido en un string
            mensajeServidor = new String(MensajeServidor_bytes).trim();
            //Imprimimos el paquete recibido
            System.out.println(mensajeServidor);

            do
            {

                //Lee los caracteres introducidos por pantalla 
                mensaje = in.readLine();
                mensaje_bytes = mensaje.getBytes();

                //Creamos paquete
                paquete = new DatagramPacket(mensaje_bytes, mensaje.length(), address, 6000);

                //Mandamos el paquete
                socket.send(paquete);

                //El mensaje recibido vendrá en bytes
                MensajeServidor_bytes = new byte[256];

                //Esperamos a recibir un paquete
                paquete2 = new DatagramPacket(MensajeServidor_bytes, 256);
                socket.receive(paquete2);

                //Convertimos el mensaje recibido en un string
                mensajeServidor = new String(MensajeServidor_bytes).trim();

                //Imprimimos el paquete recibido
                System.out.println(mensajeServidor);

            } while (!mensaje.startsWith("fin"));
        } catch (Exception e)
        {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}
