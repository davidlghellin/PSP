package unidad05;

/**
 *
 * @author David López González
 * @author fran
 */
import java.io.*;
import java.security.*;
import java.security.spec.*;
//Recuperar una clave de un fichero

public class Ejemplo08
{

    public static void main(String[] args)
    {
        try
        {
            FileInputStream inpriv = new FileInputStream("/home/wizord/clave.privada");
            byte[] bufferPriv = new byte[inpriv.available()];
            inpriv.read(bufferPriv);
            inpriv.close();

            PKCS8EncodedKeySpec clavePrivadaSpec = new PKCS8EncodedKeySpec(bufferPriv);
            KeyFactory keydSA = KeyFactory.getInstance("DSA");
            PrivateKey clavePrivada = keydSA.generatePrivate(clavePrivadaSpec);
            

        } catch (Exception e){}
    }
}
