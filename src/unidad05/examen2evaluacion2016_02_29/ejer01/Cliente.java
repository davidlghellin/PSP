package unidad05.examen2evaluacion2016_02_29.ejer01;

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

    public static void main(String[] args)
    {
        while (true)
        {
            try
            {
                Scanner teclado = new Scanner(System.in);
                //Creamos el socket cliente
                Socket socket = new Socket();
                // InetAdress escogemos dirección y puerto del server
                InetSocketAddress direccionServidor = new InetSocketAddress("localhost", 2128);
                // Nos conectamos con el metodo conect
                socket.connect(direccionServidor);

                DataInputStream entrada = new DataInputStream(socket.getInputStream());
                DataOutputStream salida = new DataOutputStream(socket.getOutputStream());

                System.out.println("URL: ");
                String respuesta = teclado.next();
                salida.writeUTF(respuesta);

                String pagina = entrada.readUTF();
                System.out.println(pagina);

            } catch (IOException ex)
            {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
