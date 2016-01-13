package unidad03.ejemplos.ejemploChat2;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;

/**
 *
 * @author David López González
 */
public class Leer extends Thread
{//Hilo que lee los mensajes que se reciben

    private Socket socket;

    public Leer(Socket socket)
    {//constructor del hilo
        super("Hilo leer");
        this.socket = socket;
        start();
    }

    public void run()
    {
        try
        {
            while (true)
            {//bucle para continuar la lectura
                InputStream aux = socket.getInputStream(); // flujo de datos del socket
                DataInputStream flujo = new DataInputStream(aux); //se asocia el flujo de datos a un flujo de lectura
                System.out.println(flujo.readUTF());//se imprime por pantalla el flujo
            }
        } catch (Exception e)
        {
            System.out.println("Error");
        }
    }
}
