package unidad04.correo;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;

/**
 *
 * @author David López González
 */
public class EjemploLeerCorreo
{

    public static void main(String[] args) throws MessagingException, IOException
    {

        Store store = null;
        try
        {
            /*
            Establecer la Sesion de JavaMail:
            
            Primero hay que obtener una instancia de la clase Session de JavaMail.
            Para ello se llama al método Session.getDefaultInstance() pasándole unas
            propiedades para la conexión. El código puede ser como este para una
            cuenta de gmail.
            */
            Properties props = new Properties();
            // Nombre del host de correo, es smtp.gmail.com
            //props.setProperty("mail.smtp.host", "smtp.gmail.com");
            // TLS si está disponible
            props.setProperty("mail.smtp.starttls.enable", "true");
            // Puerto de gmail para envio de correos
            props.setProperty("mail.smtp.port", "587");
            // Nombre del usuario
            //props.setProperty("mail.smtp.user", "davidlg.dam@gmail.com");
            // Si requiere o no usuario y password para conectarse.
            props.setProperty("mail.smtp.auth", "true");
            Session session = Session.getDefaultInstance(props);
            // Para obtener un log de salida más extenso
            //session.setDebug(true);
            
            //Para leer mensajes , descargar el buzon
            store = session.getStore("imaps");
            store.connect("imap.gmail.com", "davidlg.dam@gmail.com", "aprenderesgratis");
            Folder folder= store.getFolder("INBOX");
            folder.open(Folder.READ_ONLY);
            Message []ms=folder.getMessages();
            for (Message m : ms)
            {
                //m.writeTo(System.out);
                System.out.println("Mensaje nº: "+m.getMessageNumber());
                System.out.println("\ttitulo: "+m.getSubject());
                System.out.println("\tType: "+m.getContentType());
                System.out.println("\tflags: "+m.getFlags());
                System.out.println("\tfecha: "+m.getSentDate());
                System.out.println("\tfecha: "+InternetAddress.toString(m.getFrom()));
                System.out.println("");
            }
            folder.close(true);
            
        } catch (NoSuchProviderException ex)
        {
            Logger.getLogger(EjemploLeerCorreo.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            store.close();
        }
    }
}
