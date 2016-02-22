package unidad04.correo;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author David López González
 */
public class EnviaMensajeSimple
{

    public static void main(String[] args) throws MessagingException
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
        props.setProperty("mail.smtp.host", "smtp.gmail.com");

        // TLS si está disponible
        props.setProperty("mail.smtp.starttls.enable", "true");

        // Puerto de gmail para envio de correos
        props.setProperty("mail.smtp.port", "587");

        // Nombre del usuario
        props.setProperty("mail.smtp.user", "davidlghellin@gmail.com");

        // Si requiere o no usuario y password para conectarse.
        props.setProperty("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);

        // Para obtener un log de salida más extenso
        session.setDebug(true);

        /*
        Construir un mensaje de texto sencillo:

        Si queremos enviar un mensaje sencillo de texto, simplemente 
        instanciamos la clase MimeMessage. En el constructor se le pasa la 
        Session que acabamos de obtener. Una vez instanciado, rellenamos los 
        campos de interés: from, to, subject, texto, etc.
         */
        MimeMessage message = new MimeMessage(session);

        // Quien envia el correo
        message.setFrom(new InternetAddress("davidlghellin@gmail.com"));

        // A quien va dirigido
        message.addRecipient(Message.RecipientType.TO, new InternetAddress("davidlghellin@hotmail.com"));

        message.setSubject("Asunto del mensaje");
        //message.setContent("Texto del mensaje", "text/plain");
        message.setText("Texto del mensaje");

        //Forma 1 de enviar
        //Transport.send(message);
        
        //Forma 2 de enviar        
        String pass=UtilsCorreo.leePassword();
        message.saveChanges();
        Transport trans=session.getTransport("smtp");
        trans.connect("smtp.gmail.com","davidlghellin@gmail.com" , pass);
        trans.sendMessage(message,message.getAllRecipients());
        trans.close();
        
        
    }
}
