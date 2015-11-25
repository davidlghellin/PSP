package unidad03.ejemplos.ejemplo04dataGram01;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David López González
 */
public class ServidorDatagram
{
    public static void main(String[] args) throws SocketException, UnknownHostException
    {ServidorDatagram c=new ServidorDatagram();
        
    }
    public ServidorDatagram() throws SocketException, UnknownHostException
    {
        DatagramSocket socket;
        boolean fin = false;
        //Creamos un objeto de tipo DatagrmaPacket para recibir un paquete del cliente
        DatagramPacket paquete;
        //Creamos paquete de vuelta para el cliente
        DatagramPacket paquete2;
        //Declaramos una variable de tipo string donde guardaremos el mensaje recibido
        //por el lciente
        String mensaje = "";
        //Declaramos una variable string para la cadena que le mandaremos al cliente
        String MensajeSalida = "";

        //Abrimos un socket en el puerto 6000
        //A través de este socket enviaremos paquetes de tipo Datagrama
        socket = new DatagramSocket(6000);

        //Nos preparamos para recibir una mensaje de 256 bytes
        byte[] mensaje_bytes = new byte[256];
        //Creamos un contenedor de datagrama, el buffer será el array mensaje_bytes
        paquete = new DatagramPacket(mensaje_bytes, 256);

        do
        {

            try
            {
                mensaje_bytes = new byte[256];
                
                //Creamos un contenedor de datagrama, el buffer será el array mensaje_bytes
                paquete = new DatagramPacket(mensaje_bytes, 256);
                
                //Esperamos a recibir un paquete
                socket.receive(paquete);
                
                //Convertimos el mensaje recibido en un string
                mensaje = new String(mensaje_bytes).trim();
                
                //Imprimimos el paquete recibido
                System.out.println(mensaje);
                
                //Condiciones encadenadas para mandar la información
                //dependiendo del dato recibido
                if (mensaje.startsWith("HELLO"))
                {
                    
                    MensajeSalida = "HELLO";
                }
                else if (mensaje.startsWith("ALL"))
                {
                    
                    MensajeSalida = "ALL BUR:250;CHE:300; BIG:540; PAT:380; SAL:240; BEV:210; DIE:0; COF:0; DES:300";
                    System.out.println(MensajeSalida);
                    
                }
                else if (mensaje.startsWith("BUR"))
                {
                    MensajeSalida = "BUR 250";
                    System.out.println(MensajeSalida);
                }
                else if (mensaje.startsWith("CHE"))
                {
                    MensajeSalida = "CHE 300";
                    System.out.println(MensajeSalida);
                }
                else if (mensaje.startsWith("BIG"))
                {
                    MensajeSalida = "BIG 540";
                    System.out.println(MensajeSalida);
                }
                else if (mensaje.startsWith("PAT"))
                {
                    MensajeSalida = "PAT 380";
                    System.out.println(MensajeSalida);
                }
                else if (mensaje.startsWith("SAL"))
                {
                    MensajeSalida = "SAL 240";
                    System.out.println(MensajeSalida);
                }
                else if (mensaje.startsWith("BEV"))
                {
                    MensajeSalida = "SAL 210";
                    System.out.println(MensajeSalida);
                }
                else if (mensaje.startsWith("DIE"))
                {
                    MensajeSalida = "DIE 0";
                    System.out.println(MensajeSalida);
                }
                else if (mensaje.startsWith("COF"))
                {
                    MensajeSalida = "COF 0";
                    System.out.println(MensajeSalida);
                }
                else if (mensaje.startsWith("DES"))
                {
                    MensajeSalida = "DES 300";
                    System.out.println(MensajeSalida);
                }
                else if (mensaje.startsWith("fin"))
                {
                    MensajeSalida = "fin";
                    System.out.println(MensajeSalida);
                    fin = true;
                }
                else
                {
                    MensajeSalida = "ERROR";
                    System.out.println(MensajeSalida);
                }
                
                //Nº de puerto desde donde se envió
                int puerto = paquete.getPort();
                //Dirección de Internet desde donde se envió
                InetAddress address = paquete.getAddress();
                //Preparamos el formato que le vamos a mandar
                byte[] mensaje2_bytes = new byte[256];
                //Guardamos en mensaje2_bytes el mensaje de salida formateado
                mensaje2_bytes = MensajeSalida.getBytes();
                //Generamos el paquete de vuelta, usando los datos
                // del remitente del paquete original
                paquete2 = new DatagramPacket(mensaje2_bytes, MensajeSalida.length(), address, puerto);
                // Enviamos
                socket.send(paquete2);
            } catch (IOException ex)
            {
                Logger.getLogger(ServidorDatagram.class.getName()).log(Level.SEVERE, null, ex);
            }

        } while (!fin);
    }

}
