package unidad02.ejercicios.hoja2.ejer08;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David López González
 */
public class Escritor extends Thread
{

    /*
    Dos tipos de procesos (lectores y escritores) para acceder a un fichero:
    
    Lectores: consultan el fichero.
    Escritores: consultan y modifican el fichero.
    Cuando un escritor accede al fichero, es el único proceso que la puede usar.
    Varios lectores pueden acceder simultáneamente
     */
    private Fichero f;

    public Escritor(Fichero f)
    {
        this.f = f;
    }

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                sleep((long) (Math.random() * 4000));
                f.escribir((int) (Math.random() * 200));
            } catch (InterruptedException ex)
            {
                Logger.getLogger(Escritor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex)
            {
                Logger.getLogger(Escritor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
