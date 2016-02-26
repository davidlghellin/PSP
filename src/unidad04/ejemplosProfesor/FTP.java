package unidad04.ejemplosProfesor;

import java.io.FileInputStream;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 *
 * @author David López González
 */
public class FTP
{

    public static void main(String[] args)
    {
        FTPClient cliente = new FTPClient();
        /*  String servFTP = "matrix";
        String usuario = "dam2";
        String clave = "admin_pass2";*/

        String servFTP = "192.168.0.101";
        String usuario = "bayes";
        String clave = "bayes";
        try
        {
            cliente.setPassiveNatWorkaround(false);
            cliente.connect(servFTP, 21);
            boolean login = cliente.login(usuario, clave);
            if (login)
            {
                System.out.println("Conexión ok");
            }
            else
            {
                System.out.println("Login incorrecto");
                cliente.disconnect();
                System.exit(1);
            }
            System.out.println("Directorio actual: " + cliente.printWorkingDirectory());
            cliente.changeWorkingDirectory("debian");
            FTPFile[] files = cliente.listFiles();
            System.out.println("Número de ficheros: " + files.length);
            String tipos[] =
            {
                "Fichero", "Directorio", "Enlace"
            };

            for (int i = 0; i < files.length; i++)
            {
                System.out.println("\t" + files[i].getName()
                        + "\t=>" + tipos[files[i].getType()]);
            }

            cliente.storeFile("fichSubido", new FileInputStream("/home/wizord/arch"));
            
         /*   InputStream in = new FileInputStream(files[0].toString());
            ftp.setFileType(ftp.BINARY_FILE_TYPE);
            boolean Store = ftp.storeFile("/home/wizord/aa", in);*/

            boolean logout = cliente.logout();
            if (logout)
            {
                System.out.println("Logout...");
            }
            else
            {
                System.out.println("Logout incorrecto");
            }

            cliente.disconnect();

        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
