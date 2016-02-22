package unidad04.extra;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 * Para los envíos, descargas y navegación desde java en FTP .
 * http://c00l-hx4-lnpl.blogspot.com.es/2011/12/clase-java-ftp.html
 * @author David López González
 */
public class FTP
{

    /**
     *
     * @param ftp es la instancia para la calse de FTP
     */
    public static FTPClient ftp = null;
    private static FileInputStream fis = null;
    private static FileOutputStream fos = null;

    private static String ip = "";
    private static String user = "";
    private static String pass = "";
    private static String localFile = "";

    private static String hostFile = "";
    private static int tiempo = 0;
  
    
    public static void conectar() throws SocketException, IOException
    {
        ftp = new FTPClient();
        ftp.connect(ip);

        if (ftp.login(user, pass))
        {
            System.out.println("login OK");
        }
        else
        {
            System.out.println("login Error");
        }
    }

    
    public static void desconectar()
    {
        try
        {
            ftp.logout();
            ftp.disconnect();
            System.out.println("Googbye!");
        } catch (IOException ex)
        {
//            Logger.getLogger(FTP.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("||||||||||||Error en el Envio|||||||||||||");
        }
    }

    
    public static void directorioActual() throws IOException
    {
        System.out.println(ftp.printWorkingDirectory());
    }

    public static void cambiarDeDirectorioEnFTP(String dir) throws IOException
    {
        ftp.changeWorkingDirectory(dir);
    }

    public static void cambiarDirectorioAnterior() throws IOException
    {
        ftp.changeToParentDirectory();
    }

    
    public static boolean enviarArchivoFTP() throws FileNotFoundException
    {
        try
        {
            fis = new FileInputStream(localFile);

            if (ftp.storeFile(hostFile, fis))
            {
                System.out.println("send OK");
                fis.close();
                return true;
            }
            else
            {
                System.out.println("send ERROR");
                fis.close();
                return false;

            }
        } catch (IOException ex)
        {
            Logger.getLogger(FTP.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error en el Envio");
            return false;
        }

    }

    public static void descargarArchivoFTP(String localFile, String hostFile) throws FileNotFoundException, IOException
    {

        fos = new FileOutputStream(localFile);
        if (ftp.retrieveFile("/" + hostFile, fos))
        {
            System.out.println("Desgarca correcta");
        }
        else
        {
            System.out.println("Error Descarga");
        }

        fos.close();

    }

    public static void eliminarArchivoFTP() throws IOException
    {

        boolean deleted = ftp.deleteFile(hostFile);
        if (deleted)
        {
            System.out.println("File deleted...");
        }
        else
        {
            System.out.println("Error al borrar");
        }

    }

    public static void listaArchivos() throws IOException
    {

        FTPFile[] ftpFiles = ftp.listFiles();
        for (FTPFile ftpFile : ftpFiles)
        {
            // Check if FTPFile is a regular file
            if (ftpFile.getType() == FTPFile.FILE_TYPE)
            {
                System.out.println("FTPFile: " + ftpFile.getName() + "; " + ftpFile.getSize() + " b");
            }
        }
    }

    public static void listaDirectorios() throws IOException
    {

        FTPFile[] ftpFiles = ftp.listDirectories();
        for (FTPFile ftpFile : ftpFiles)
        {
            // Check if FTPFile is a regular file
            if (ftpFile.getType() == FTPFile.FILE_TYPE)
            {
            //  System.out.println("FTPFile: " + ftpFile.getName() + "; " + ftpFile.getSize()/1024 + " Kbs");
                System.out.println("FTPFile: " + ftpFile.getName() + "; " + ftpFile.getSize() + " b");
            }
        }
    }

    public static void lista() throws IOException
    {
        String[] names = ftp.listNames();
        for (String name : names)
        {
            System.out.println("Name = " + name);
        }

    }

    public static void setTimeOut() throws SocketException
    {
        ftp.setSoTimeout(getTiempo());

    }
    public static void getTimeout() throws SocketException
    {
        System.out.println(ftp.getSoTimeout());
    }

    public static void setDataTimeOut()
    {
        ftp.setDataTimeout(tiempo);
    }

    public static void getDataTimeOut()
    {

        System.out.println(ftp.getDataConnectionMode());
    }

    public static void mensaje() throws IOException
    {
        System.out.println(ftp.getControlKeepAliveTimeout());
    }
    
    public static int getTiempo(){return tiempo;}
    public static void setTiempo(int tiempo){FTP.tiempo = tiempo;}
    public static String getHostFile(){return hostFile;}
    public static void setHostFile(String hostFile){FTP.hostFile = hostFile;}
    public static String getIp(){return ip;}
    public static void setIp(String ip){FTP.ip = ip;}
    public static String getLocalFile(){return localFile;}
    public static void setLocalFile(String localFile){FTP.localFile = localFile;}    
    public static String getPass(){return pass;}    
    public static void setPass(String pass){FTP.pass = pass;}
    public static String getUser(){return user;}
    public static void setUser(String user){FTP.user = user;}
}
