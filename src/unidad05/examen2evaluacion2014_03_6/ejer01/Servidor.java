package unidad05.examen2evaluacion2014_03_6.ejer01;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author aRojo24 <-- git
 * @author David López González
 */
public class Servidor
{

    public static void main(String[] args)
    {
        Socket cliente;
        int i = 0;
        try
        {
            // Iniciamos el servidor
            ServerSocket server = new ServerSocket();
            // Escogemos puerto de escucha
            InetSocketAddress enlace = new InetSocketAddress("localhost", 3128);

            server.bind(enlace);
            try
            {
                System.out.println("Esperando clientes: ");
                while (true)
                {
                    // Aceptamos Clientes y guardamos en el Socket
                    cliente = server.accept();
                    System.out.println("Cliente " + i);
                    i++;
                    new Proxy(cliente).start();
                    cliente = null;
                }
            } catch (IOException e)
            {
                System.out.println(e);
                System.exit(-1);
            }

        } catch (IOException ex)
        {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
