package unidad05;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.swing.JFileChooser;

/**
 *
 * @author David López González
 * @author Fran
 */
public class Ejemplo13CifraFlujo
{

    public static void main(String[] args)
    {
        try
        {
            JFileChooser jf = new JFileChooser();
            jf.showOpenDialog(null);
            //Generamos clave secreta
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init(128);
            SecretKey clavesecreta = kg.generateKey();

            //Se define el objeto a Cipher
            Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
            c.init(Cipher.ENCRYPT_MODE, clavesecreta);

            //Fichero a cifrar
            FileInputStream filein = new FileInputStream(jf.getSelectedFile());
            //Objeto CipherOutputStream donde se almacenará el fichero cifrado
            jf.showSaveDialog(null);
            CipherOutputStream out = new CipherOutputStream(new FileOutputStream(jf.getSelectedFile()), c);
            int tbloque = c.getBlockSize();
            System.out.println("el tamaño de bloque es " + tbloque);
            byte[] datos = new byte[tbloque]; //bloque de bytes

            //Leemos blques de bytes del fichero y lo vamos escribiendo ya cifrado en el fichero de salida
            int i = filein.read(datos);
            while (i != -1)
            { //fin de fichero
                out.write(datos, 0, i);
                i = filein.read(datos);
            }
            out.flush();
            out.close();
            filein.close();
            System.out.println("Fichero cifrado con clave secreta");

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
