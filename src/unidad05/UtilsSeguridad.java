package unidad05;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.swing.JFileChooser;

/**
 *
 * @author David López González
 */
public class UtilsSeguridad
{

    //fileChooser
    public static File guardarFichero()
    {
        JFileChooser fc = new JFileChooser();
        fc.showSaveDialog(null);
        return fc.getSelectedFile();
    }

    public static File abrirFichero()
    {
        JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(null);
        return fc.getSelectedFile();
    }

    //md5
    public static String Hexadecimal(byte[] resumen)
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

    public static boolean comprobarMD5(String md5) throws FileNotFoundException, IOException, NoSuchAlgorithmException
    {
        File file = abrirFichero();
        FileInputStream in = new FileInputStream(file.toPath().toString());
        byte[] buffer = new byte[in.available()];
        in.read(buffer);
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(buffer);
        byte[] resume = md.digest();

        //no funciona el isEqual correcto
        //if (md.isEqual(resume, md5.getBytes()))
        return md5.equalsIgnoreCase(Hexadecimal(resume));
    }

    // Seguridad
    public static KeyPair crearPar() throws NoSuchAlgorithmException
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

    public static PrivateKey generarPrivada(KeyPair parClaves)
    {
        return parClaves.getPrivate();
    }

    public static PublicKey generarPublica(KeyPair parClaves)
    {
        return parClaves.getPublic();
    }

    public static byte[] firmar(PrivateKey privateKey, String mensaje) throws InvalidKeyException, SignatureException, NoSuchAlgorithmException
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

    public static void guardarPrivadaFichero(PrivateKey privateKey) throws FileNotFoundException, IOException
    {
        File rutaFichero = guardarFichero();
        guardarPrivadaFichero(privateKey, rutaFichero.toString());
    }

    public static void guardarPrivadaFichero(PrivateKey privateKey, String rutaFichero) throws FileNotFoundException, IOException
    {
        PKCS8EncodedKeySpec pk8 = new PKCS8EncodedKeySpec(privateKey.getEncoded());
        FileOutputStream outPrivate = new FileOutputStream(rutaFichero);
        outPrivate.write(pk8.getEncoded());
        outPrivate.close();
    }

    public static void guardarPublicaFichero(PublicKey publicKey) throws FileNotFoundException, IOException
    {
        File rutaFichero = guardarFichero();
        guardarPublicaFichero(publicKey, rutaFichero.toString());
    }

    public static void guardarPublicaFichero(PublicKey publicKey, String rutaFichero) throws FileNotFoundException, IOException
    {
        X509EncodedKeySpec pux = new X509EncodedKeySpec(publicKey.getEncoded());
        FileOutputStream outPublic = new FileOutputStream(rutaFichero);
        outPublic.write(pux.getEncoded());
        outPublic.close();
    }

    public static PrivateKey recuperarPrivadaFichero() throws IOException, FileNotFoundException, NoSuchAlgorithmException, InvalidKeySpecException
    {
        File file = abrirFichero();
        return recuperarPrivadaFichero(file.toString());
    }

    public static PrivateKey recuperarPrivadaFichero(String rutaFichero) throws FileNotFoundException, IOException, NoSuchAlgorithmException, InvalidKeySpecException
    {
        FileInputStream inpriv = new FileInputStream(rutaFichero);
        byte[] bufferPriv = new byte[inpriv.available()];
        inpriv.read(bufferPriv);
        inpriv.close();

        PKCS8EncodedKeySpec clavePrivadaSpec = new PKCS8EncodedKeySpec(bufferPriv);
        KeyFactory keydSA = KeyFactory.getInstance("DSA");
        return keydSA.generatePrivate(clavePrivadaSpec);
    }

    public static PublicKey recuperarPublicaFichero() throws FileNotFoundException, IOException, NoSuchAlgorithmException, InvalidKeySpecException
    {
        File file = abrirFichero();
        return recuperarPublicaFichero(file.toString());
    }

    public static PublicKey recuperarPublicaFichero(String rutaFichero) throws FileNotFoundException, IOException, NoSuchAlgorithmException, InvalidKeySpecException
    {
        FileInputStream inpublic = new FileInputStream(rutaFichero);
        byte[] bufferPublic = new byte[inpublic.available()];
        inpublic.read(bufferPublic);
        inpublic.close();

        X509EncodedKeySpec clavePublicSpec = new X509EncodedKeySpec(bufferPublic);
        KeyFactory keydSA = KeyFactory.getInstance("DSA");
        return keydSA.generatePublic(clavePublicSpec);
    }

