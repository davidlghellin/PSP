package unidad02.ejercicios.hoja3.puente2;


/**
 *
 * @author David López González
 */
public class MonitorPuente
{

    private int cochesNCruzando= 0, cochesSCruzando= 0;
    private int total= 0;
    private int cochesNorteSegidos=0,cochesSurSegidos=0;
    private static int MAX_COCHES_SEGUIDOS_POR_LADO=5;

    public int getCochesNCruzando(){return cochesNCruzando;}
    public int getCochesSCruzando(){return cochesSCruzando;}
    public int getTotal(){return total;}


    public synchronized void entrarCocheDelNorte() throws InterruptedException
    {
        while (cochesSCruzando > 0||cochesNorteSegidos==MAX_COCHES_SEGUIDOS_POR_LADO)
            wait();
        
        cochesSurSegidos = 0;   // El otro lado se reinicia el contador
        cochesNorteSegidos++;   // Aumentamos contador de coches seguidos
        cochesNCruzando++;      // Aumentamos el nº de coches norte
        total++;                // Total de los dos lados
        System.out.println("Entra el coche del norte, hay coches del norte " + cochesNCruzando + " soy :" + Thread.currentThread().getName());
    }
    public synchronized void salirCocheNorte() throws InterruptedException 
    {
        //Thread.sleep((long) (Math.random() * 3000));//tiempo en recorrer el puente
        System.out.println("Sale el coche del norte");
        cochesNCruzando--;
        notifyAll();
        System.out.println("En total han pasado/ hay: " + getTotal()+ " coches por el puente");
    }
   

    public synchronized void entrarCocheDelSur() throws InterruptedException
    {
        while (cochesNCruzando > 0 || cochesSurSegidos==MAX_COCHES_SEGUIDOS_POR_LADO)
            wait();
        
        cochesNorteSegidos = 0; // El otro lado se reinicia el contador
        cochesSurSegidos++;     // Aumentamos contador de coches seguidos
        cochesSCruzando++;      // Aumentamos el nº de coches sur
        total++;                // Total de los dos lados
        System.out.println("Entra el coche del sur, hay coches del sur " + cochesSCruzando + " soy :" + Thread.currentThread().getName());
    }
    public synchronized void salirCocheSur() throws InterruptedException 
    {
        //Thread.sleep((long) (Math.random() * 3000));//tiempo en recorrer el puente
        System.out.println("Sale el coche del norte");
        cochesSCruzando--;
        notifyAll();
        System.out.println("En total han pasado/ hay: " + getTotal()+ " coches por el puente");
    }
    /*
    primera idea, mal, porque es synchronize y no puede entrar hasta que salgan
    
     public synchronized void CocheDelNorte() throws InterruptedException
    {
        while (cochesSCruzando > 0)
            wait();

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
