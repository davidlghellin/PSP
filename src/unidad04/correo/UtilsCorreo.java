package unidad04.correo;

import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

/**
 *
 * @author David López González
 */
public class UtilsCorreo
{

    public static String leePassword()
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

    public static void setPropiedadesSeguras(Properties props)
    {
        //Activa SSL para smtp
        props.setProperty("mail.smtp.ssl.enable", "true");
        //Establece el puerto
        props.setProperty("mail.smtp.port", "465");

        props.setProperty("mail.transport.protocol", "smtps");
        //Activa la autenticación, mi servidor requiere autenticación
        props.setProperty("mail.smtps.auth", "true");
    }
}
