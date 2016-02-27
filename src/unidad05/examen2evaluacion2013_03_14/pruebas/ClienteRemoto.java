package unidad05.examen2evaluacion2013_03_14.pruebas;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

/**
 *
 * @author David López González
 */
public class ClienteRemoto
{
    public static void main(String[] args) throws RemoteException, NoSuchAlgorithmException
    {
        InterfaceRemota or = null;
        try
        {
            System.out.println("Localizando el registro de objetos remotos ...");
            Registry reg = LocateRegistry.getRegistry("localhost", 5555);
            System.out.println("Obteniendo el stub del objeto remoto ...");
            or = (InterfaceRemota) reg.lookup("Claves");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        if (or != null)
        {
            System.out.println("Realizando operaciones ...");
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
            or.sendClavePublica(publicKey);
        }
        System.out.println("Fin");
    }
}
