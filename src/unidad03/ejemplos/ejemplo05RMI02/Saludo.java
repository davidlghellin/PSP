package unidad03.ejemplos.ejemplo05RMI02;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David López González
 */
public class Saludo extends UnicastRemoteObject implements SaludoInterface
{
    /**
     * Constructor que lanza la excepción obligado, y necesitamos hacer el super
     * @throws RemoteException 
     */
    public Saludo() throws RemoteException{super();}

    @Override
    public String saludar() throws RemoteException
    {
        return "Hola_";
    }

    @Override
    public String despedirse() throws RemoteException
    {
        return "Adios";
    }

    public static void main(String[] args) throws InterruptedException, RemoteException
    {
        // Instanciamos donde registraremos los nombres asociados con el puerto
        Registry registroNombres = LocateRegistry.createRegistry(12345);
        SaludoInterface s = new Saludo();
        try
        {
            Naming.rebind("//localhost:12345/saludo1", s);
        } catch (MalformedURLException ex)
        {
            Logger.getLogger(Saludo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        while (true)
            Thread.sleep(1000);        
    }
}
