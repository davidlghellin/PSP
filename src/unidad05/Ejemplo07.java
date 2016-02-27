package unidad05;

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

/**
 *
 * @author David López González
 * @author fran
 */
public class Ejemplo07
{

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

    public static byte[] firmar(PrivateKey privateKey,String mensaje) throws InvalidKeyException, SignatureException, NoSuchAlgorithmException
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

    public static void main(String[] args) throws FileNotFoundException, IOException, InvalidKeySpecException
    {
        try
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
            // obtenermos la privada
            PrivateKey privateKey = parClaves.getPrivate();
            // obtenermos la publica
            PublicKey publicKey = parClaves.getPublic();

            //Firma con clave privada,necesitamos la signature, decimos el algoritmo
            Signature signaturePrivada = Signature.getInstance("SHA1withDSA");
            //cargamos la privada
            signaturePrivada.initSign(privateKey);
            String mensaje = "Este es un mensaje firmado";
            //actualizamos, podemos pasar cualquier cosa
            signaturePrivada.update(mensaje.getBytes());

            //una vez actualizado aplicamos sing, nos devuelve la firma
            byte[] firma = signaturePrivada.sign();

            System.out.println("Firma: " + firma.toString());
            System.out.println("Firma hexadecimal: " + Hexadecimal(firma));
            //

            //comprobar necesito otro objeto signature y comprobar            
            Signature signaturePublica = Signature.getInstance("SHA1withDSA");
            // cargamos la publica
            signaturePublica.initVerify(publicKey);
            // actualizamos los datos a firmar
            signaturePublica.update(mensaje.getBytes());

            // Comprobamos si es correcto
            System.out.println(signaturePublica.verify(firma));

            System.out.println("---------------------");
            System.out.println("---------------------");

            //Inicialización del generador, semilla             
            numero = SecureRandom.getInstance("SHA1PRNG");
            //le decimos el tamaño y la semilla
            keyGen.initialize(1024, numero);

            //Creación de las claves, tenemos la pareja en par
            KeyPair parClaves2 = keyGen.generateKeyPair();
            // obtenermos la privada
            PrivateKey privateKey2 = parClaves2.getPrivate();
            // obtenermos la publica
            PublicKey publicKey2 = parClaves2.getPublic();
            //Firma con clave privada,necesitamos la signature, decimos el algoritmo
            Signature signaPubli2 = Signature.getInstance("SHA1withDSA");

            //cargamos la publica
            signaPubli2.initVerify(publicKey2);
            //actualizamos, podemos pasar cualquier cosa
            signaPubli2.update(mensaje.getBytes());

            //privada
            Signature signaPri2 = Signature.getInstance("SHA1withDSA");
            signaPri2.initSign(privateKey2);
            signaPri2.update(mensaje.getBytes());
            byte[] firma2 = signaPri2.sign();

            System.out.println("Firma: " + firma2.toString());
            System.out.println("Firma hexadecimal: " + Hexadecimal(firma2));

            System.out.println("Correcto: " + signaPubli2.verify(firma2));

            System.out.println("-----------------");
            System.out.println("-----ficheros----");
            System.out.println("-----------------");
            //privada
            PKCS8EncodedKeySpec pk8 = new PKCS8EncodedKeySpec(privateKey2.getEncoded());
            FileOutputStream outpr = new FileOutputStream("/home/wizord/privada.txt");
            outpr.write(pk8.getEncoded());
            outpr.close();
            //publica
            X509EncodedKeySpec pux = new X509EncodedKeySpec(publicKey2.getEncoded());
            FileOutputStream outpu = new FileOutputStream("/home/wizord/public.txt");
            outpu.write(pux.getEncoded());
            outpu.close();

            //recuperamos los datos
            //private
            FileInputStream inpriv = new FileInputStream("/home/wizord/privada.txt");
            byte[] bufferPriv = new byte[inpriv.available()];
            inpriv.read(bufferPriv);
            inpriv.close();

            PKCS8EncodedKeySpec clavePrivadaSpec = new PKCS8EncodedKeySpec(bufferPriv);
            KeyFactory keydSA = KeyFactory.getInstance("DSA");
            PrivateKey clavePrivadaFichero = keydSA.generatePrivate(clavePrivadaSpec);

            //public
            FileInputStream inpublic = new FileInputStream("/home/wizord/public.txt");
            byte[] bufferPublic = new byte[inpublic.available()];
            inpublic.read(bufferPublic);
            inpublic.close();

            X509EncodedKeySpec clavePublicSpec = new X509EncodedKeySpec(bufferPublic);
            PublicKey clavePublicaFicheros = keydSA.generatePublic(clavePublicSpec);

            //signature privada
            Signature signaFicherosPrivada = Signature.getInstance("DSA");
            signaFicherosPrivada.initSign(clavePrivadaFichero);
            signaFicherosPrivada.update(mensaje.getBytes());
            byte[] firma2Ficheros = signaFicherosPrivada.sign();

            //signature publica
            Signature signaFicherosPublic = Signature.getInstance("DSA");
            signaFicherosPublic.initVerify(clavePublicaFicheros);
            signaFicherosPublic.update((mensaje).getBytes());

            //comprobacion
            System.out.println(signaFicherosPublic.verify(firma2Ficheros));
            
            
            //pruebas métodos
            System.out.println("\n\n\n\n");
            KeyPair k2 = crearPar();
            PrivateKey pk2=generarPrivada(parClaves);
            PublicKey pu2=generarPublica(parClaves);
            String sms="este es nuev mensae";
            byte[] array=firmar(privateKey,sms);
            System.out.println(comprobarFirma(pu2, sms,array));

        } catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        } catch (InvalidKeyException ex)
        {
            Logger.getLogger(Ejemplo07.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SignatureException ex)
        {
            Logger.getLogger(Ejemplo07.class.getName()).log(Level.SEVERE, null, ex);
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
