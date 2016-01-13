package unidad03.ejemplos.ejemplo03c;

import java.io.IOException;

/**
 *
 * @author David López González
 */
public class PrincipalClienteSocket
{
    public static void main(String[] args) throws IOException
    {
        ClienteSocket c=new ClienteSocket();
        c.conectar();
    }
}
