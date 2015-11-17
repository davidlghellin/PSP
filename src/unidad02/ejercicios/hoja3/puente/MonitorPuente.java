/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidad02.ejercicios.hoja3.puente;

/**
 *
 * @author David López González
 */
public class MonitorPuente
{

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
