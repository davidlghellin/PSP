package unidad02.semaforo1;

import java.util.concurrent.Semaphore;

public class p3 extends Thread
{
    protected Semaphore semaforo;

    public p3(Semaphore semaforo)
    {
        this.semaforo = semaforo;
    }

    public void run()
    {
        try
        {
            sleep((int) Math.round(500 * Math.random() - 0.5));
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println("P3");
        this.semaforo.release(2); //para que puedan adquirirlo P2 y P3
    }
}
