package unidad03.ejercicios.ejemplo03;

import java.io.IOException;

/**
 *
 * @author David López GonzálezS
 */
public class PrincipalCliente
{
    public static void main(String[] args) throws IOException
    {
        Cliente cliente= new Cliente();
        cliente.conectar(); 
    }
}
