package unidad04.correo;

import java.security.Security;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import sun.misc.JavaAWTAccess;

/**
 *
 * @author David López González
 */
public class EnviaMensajeChuwiki
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
        props.setProperty("mail.smtp.user", "davidlg.dam@gmail.com");

        // Si requiere o no usuario y password para conectarse.
        props.setProperty("mail.smtp.auth", "true");

        //Session session = Session.getDefaultInstance(props);
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication("davidlg.dam@gmail.com", "contrasenya");
            }
        });
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
        //message.setFrom(new InternetAddress("davidlg.dam@gmail.com"));
        // A quien va dirigido
        //message.addRecipient(Message.RecipientType.TO, new InternetAddress("davidlghellin@gmail.com"));
        message.setSubject("Asunto del mensaje");
        message.setText("Texto del mensaje");

        /*
        Construir un mensaje complejo con adjuntos

        Si queremos construir un mensaje complejo con adjuntos, por ejemplo, 
        un texto con una imagen adjunta, debemos construir cada una de las 
        partes y juntarlas todas luego en un solo mensaje.
        Primero construimos la parte de texto
         */
        BodyPart texto = new MimeBodyPart();

        // Texto del mensaje
        texto.setText("Mensaje desde java");
        //Luego construimos la parte del adjunto con la imagen. Suponemos que 
        //la tenemos en un fichero
        BodyPart adjunto = new MimeBodyPart();

        // Cargamos la imagen
        adjunto.setDataHandler(new DataHandler(new FileDataSource("/home/wizord/Imágenes/gif/taylor.gif")));

        // Opcional. De esta forma transmitimos al receptor el nombre original del
        // fichero de imagen
        adjunto.setFileName("taylorNombre.gif");
        //Ahora juntamos ambas en una sola parte que luego debemos añadir al mensaje
        MimeMultipart multiParte = new MimeMultipart();
        multiParte.addBodyPart(texto);
        multiParte.addBodyPart(adjunto);
        //Finalmente construimos el mensaje, le ponemos este MimeMultiPart como 
        //contenido y rellenamos el resto de campos from, to y subject.
        message = new MimeMessage(session);

        // Se rellena el From
        message.setFrom(new InternetAddress("davidlg.dam@gmail.com"));

        // Se rellenan los destinatarios
        message.addRecipient(Message.RecipientType.TO, new InternetAddress("davidlghellin@gmail.com"));
        // message.setSender(new InternetAddress("davidlghellin@gmail.com"));

        // Se rellena el asunto
        message.setSubject("Hola dav");

        // Se mete el texto y la foto adjunta.
        message.setContent(multiParte);
        /*Enviar el mensaje

        Una vez construido el mensaje -el simple o el compuesto con adjunto- 
        lo enviamos. Para ello necesitamos una instancia de la clase Transport. 
        Se hace de la siguiente manera
         */
        
        /* Transport t = session.getTransport("smtp");
        // Aqui usuario y password de gmail
        String pass = "aprenderesgratis";//UtilsCorreo.leePassword();
        t.connect("davidlg.dam@gmail.com", pass);
        //enviamos mensaje
        t.sendMessage(message, message.getAllRecipients());
        t.close();*/
        
        Transport.send(message);
    }
}
