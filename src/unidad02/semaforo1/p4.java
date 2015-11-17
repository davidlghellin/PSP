package unidad02.semaforo1;

import java.util.concurrent.Semaphore;

public class p4 extends Thread
{
    protected Semaphore semaforo;

    public p4(Semaphore semaforo)
    {
        this.semaforo = semaforo;
    }

    public void run()
    {
        try
        {
            this.semaforo.acquire();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        try
        {
            sleep((int) Math.round(500 * Math.random() - 0.5));
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println("P4");
    }
}
