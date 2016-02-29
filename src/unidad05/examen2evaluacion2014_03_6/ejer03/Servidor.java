package unidad05.examen2evaluacion2014_03_6.ejer03;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author aRojo24 <-- git
 * @author David López González
 */
public class Servidor implements InterfaceRemota
{

    static PublicKey pbk;
    static PrivateKey prk;
    static Cipher c2;
    Key clavedesenvuelta;

    @Override
    public void sendClaveSecreta(byte[] claveenvuelta)
    {
        try
        {
            //desenvolver y guardar en variable del servidor
            c2 = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            c2.init(Cipher.UNWRAP_MODE, prk);
            clavedesenvuelta = c2.unwrap(claveenvuelta, "AES", Cipher.SECRET_KEY);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException ex)
        {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void sendMensaje(byte[] textoCifrado)
    {
        try
        {
            //Desciframos el texto con la clave desenvuelta
            System.out.println("Mensaje cirfrado: " + new String(textoCifrado));
            c2 = Cipher.getInstance("AES/ECB/PKCS5Padding");
            c2.init(Cipher.DECRYPT_MODE, clavedesenvuelta);
            byte desencriptado[] = c2.doFinal(textoCifrado);
            System.out.println("Mensaje descifrado: " + new String(desencriptado));

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex)
        {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public PublicKey getClavePublica()
    {
        return pbk;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException
    {

        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(1024);
        KeyPair par = keyGen.generateKeyPair();
        prk = par.getPrivate();
        pbk = par.getPublic();

        Registry reg = null;
        System.out.println("Creando conexion remota");
        try
        {
            reg = LocateRegistry.createRegistry(5555);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("Creando objeto servidor");
        Servidor s = new Servidor();
        try
        {
            reg.rebind("Operaciones", (InterfaceRemota) UnicastRemoteObject.exportObject(s, 0));
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        while (true)
        {
            try
            {
                Thread.sleep(1000);
            } catch (InterruptedException ex)
            {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
