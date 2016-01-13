package unidad02.ejemplo.barreraCiclica;

/**
 *
 * @author David López González
 */
public class BarreraCiclica
{

    private final int n;
    private int waitingIn = 0;
    private int waitingOut = 0;

    public BarreraCiclica(int n)
    {
        this.n = n;
    }

    public synchronized void await()
    {
        while (waitingOut > 0)
        {
            waiting();
        }
        waitingIn++;
        while (waitingIn < n && waitingOut == 0)
        {
            waiting();
        }
        if (waitingIn >= n)
        {
            waitingOut = n;
            waitingIn -= n;
        }
        waitingOut--;
        notifyAll();
    }

    private void waiting()
    {
        try
        {
            System.out.println("Esperando");
            wait();
        } catch (InterruptedException ignored)
        {
        }
    }

    public static void main(String[] args) throws InterruptedException
    {
        BarreraCiclica b = new BarreraCiclica(3);
        for (int i = 1; i < 100; i++)
        {
            Barco barco = new Barco(i, b);
            new Thread(barco).start();
            Thread.sleep((long) (Math.random() * 1000));
        }
    }
}
