package unidad03.ejemplos.ejemplo03c;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David López González
 */
public class ServidorHiloHabla implements Runnable
{
    //TODO revisar

    private Socket socket;
    private DataOutputStream salida;
    private DataInputStream entrada;
    private int id;
    private String mensaje;

    public ServidorHiloHabla(Socket socket, int id, String mensaje) throws IOException
    {
        this.socket = socket;
        this.id = id;
        this.mensaje = mensaje;
        entrada = new DataInputStream(socket.getInputStream());
        salida = new DataOutputStream(socket.getOutputStream());
    }

    public void cerrar()
    {
        try
        {
            salida.close();
            entrada.close();
            socket.close();
        } catch (IOException ex)
        {
            Logger.getLogger(ServidorHiloHabla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run()
    {
        try
        {
            String texto = null;
            do
            {
                texto = entrada.readUTF();
                System.out.println("El cliente " + id + ": " + texto);
            } while (!texto.equals("FIN"));
        } catch (IOException ex)
        {
            Logger.getLogger(ServidorHiloHabla.class.getName()).log(Level.SEVERE, null, ex);
        }
        cerrar();
    }

}
