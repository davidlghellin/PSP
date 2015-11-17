package unidad02.semaforo1;

import java.util.concurrent.Semaphore;

/*
 Su pongamos que tenemos 4 procesos (p1, p2, p3, p4), cada proceso realiza su "tarea"
 simultaneamente (durante un tiempo indefinido) y posteriormente termina.
 Supongamos además que necesitamos que se ejecuten primero los procesos P1 y P3, y luego P2 y P4.
 */
public class UsoSemaforos
{
    protected static Semaphore semaforo;

    public static void main(String[] args)
    {
        semaforo = new Semaphore(-2, true);
     
        (new Thread(new p1(semaforo))).start();
        (new Thread(new p2(semaforo))).start();
        (new Thread(new p3(semaforo))).start();
        (new Thread(new p4(semaforo))).start();
    }
}
