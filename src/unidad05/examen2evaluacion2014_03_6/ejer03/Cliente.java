package unidad05.examen2evaluacion2014_03_6.ejer03;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 *
 * @author aRojo24 <-- git
 * @author David López González
 */
public class Cliente
{

    static InterfaceRemota or = null;

    public void Conectar()
    {
        try
        {
            Registry reg = LocateRegistry.getRegistry("localhost", 5555);
            or = (InterfaceRemota) reg.lookup("Operaciones");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException
    {
        try
        {
            Cliente cl = new Cliente();
            cl.Conectar();

            //Creación de clave secreta AES
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init(128);
            SecretKey secretKey = kg.generateKey();
            //Se encripta la clave secreta con la clave RSA pública
            Cipher c = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            c.init(Cipher.WRAP_MODE, or.getClavePublica());
            byte claveenvuelta[] = c.wrap(secretKey);

            or.sendClaveSecreta(claveenvuelta);

            c = Cipher.getInstance("AES/ECB/PKCS5Padding");
            c.init(Cipher.ENCRYPT_MODE, secretKey);
            System.out.println("Escriba un mensaje: ");
            byte textoPlano[] = new Scanner(System.in).nextLine().getBytes();
            byte textoCifrado[] = c.doFinal(textoPlano);

            System.out.println("Mensaje cifrado: " + new String(textoCifrado));

            or.sendMensaje(textoCifrado);

        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | RemoteException ex)
        {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
