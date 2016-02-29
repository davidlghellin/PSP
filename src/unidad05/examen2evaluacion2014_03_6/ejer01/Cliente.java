package unidad05.examen2evaluacion2014_03_6.ejer01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author aRojo24 <-- git
 * @author David López González
 */
public class Cliente
{

    public static void main(String[] args)
    {
        while (true)
        {
            try
            {
                Scanner sc = new Scanner(System.in);
                //Creamos el socket cliente
                Socket socket = new Socket();
                // InetAdress escogemos dirección y puerto del server
                InetSocketAddress direccionServidor = new InetSocketAddress("localhost", 3128);
                // Nos conectamos con el metodo conect
                socket.connect(direccionServidor);

                DataInputStream entrada = new DataInputStream(socket.getInputStream());
                DataOutputStream salida = new DataOutputStream(socket.getOutputStream());

                System.out.println("URL: ");
                String respuesta = sc.next();
                salida.writeUTF(respuesta);

                String dato = entrada.readUTF();
                System.out.println(dato);

            } catch (IOException ex)
            {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
