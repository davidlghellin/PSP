package unidad03.ejemplos.ejemplo05RMI00;

/**
 *
 * @author fran
 */
import java.rmi.registry.*;
import java.rmi.*;

public class ObjetoCliente
{

    public static void main(String[] args)
    {
        InterfaceRemota or = null;
        try
        {
            System.out.println("Localizando el registro de objetos remotos ...");
            Registry reg = LocateRegistry.getRegistry("localhost", 5555);
            System.out.println("Obteniendo el stub del objeto remoto ...");
            or = (InterfaceRemota) reg.lookup("Operaciones");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        if (or != null)
        {
            System.out.println("Realizando operaciones ...");
            try
            {
                System.out.println("5 + 4=" + or.suma(5, 4));
            } catch (RemoteException e)
            {
                e.printStackTrace();
            }
        }
        System.out.println("Fin");
    }
}
