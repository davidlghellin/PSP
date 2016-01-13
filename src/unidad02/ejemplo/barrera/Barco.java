package unidad02.ejemplo.barrera;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David López González
 */
public class Barco implements Runnable
{
    private int id;
    private Barrera barrera;

    public Barco(int id, Barrera barrera)
    {
        this.id = id;
        this.barrera = barrera;
    }

    @Override
    public void run()
    {
        try
        {
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException ex)
        {
            Logger.getLogger(Barco.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Llega el " + id + " a las: " + new Date());
        barrera.await();
        System.out.println("Sale el " + id + " a las: " + new Date());
    }

}
