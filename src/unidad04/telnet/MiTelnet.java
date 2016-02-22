package unidad04.telnet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.telnet.TelnetClient;

/**
 *
 * @author David López González
 */
public class MiTelnet
{

    private TelnetClient telnet = new TelnetClient();
    private BufferedReader reader;
    private InputStream in;
    private PrintStream out;
    private String serverName;
    private String user;
    private String password;
    public static String promptComplete;

    public void conectarServer() throws IOException
    {

        serverName = "192.168.0.101";
        user = "bayes";
        password = "bayes";

        //Abro la conexión al telnet por el puerto 23
        telnet.connect(serverName, 23);

        //Ahora necesito una forma de enviarle los comandos al telnet
        //para esto obtengo un OutputStream desde el objeto telnet
        out = new PrintStream(telnet.getOutputStream());

        //Ahora necesito una forma de leer las respuestas que
        //me envía el telnet, para esto obtenemos un InputStream
        //del objeto telnet
        in = telnet.getInputStream();

        //Ahora envuelvo el InputStream dentro de un BufferedReader
        //para que la lectura de las respuestas del telnet sean mucho
        //mas sencillas y mejor gestionadas
        reader = new BufferedReader(new InputStreamReader(in));
    }

    public void desconectar() throws IOException
    {
        telnet.disconnect();
    }

    public void enviarComando(String comando)
    {
        try
        {
            System.out.println(comando+ "   :::::");
            out.write(comando.getBytes());
            out.flush();
        } catch (IOException ex)
        {
            Logger.getLogger(MiTelnet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void lecturaDatos() throws IOException
    {
        byte[] buff = new byte[1024];
        int nbytes;
        nbytes = in.read(buff);
        System.out.println(new String(buff, 0, nbytes));
    }

    public void user() throws IOException
    {
        lecturaDatos();
        enviarComando("bayes");
        lecturaDatos();
        enviarComando("bayes");
        lecturaDatos();
    }

    public static void main(String[] args) throws IOException
    {
        MiTelnet mi = new MiTelnet();
        mi.conectarServer();
        mi.user();
        /* mi.lecturaDatos();
        mi.enviarComando("ls");
        mi.lecturaDatos();*/
        
        
System.in.read();
        mi.desconectar();
    }
}
