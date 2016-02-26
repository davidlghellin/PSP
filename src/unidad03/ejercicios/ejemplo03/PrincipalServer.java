package unidad03.ejercicios.ejemplo03;

import java.io.IOException;

/**
 *
 * @author David López González
 */
public class PrincipalServer
{
    public static void main(String[] args) throws IOException
    {
        Server server= new Server();
        server.servicio();        
    }
}
