package unidad03.ejercicios.ejercicio04;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author David López González
 */
public class ServidorEcho
{

    Socket s;
    DataOutputStream os;
    DataInputStream is;

    public ServidorEcho(Socket s) throws IOException
    {
        this.s = s;
        os = new DataOutputStream(s.getOutputStream());
        is = new DataInputStream(s.getInputStream());
    }

}
