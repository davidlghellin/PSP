package unidad05.ejercicios;

import java.io.File;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import javax.swing.JFileChooser;

/**
 *
 * @author David López González
 */
public class Ejer05
{

    public  static KeyPair crearPar() throws NoSuchAlgorithmException
    {
        //objeto generador, con el tipo de algoritmo
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");

        //Inicialización del generador, semilla 
        //tipo de algorimo para generar dicha semilla
        SecureRandom numero = SecureRandom.getInstance("SHA1PRNG");
        //le decimos el tamaño y la semilla
        keyGen.initialize(1024, numero);

        //Creación de las claves, tenemos la pareja en par
        KeyPair parClaves = keyGen.generateKeyPair();
        return parClaves;
    }

    public  static PrivateKey generarPrivada(KeyPair parClaves)
    {
        return parClaves.getPrivate();
    }

    public  static PublicKey generarPublica(KeyPair parClaves)
    {
        return parClaves.getPublic();
    }

    public  static byte[] firmar(PrivateKey privateKey, String mensaje) throws InvalidKeyException, SignatureException, NoSuchAlgorithmException
    {
        //Firma con clave privada,necesitamos la signature, decimos el algoritmo
        Signature signaturePrivada = Signature.getInstance("SHA1withDSA");
        //cargamos la privada
        signaturePrivada.initSign(privateKey);
        //actualizamos, podemos pasar cualquier cosa
        signaturePrivada.update(mensaje.getBytes());

        //una vez actualizado aplicamos sing, nos devuelve la firma
        byte[] firma = signaturePrivada.sign();
        return firma;
    }

    public static boolean comprobarFirma(PublicKey publicKey, String mensaje, byte[] firma) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException
    {
        //comprobar necesito otro objeto signature y comprobar            
        Signature signaturePublica = Signature.getInstance("SHA1withDSA");
        // cargamos la publica
        signaturePublica.initVerify(publicKey);
        // actualizamos los datos a firmar
        signaturePublica.update(mensaje.getBytes());

        // Comprobamos si es correcto
        return signaturePublica.verify(firma);
    }
    
    public static File abrirFichero()
    {
        JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(null);
        return fc.getSelectedFile();
    }
    
    public static void main(String[] args) throws NoSuchAlgorithmException
    {
        KeyPair key =crearPar();
        PublicKey publicKey=generarPublica(key);
        PrivateKey privateKey=generarPrivada(key);
        File fichero=abrirFichero();
    }
}
