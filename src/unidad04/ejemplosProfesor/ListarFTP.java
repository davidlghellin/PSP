package unidad04.ejemplosProfesor;

import java.io.IOException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

/**
 *
 * @author David López González
 */
public class ListarFTP
{

    static public void main(String[] args)
    {

        // Datos para la conexion
        String server = "";
        String username = "";
        String password = "";

        // Cliente de conexion a FTP
        FTPClient ftp = new FTPClient();

        int respuesta, i;
        String[] lista;

        try
        {
            System.out.println("CONECTANDO AL SERVIDOR FTP");
            // Conectando e identificandose con el servidos
            ftp.connect(server);
            ftp.login(username, password);
            // Entrando a modo pasivo
            ftp.enterLocalPassiveMode();

            // Obteniendo respuesta del servidos
            respuesta = ftp.getReplyCode();
            System.out.println("RESPUESTA " + respuesta);

            // Si la respuesta del servidor indica podemos pasar procedemos 
            if (FTPReply.isPositiveCompletion(respuesta) == true)
            {
                System.out.println("LISTANDO ARCHIVOS");
                lista = ftp.listNames();

                for (i = 0; i < lista.length; i++)
                {

                    System.out.println(lista[i]);
                }
                // Si no avisamos
            }
            else
            {
                System.out.println("Error de conexin");
            }

            // en ambos casos terminaos sesion
            ftp.logout();
            // Y nos desconectamos
            ftp.disconnect();

            // Esta excepcion se lanza en caso de algun error durante el proceso 
        } catch (IOException e)
        {
            System.out.println("Error de conexion");
        }
    }
}
