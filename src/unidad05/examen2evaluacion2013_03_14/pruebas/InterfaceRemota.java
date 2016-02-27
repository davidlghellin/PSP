package unidad05.examen2evaluacion2013_03_14.pruebas;

import unidad05.examen2evaluacion2013_03_14.*;
import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;

/**
 *
 * @author David López González
 */
public interface InterfaceRemota extends Remote
{
    public void sendClavePublica(PublicKey pk) throws RemoteException;// Enviará al servidor la clave pública del cliente.
}
