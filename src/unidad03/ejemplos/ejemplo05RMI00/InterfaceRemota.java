package unidad03.ejemplos.ejemplo05RMI00;

import java.rmi.*;

/**
 *
 * @author fran
 */
//Creamos la interface con los métodos que queremos llamar remotamente
//Todos los parámetros y valores devueltos de estos métodos deben ser tipos 
//primitivos de java o bien clases que implementen la interface Serializable de java
public interface InterfaceRemota extends Remote
{
    public int suma(int a, int b) throws RemoteException;
}
