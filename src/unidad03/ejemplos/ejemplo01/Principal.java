package unidad03.ejemplos.ejemplo01;

import java.net.UnknownHostException;

/**
 *
 * @author David López González
 */
public class Principal
{
    public static void main(String[] args) throws UnknownHostException
    {
        Conector c=new Conector();
        c.iniciar();
    }
}
