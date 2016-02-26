package unidad05.internet;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author http://blog.gotencool.com/2011/08/cifradodecifrado-con-aes-y-llave.html
 * @author David López González
 */
public class Probador
{

    public static void main(String[] args)
    {
        // llaveSimetrica es un String de largo múltiplo de 8
        // en este caso si es de largo 32 nos permite AES-256 (32 * 8)
        String llaveSimetrica = "holamundocruel12holamundocruel12";

        SecretKeySpec key = new SecretKeySpec(llaveSimetrica.getBytes(), "AES");
        Cipher cipher;
        try
        {
            cipher = Cipher.getInstance("AES");

            //Comienzo a encriptar
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] campoCifrado = cipher.doFinal("mensaje_secreto".getBytes());
            /*
    * TODO: Representar los bytes como string vía base64, así será
    * humanamente leíble. La otra opción es expresar como hexadecimal
    * 
    * En este caso lo imprimo en pantalla como bytes.
             */
            System.out.println(new String(campoCifrado));

            //Comienzo a desencriptar
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] datosDecifrados = cipher.doFinal(campoCifrado);
            String mensaje_original = new String(datosDecifrados);
            System.out.println(mensaje_original);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
