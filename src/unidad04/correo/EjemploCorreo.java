package unidad04.correo;

import java.security.Security;
import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import static unidad04.correo.UtilsCorreo.leePassword;

/**
 *
 * @author David López González
 */
public class EjemploCorreo
{

    private static String smtpHost = "smtp.googlemail.com";
    private static String deCorreo = "davidlg.dam@gmail.com";
    private static String paraCorreo = "davidlghellin@hotmail.com";

    public static void main(String[] args) throws MessagingException
    {
        Properties props = System.getProperties();

        //Activa SSL para smtp
        props.setProperty("mail.smtp.ssl.enable", "true");
        //Establece el puerto
        props.setProperty("mail.smtp.port", "465");

        props.setProperty("mail.transport.protocol", "smtps");
        //Activa la autenticación, mi servidor requiere autenticación
        props.setProperty("mail.smtps.auth", "true");
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

        props.put("mail.smtps.host", smtpHost);

        //clase session
        Session sesion = Session.getDefaultInstance(props);
        sesion.setDebug(true);

        //Clase messaje
        Message mensaje = new MimeMessage(sesion);
        mensaje.setSubject("Archivo adjunto");
        mensaje.setFrom(new InternetAddress(deCorreo));

        mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(paraCorreo));

        // Create the message part 
        BodyPart messageBodyPart = new MimeBodyPart();
        // Rellena el mensaje
        messageBodyPart.setText("Aquí llevas un archivo adjunto");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        Transport t = sesion.getTransport();

        String pass = leePassword();
        
        t.connect(deCorreo,pass);

        t.sendMessage(mensaje, mensaje.getAllRecipients());
        t.close();
    }
}
