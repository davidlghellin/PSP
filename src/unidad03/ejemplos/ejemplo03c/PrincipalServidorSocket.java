package unidad03.ejemplos.ejemplo03c;

import java.io.IOException;

/**
 *
 * @author David López González
 */
public class PrincipalServidorSocket
{
    public static void main(String[] args) throws IOException
    {
        ServidorSocket s=new ServidorSocket();
        s.servicio();
    }
}
