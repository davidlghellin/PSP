package unidad05.examen2evaluacion2014_03_6.ejer03;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.security.PublicKey;

/**
 * @author aRojo24 <-- git
 * @author David López González
 */
public interface InterfaceRemota extends Remote
{
    public PublicKey getClavePublica() throws RemoteException;
    public void sendClaveSecreta(byte[] claveenvuelta) throws RemoteException;
    public void sendMensaje(byte[] textoCifrado) throws RemoteException;
}
