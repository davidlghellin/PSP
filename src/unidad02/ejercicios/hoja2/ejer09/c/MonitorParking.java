package unidad02.ejercicios.hoja2.ejer09.c;

/**
 *
 * @author David López González
 */
public class MonitorParking
{
    /*
    Un Parking con un número de plazas N recibe un número de coches M. Se crearán tantos
    Threads como coches haya. El parking dispondrá de una sola entrada y una única salida. Para
    acceder al parking debe haber plazas disponibles. El tiempo de espera dentro del parking es
    aleatorio. En el momento que un vehículo sale del parking notifica al dispositivo de control el
    número de plaza que ha dejado libre, poniéndose ésta a disposición del próximo coche que
    entre. Un vehículo que ha salido estará un tiempo aleatorio fuera del parking y después
    intentará entrar. Por tanto al parking estarán entrando y saliendo coches de forma indefinida.
    El resultado que debe mostrar debe ser algo así:
    
    ENTRADA: Coche 1 aparca en 0
    Plazas libres: 5
    Parking: [1] [0] [0] [0] [0] [0]
    ENTRADA: Coche 2 aparca en 1
     */
    private int numCeldas;
    private Coche[] buffer;
    private int contador = 0;

    public MonitorParking(int numCeldas)
    {
        this.numCeldas = numCeldas;
        this.buffer = new Coche[numCeldas];
        for (int i = 0; i < buffer.length; i++)
        {
            buffer[i] = new Coche();
        }
    }

    public synchronized void entrar(Coche c) throws InterruptedException
    {
        while (contador == numCeldas)  //condicion buffer lleno
        {
            wait();
        }

        //comprobar que la posicion de la plaza esta en 0      
        int posEntra = -1;
        for (int i = 0; i < buffer.length && posEntra < 0; i++)
        {
            if (buffer[i].getId() == 0)
            {
                posEntra = i;
            }
        }
        buffer[posEntra] = c;
        contador++;
        notifyAll();
    }

    public synchronized Coche salir(Coche c) throws InterruptedException
    {
        Coche coche;
        while (contador == 0)     //buffer vacio 
        {
            wait();
        }

        // tenemos que saber que posicio es la que deja
        int posDeja = 0;
        for (int i = 0; i < buffer.length; i++)
        {
            if (buffer[i].getId() == c.getId())
            {
                posDeja = i;
            }
        }
        coche = buffer[posDeja];
        buffer[posDeja] = new Coche();
        contador--;
        notifyAll();
        return coche;
    }

    public void imp()
    {
        for (int i = 0; i < buffer.length; i++)
        {
            System.out.print(" " + buffer[i].getId());
        }
        System.out.println("");
        System.out.println("");
    }
}
