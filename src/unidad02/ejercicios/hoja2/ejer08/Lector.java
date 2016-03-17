package unidad02.ejercicios.hoja2.ejer08;

import static java.lang.Thread.sleep;

/**
 *
 * @author David López González
 */
public class Lector extends Thread
{

    /*
    Dos tipos de procesos (lectores y escritores) para acceder a un fichero:
    
    Lectores: consultan el fichero.
    Escritores: consultan y modifican el fichero.
    Cuando un escritor accede al fichero, es el único proceso que la puede usar.
    Varios lectores pueden acceder simultáneamente
     */
    private Fichero f;

    public Lector(Fichero f)
    {
        this.f = f;
    }

    @Override
    public void run()
    {
        String palabra;
        try
        {
            while (true)
            {
                sleep((long) (Math.random() * 2000));
                palabra = f.leer();
                System.out.println(palabra);
            }
        } catch (Exception e)
        {
        }
    }
}
