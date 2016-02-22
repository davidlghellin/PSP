package unidad04.correo;

import java.security.Security;
import java.util.Properties;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

/**
 *
 * @author fran
 */
public class EnviaMensajje
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
        //     props.setProperty("mail.smtp.ssl.enable", "true");
        //Establece el puerto
        props.setProperty("mail.smtp.port", "25");
        props.setProperty("mail.transport.protocol", "smtp");
        //Activa la autenticación, mi servidor requiere autenticación
        props.setProperty("mail.smtp.auth", "true");
    }

    public static void main(String[] args)
    {
        try
        {
            String smtpHost = "smtp.netilia.com";
            String from = "moodle@netilia.com", to = "davidlghellin@gmail.com";
            Properties props = System.getProperties();
            setPropiedadesSeguras(props);
            Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

            props.put("mail.smtp.host", smtpHost);

            Session sesion = Session.getDefaultInstance(props);
            sesion.setDebug(true);
            Message mensaje = new MimeMessage(sesion);

            mensaje.setSubject("Hola Mundo");
            mensaje.setFrom(new InternetAddress(from));

            mensaje.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));

            // mensaje.addRecipients(Message.RecipientType.TO, direcciones);
            mensaje.setText("Este es el cuerpo del mensaje");

            Transport t = sesion.getTransport();

            String pass = leePassword();
            t.connect(smtpHost,from, pass);
            t.sendMessage(mensaje, mensaje.getAllRecipients());
            t.close();

        } catch (MessagingException ex)
        {
            Logger.getLogger(EnviaMensajje.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
