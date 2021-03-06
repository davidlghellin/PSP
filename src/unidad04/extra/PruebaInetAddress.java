package unidad04.extra;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * http://javax.mty.itesm.mx/redes2/material/Leccion2.htm
 * @author David López González
 */
public class PruebaInetAddress
{

    public static void main(String args[])
    {

        // Análisis del parámetro dado en la línea de comandos
        String argumento;
        InetAddress direccion = null;
        try
        {
            argumento = args[0];
        } catch (ArrayIndexOutOfBoundsException e)
        {
            argumento = null;
        }

        // Obtención de la dirección IP, de la máquina dada como parámetro o de la máquina local
        try
        {
            if (argumento != null)
            {
                direccion = InetAddress.getByName(argumento);
            }
            else
            {
                direccion = InetAddress.getLocalHost();
            }
        } catch (UnknownHostException e)
        {
            System.out.println(e.getMessage());
            System.exit(0);
        }

        // Obtención de datos de la dirección IP con métodos de la clase InetAddress
        System.out.println(direccion.getHostAddress());
        System.out.println(direccion.getHostName());
        System.out.println(direccion.toString());
        byte[] dir = direccion.getAddress();
        for (int i = 0; i < dir.length; i++)
        {
            int b = dir[i] < 0 ? dir[i] + 256 : dir[i];
            System.out.print(b + ".");
        }
        System.out.println();
        try
        {
            InetAddress[] direcciones = InetAddress.getAllByName(direccion.getHostName());
            for (int i = 0; i < direcciones.length; i++)
            {
                System.out.println(direcciones[i].toString());
            }
        } catch (UnknownHostException e)
        {
        }
    }
}
