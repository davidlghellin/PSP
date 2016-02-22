package unidad04.correo;

import java.security.Security;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
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
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

/**
 *
 * @author Fran
 */
public class EnviarArchivoAdjunto
{

    static String leePassword()
    {
        JPasswordField pf = new JPasswordField();
        String password = null;
        int okCxl = JOptionPane.showConfirmDialog(null, pf, "Introduce la contraseña: ", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (okCxl == JOptionPane.OK_OPTION)
        {
            password = new String(pf.getPassword());
        }
        return password;
    }

    static void setPropiedadesSeguras(Properties props)
    {
        //Activa SSL para smtp
        props.setProperty("mail.smtp.ssl.enable", "true");
        //Establece el puerto
        props.setProperty("mail.smtp.port", "465");

        props.setProperty("mail.transport.protocol", "smtps");
        //Activa la autenticación, mi servidor requiere autenticación
        props.setProperty("mail.smtps.auth", "true");
    }

    public static void main(String[] args)
    {
        try
        {
            String smtpHost = "smtp.googlemail.com";
            String from = "davidlghellin@gmail.com", to = "davidlghellin@hotmail.com";
            Properties props = System.getProperties();
            setPropiedadesSeguras(props);
            Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

            props.put("mail.smtps.host", smtpHost);

            Session sesion = Session.getDefaultInstance(props);
            sesion.setDebug(true);
            Message mensaje = new MimeMessage(sesion);
            mensaje.setSubject("Archivo adjunto");
            mensaje.setFrom(new InternetAddress(from));

            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Create the message part 
            BodyPart messageBodyPart = new MimeBodyPart();
            // Rellena el mensaje
            messageBodyPart.setText("Aquí llevas un archivo adjunto");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            // La segunda es el archivo adjunto
            JFileChooser jf = new JFileChooser();
            jf.showOpenDialog(jf);
            String filename = jf.getSelectedFile().getAbsolutePath();

            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(jf.getSelectedFile().getName());
            multipart.addBodyPart(messageBodyPart);
            // Establecer el multipart
            mensaje.setContent(multipart);
            // Enviar el message

            Transport t = sesion.getTransport();

            String pass = leePassword();
            //t.connect(from, pass);
            t.connect(smtpHost, from, pass);
            t.sendMessage(mensaje, mensaje.getAllRecipients());
            t.close();

        } catch (MessagingException ex)
        {
            Logger.getLogger(EnviaMensajje.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
