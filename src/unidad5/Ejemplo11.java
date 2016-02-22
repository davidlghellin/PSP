package unidad5;

/**
 *
 * @author David López González
 * @author fran
 */
import java.security.*;
import javax.crypto.*;

public class Ejemplo11
{
    public static void main(String[] args)
    {
        try
        {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            //Inicialización del generador

            keyGen.initialize(1024);

            //Creación de las claves pública y privada
            KeyPair par = keyGen.generateKeyPair();
            PrivateKey clavePrivada = par.getPrivate();
            PublicKey clavePublica = par.getPublic();

            //Creación de clave secreta AES
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init(128);
            SecretKey clavesecreta = kg.generateKey();

            //Se encripta la clave secreta con la clave RSA pública del receptor
            Cipher c = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            c.init(Cipher.WRAP_MODE, clavePublica);
            //hacemos la envoltura --> envuelve la clave secreta con la publica del receptor
            byte claveenvuelta[] = c.wrap(clavesecreta);

            //Ciframos el texto con la clave secreta para enviarlos al receptor
            c = Cipher.getInstance("AES/ECB/PKCS5Padding");
            //arranco el cipher
            c.init(Cipher.ENCRYPT_MODE, clavesecreta);
            //obtengo ls bytes
            byte textoPlano[] = "Esto es Texto Plano".getBytes();
            //msg cifrado 
            byte textoCifrado[] = c.doFinal(textoPlano);

            System.out.println("Encriptado: " + new String(textoCifrado));
            System.out.println("");
            
            //Desciframos la clave secreta con la clave RSA privada
            Cipher c2 = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            c2.init(Cipher.UNWRAP_MODE, clavePrivada);
            Key clavedesenvuelta = c2.unwrap(claveenvuelta, "AES", Cipher.SECRET_KEY);

            //Desciframos el texto con la clave desenvuelta
            c2 = Cipher.getInstance("AES/ECB/PKCS5Padding");
            c2.init(Cipher.DECRYPT_MODE, clavedesenvuelta);
            byte desencriptado[] = c2.doFinal(textoCifrado);
            System.out.println("Desencriptado: " + new String(desencriptado));
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
