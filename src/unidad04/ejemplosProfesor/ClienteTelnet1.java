package unidad04.ejemplosProfesor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.telnet.*;

/**
 *
 * @author David López González
 * @author fran
 */
public class ClienteTelnet1
{

    static InputStream in;
    static OutputStream out;

    static void LecturaDatos() throws IOException
    {
        byte[] buff = new byte[1024];
        int nbytes;
        nbytes = in.read(buff);
        System.out.println(new String(buff, 0, nbytes));
    }

    static void EnvioDatos(String d)
    {
        try
        {
            out.write(d.getBytes());
            out.flush();
        } catch (IOException ex)
        {
            Logger.getLogger(ClienteTelnet1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args)
    {
        TelnetClient telnet = new TelnetClient("xterm");
        String server = "192.168.0.101";
        String login = "bayes";
        String passw = "bayes";
        try
        {
            telnet.connect(server);
            out = new PrintStream(telnet.getOutputStream());
            in = telnet.getInputStream();
            LecturaDatos();
            EnvioDatos(login);
            LecturaDatos();
            EnvioDatos(passw);
            LecturaDatos();
            EnvioDatos("ls");
            LecturaDatos();
            telnet.disconnect();
        } catch (Exception e){}
    }
}
