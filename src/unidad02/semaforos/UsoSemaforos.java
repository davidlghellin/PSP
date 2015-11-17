package unidad02.semaforos;
import java.util.concurrent.Semaphore;

/*
 Su pongamos que tenemos 4 procesos (p1, p2, p3, p4), cada proceso realiza su "tarea"
 simultaneamente (durante un tiempo indefinido) y posteriormente termina.
 Supongamos además que necesitamos que se ejecuten primero los procesos P1 y P3, y luego P2 y P4.
 */
public class UsoSemaforos
{

    protected static Semaphore oFinP1, oFinP3;

    public static void main(String[] args)
    {
        oFinP1 = new Semaphore(0, true);
        oFinP3 = new Semaphore(0, true);
        (new Thread(new p1(oFinP1))).start();
        (new Thread(new p2(oFinP1, oFinP3))).start();
        (new Thread(new p3(oFinP3))).start();
        (new Thread(new p4(oFinP1, oFinP3))).start();
    }
}
