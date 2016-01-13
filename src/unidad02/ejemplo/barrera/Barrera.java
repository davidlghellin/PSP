package unidad02.ejemplo.barrera;

import java.util.concurrent.Semaphore;

/**
 *
 * @author David López González
 */
public class Barrera
{

    private final int n;
    private int waiting = 0;
    private boolean rota = false;

    public Barrera(int n)
    {
        this.n = n;
    }

    public synchronized void await()
    {
        waiting++;
        while (!rota && waiting < n)
        {
            waiting();
        }
        rota = true;
        waiting = 0;
        notifyAll();
    }

    private void waiting()
    {
        try
        {
            System.out.println("Esperamos");
            wait();
        } catch (InterruptedException ignored)
        {
        }
    }

    public static void main(String[] args)
    {
        Barrera b = new Barrera(3);
        for (int i = 0; i < 100; i++)
        {
            Barco barco = new Barco(i, b);
            new Thread(barco).start();
        }
    }
}
