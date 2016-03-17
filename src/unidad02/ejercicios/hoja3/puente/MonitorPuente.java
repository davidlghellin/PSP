
package unidad02.ejercicios.hoja3.puente;

/**
 *
 * @author David López González
 */
public class MonitorPuente
{

    /*
    El pueblo de Puente Duero debe su nombre a un viejo puente romano sobre el conocido
    río. El puente es estrecho y no permite el cruce de dos coches que circulen en sentidos
    contrarios. Por su peculiar forma, alto en el centro y bajo en los extremos, no hay
    visibilidad, y desde un lado no podemos saber si hay un coche empezando a cruzar el
    puente por el otro.
    Se han instalado cuatro sensores y dos barreras. Dos sensores indican la llegada de
    coches en cada dirección, emitiendo una señal por cada coche que pasa, y los otros dos
    indican salidas respectivas. Las barreras bloquean las entradas al puente, y cuando
    reciben una señal se levantan para permitir el paso de un coche (y uno sólo); acto
    seguido se vuelven a cerrar.
    Diseñe un programa concurrente, libre de bloqueos e inaniciones, que simule el sistema
    descrito, incluyendo un proceso que emplee las señales recibidas de los sensores para
    emitir señales apropiadas a las barreras, de manera que:
    Nunca crucen simultáneamente el puente vehículos en sentidos opuestos.
    Nadie espere eternamente para pasar
    Todos pasen lo más pronto posible salvo violación de las normas 1 o 2.
    Las señales de los sensores han de atenderse inmediatamente (o al menos lo más
    pronto posible), para no “perder” el paso de ningún coche.
     */
    private int cochesNCruzando, cochesSCruzando;
    private int total;

    public int getCochesNCruzando(){return cochesNCruzando;}
    public int getCochesSCruzando(){return cochesSCruzando;}
    public int getTotal(){return total;}

    MonitorPuente()
    {
        cochesNCruzando = 0;
        cochesSCruzando = 0;
        total = 0;
    }

    public synchronized void entrarCocheDelNorte() throws InterruptedException
    {
        while (cochesSCruzando > 0)
            wait();
        

        cochesNCruzando++;
        total++;
        System.out.println("Entra el coche del norte, hay coches del norte " + cochesNCruzando + " soy :" + Thread.currentThread().getName());
    }
    public synchronized void salirCocheNorte() throws InterruptedException 
    {
        //Thread.sleep((long) (Math.random() * 3000));//tiempo en recorrer el puente
        System.out.println("Sale el coche del norte");
        cochesNCruzando--;
        notifyAll();
        System.out.println("Hay total: " + getTotal());
    }
    public synchronized void salirCocheSur() throws InterruptedException 
    {
        //Thread.sleep((long) (Math.random() * 3000));//tiempo en recorrer el puente
        System.out.println("Sale el coche del norte");
        cochesSCruzando--;
        notifyAll();
        System.out.println("Hay total: " + getTotal());
    }

    public synchronized void entrarCocheDelSur() throws InterruptedException
    {
        while (cochesNCruzando > 0)
            wait();
        
        cochesSCruzando++;
        total++;
        System.out.println("Entra el coche del sur, hay coches del sur " + cochesSCruzando + " soy :" + Thread.currentThread().getName());
    }
    /*
    primera idea, mal, porque es synchronize y no puede entrar hasta que salgan
    
     public synchronized void CocheDelNorte() throws InterruptedException
    {
        while (cochesSCruzando > 0)
        {
            wait();
        }

        cochesNCruzando++;
        total++;
        System.out.println("Entra el coche del norte, hay coches del norte " + cochesNCruzando + " soy :" + Thread.currentThread().getName());
        Thread.sleep((long) (Math.random() * 3000));//tiempo en recorrer el puente
        System.out.println("Sale el coche del norte");
        cochesNCruzando--;
        notifyAll();
        System.out.println("Hay total: " + getTotal());
    }
    */
}