    ///////////////////////////
    public static KeyPair crearParRSA() throws NoSuchAlgorithmException
    {
        // Obtenemos el tipo de algoritmo
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        // Inicialización del generador
        keyGen.initialize(1024);
        // Creación de las claves pública y privada
        return keyGen.generateKeyPair();
    }

    public static SecretKey crearClaveSecretaAES() throws NoSuchAlgorithmException
    {
        // Creación de clave secreta AES
        KeyGenerator kg = KeyGenerator.getInstance("AES");
        // Inicializamos el generador de claves con uno de los siguientes parámetros
        // 128 o 192 o 256
        kg.init(128);
        return kg.generateKey();
    }

    public static byte[] encriptarClaveSecreta(SecretKey claveSecreta, PublicKey clavePublica) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException
    {
        //Se encripta la clave secreta con la clave RSA pública del receptor
        Cipher c = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        c.init(Cipher.WRAP_MODE, clavePublica);
        //hacemos la envoltura --> envuelve la clave secreta con la pública del receptor
        return c.wrap(claveSecreta);
    }

    public static byte[] cifrarClaveSecreta(SecretKey claveSecreta, String mensaje) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException
    {
        //Ciframos el texto con la clave secreta para enviarlos al receptor
        Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
        //arranco el cipher con la clave secreta
        c.init(Cipher.ENCRYPT_MODE, claveSecreta);
        //obtengo ls bytes
        byte textoPlano[] = mensaje.getBytes();
        //msg cifrado 
        return c.doFinal(textoPlano);
    }

    public static byte[] encriptarMensaje(PublicKey clavePublica, String mensaje) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
    {
        SecretKey secretKey = crearClaveSecretaAES();
        byte[] claveEnvuelta = encriptarClaveSecreta(secretKey, clavePublica);
        return cifrarClaveSecreta(secretKey, mensaje);
    }

    ///////////////////////
    ///  DESCIFRAR       //
    ///////////////////////
    public static Key descifrarClaveSecreta(PrivateKey privateKey, byte claveEnvuelta[]) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException
    {
        // Desciframos la clave secreta con la clave RSA privada
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.UNWRAP_MODE, privateKey);
        return cipher.unwrap(claveEnvuelta, "AES", Cipher.SECRET_KEY);//Key clavedesenvuelta
    }

    public static String descifrarTexto(Key clavedesenvuelta, byte textoCifrado[]) throws IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException
    {
        // Desciframos el texto con la clave desenvuelta
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, clavedesenvuelta);
        byte desencriptado[] = cipher.doFinal(textoCifrado);
        return new String(desencriptado);
    }

    public static String descifrarTextoCifrado2(PrivateKey clavePrivada, byte claveEnvuelta[], byte textoCifrado[]) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException
    {
        Key key = descifrarClaveSecreta(clavePrivada, claveEnvuelta);
        return descifrarTexto(key, textoCifrado);
    }

    public static String descifrarTextoCifrado(PrivateKey clavePrivada, byte claveEnvuelta[], byte textoCifrado[]) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException
    {
        // Desciframos la clave secreta con la clave RSA privada
        // Iniciamos el cipher con el algoritmo
        Cipher c = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        // Inicializamos para desenvolver con la privada
        c.init(Cipher.UNWRAP_MODE, clavePrivada);
        // /Oobtenemos la clave desenvuelta, que usaremos para descifrar el texto
        Key claveDesenvuelta = c.unwrap(claveEnvuelta, "AES", Cipher.SECRET_KEY);

        // Desciframos el texto con la clave desenvuelta
        c = Cipher.getInstance("AES/ECB/PKCS5Padding");
        // Inicializ
        c.init(Cipher.DECRYPT_MODE, claveDesenvuelta);
        byte desencriptado[] = c.doFinal(textoCifrado);
        return new String(desencriptado);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, NoSuchPaddingException, IllegalBlockSizeException, IOException
    {
        System.out.println(comprobarMD5("7CE402353454BFE7DAFAAFC3B5F48DC5"));

    }
}
