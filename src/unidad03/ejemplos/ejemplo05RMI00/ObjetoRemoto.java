package unidad03.ejemplos.ejemplo05RMI00;

/**
 *
 * @author fran
 */
import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ObjetoRemoto implements InterfaceRemota
{
    
    public int suma(int a, int b) throws RemoteException
    {
        System.out.println("sumando " + a + " + " + b + "...");
        return a + b;
    }

    public static void main(String[] args)
    {
        System.out.println("Creando el registro de objetos remotos ....");
        Registry reg = null;
        try
        {
            reg = LocateRegistry.createRegistry(5555);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("Creando el objeto servidor e inscribiéndolo en el registro ...");
        ObjetoRemoto or = new ObjetoRemoto();
        try
        {
            reg.rebind("Operaciones", (InterfaceRemota) UnicastRemoteObject.exportObject(or, 0));
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        while (true)
        {
            try
            {
                Thread.sleep(1000);
            } catch (InterruptedException ex)
            {
                Logger.getLogger(ObjetoRemoto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
