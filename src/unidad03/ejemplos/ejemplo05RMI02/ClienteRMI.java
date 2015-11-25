package unidad03.ejemplos.ejemplo05RMI02;

import unidad03.ejemplos.ejemplo05RMI01.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author David López González
 */
public class ClienteRMI
{
    public static void main(String[] args) throws RemoteException, NotBoundException
    {
        SaludoInterface s=null;
        Registry reg= LocateRegistry.getRegistry("127.0.0.1", 12345);
        s=(SaludoInterface)reg.lookup("saludo1");
        if(s!=null)
        {
            System.out.println("salida:::"+s.despedirse());
        }
    }
}
