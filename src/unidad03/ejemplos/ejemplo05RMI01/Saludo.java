package unidad03.ejemplos.ejemplo05RMI01;

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
public class Saludo implements SaludoInterface
{

    private String nombre;

    public Saludo(String nombre){this.nombre = nombre;}
    @Override
    public String saludar() throws RemoteException{return "Hola " + nombre;}
    @Override
    public String despedirse() throws RemoteException{return "Adios " + nombre;}

    public static void main(String[] args) throws InterruptedException
    {
        Registry registroNombres = null;
        try
        {
            registroNombres = LocateRegistry.createRegistry(5555);
        } catch (RemoteException ex){Logger.getLogger(Saludo.class.getName()).log(Level.SEVERE, null, ex);}
        
        SaludoInterface miSaludo = new Saludo("1");
        try
        {
            registroNombres.rebind("saludo1", (SaludoInterface) UnicastRemoteObject.exportObject(miSaludo, 0));
        } catch (RemoteException ex){Logger.getLogger(Saludo.class.getName()).log(Level.SEVERE, null, ex);}
        
        SaludoInterface miSaludo2 = new Saludo("2");
        try
        {
            registroNombres.rebind("saludo2", (SaludoInterface) UnicastRemoteObject.exportObject(miSaludo2, 0));
        } catch (RemoteException ex){Logger.getLogger(Saludo.class.getName()).log(Level.SEVERE, null, ex);}

        while (true)
            Thread.sleep(1000);
    }
}
