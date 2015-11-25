package unidad03.ejemplos.ejemplo05RMI01;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author David López González
 */
public interface SaludoInterface extends Remote
{
    public String saludar() throws RemoteException; 
    public String despedirse() throws RemoteException;    
}
