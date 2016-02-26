package unidad05.examen2evaluacion2013_03_14;

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
    public Cipher getClaveSecreta(PublicKey pk) throws RemoteException;// Enviará al cliente la clave secreta cifrada por servidor con la clave pública del cliente.
    public String getWeb() throws RemoteException;// El cliente enviará la URL cifrada con la clave simétrica y el servidor devolverá el resultado, es decir, el contenido de la web, también cifrada. El cliente debe descifrarlo y mostrarlo por pantalla.
    public String conecta() throws RemoteException;// El cliente enviará el host, el usuario y la contraseña de un sitio FTP, todo cifrado, y el servidor conectará y devolverá el resultado también cifrado.
    public String getList() throws RemoteException;// El servidor devolverá la lista de archivos (nombre y tipo) cifrados al cliente. El cliente debe descifrarlo y mostrarlo por pantalla.
    public File getFile() throws RemoteException;// El servidor devolverá el archivo cifrado al cliente. El cliente debe descifrarlo y mostrarlo por pantalla.
    public void desconecta() throws RemoteException;// El servidor cerrará la conexión FTP.
    public void sendMensaje() throws RemoteException; //Enviará al servidor el mensaje cifrado con la clave secreta. El servidor debe descifrarlo y mostrarlo por pantalla.
}
