package unidad05;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.*;

/**
 *
 * @author David López González
 * @author fran
 */
public class Ejemplo04
{

    public static void main(String[] args)
    {
        MessageDigest md;
        try
        {
            md = MessageDigest.getInstance("MD5", Security.getProvider("SUN"));      //funcionaria si  lo quitamos
            //md = MessageDigest.getInstance("MD5");   
            String texto = "Esto es un texto plano.";   //string a capturar
            byte dataBytes[] = texto.getBytes();        //obtenemos los bytes
            md.update(dataBytes);                       //actualizamos el mesagediges
            byte resume[] = md.digest();                //Calcula el resumen
            // Información
            System.out.println("Original: " + texto);
            System.out.println("Número de Bytes: " + md.getDigestLength());
            System.out.println("Algoritmo: " + md.getAlgorithm());
            System.out.println("Mensaje resumen: " + new String(resume));
            System.out.println("Hexadecimal: " + Hexadecimal(resume));
            System.out.println(MessageDigest.isEqual(dataBytes, ("esto es un texto plano.").getBytes()));

            // Proveedor
            Provider proveedor = md.getProvider();
            System.out.println("Proveedor: " + proveedor.toString());
        } catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
    }

    static String Hexadecimal(byte[] resumen)
    {
        String hex = "";
        for (int i = 0; i < resumen.length; i++)
        {
            String h = Integer.toHexString(resumen[i] & 0xFF);
            if (h.length() == 1)
            {
                hex += "0";
            }
            hex += h;
        }
        return hex.toUpperCase();
    }
}
