package unidad02.ejercicios.hoja2;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David López González
 */
public class EjerTorno
{

    /*
    Considera el caso de un parque público que dispone de varias puertas de acceso. El
    acceso por cada una de las puertas del parque, está controlada por un torno
    independiente, que envía un evento propio a una aplicación de computador que debe
    contarlas y proporcionar en cualquier instante el número total de visitantes que han
    entrado en el parque. Crea un programa concurrente que simule esta situación. Haz una
    versión que falle y otra que solucione el problema.
    
    Orientaciones:

    Cada torno estará representado por un hilo.
    El número de visitantes se puede generar aleatoriamente para cada torno.
    En cada instante se mostrará el número de visitantes que ya han entrado.
    Al final se debe de mostrar el número total de visitantes.
    */
    public static void main(String[] args)
    {
        Torno t1 = new Torno((int) (Math.random() * 10));
        Torno t2 = new Torno((int) (Math.random() * 10));
        Torno t3 = new Torno((int) (Math.random() * 10));
        Torno t4 = new Torno((int) (Math.random() * 10));

        System.out.println("Hay " + t1.getnPersonasTorno()+ " personas en el torno 1");
        System.out.println("Hay " + t2.getnPersonasTorno()+ " personas en el torno 2");
        System.out.println("Hay " + t3.getnPersonasTorno()+ " personas en el torno 3");
        System.out.println("Hay " + t4.getnPersonasTorno()+ " personas en el torno 4\n");
        
        Thread h1 = new Thread(t1,"t1");
        Thread h2=new Thread(t2,"t2");
        Thread h3=new Thread(t3,"t3");
        Thread h4=new Thread(t4,"t4");
        
        h1.start();h2.start();h3.start();h4.start();
        
        try
        {
            h1.join();h2.join();h3.join();h4.join();
        } catch (InterruptedException ex)
        {
            Logger.getLogger(EjerTorno.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("\nTotal " + t1.getNumPersonasParque());
        System.out.println("La suma da:" + (t1.getnPersonasTorno() + t2.getnPersonasTorno() + t3.getnPersonasTorno() + t4.getnPersonasTorno()));
    }
}

class Torno implements Runnable
{

    private static int numPersonasParque=0;
    private int nPersonasTorno;
    private int aux;

    Torno(int n)
    {
        
        this.nPersonasTorno = n;
        aux = nPersonasTorno;
    }

    public  int getNumPersonasParque(){return numPersonasParque;}
    public int getnPersonasTorno(){return nPersonasTorno;}
    public  void setNumPersonasParque(int numPersonasParque){this.numPersonasParque = numPersonasParque; }
    public void setnPersonasTorno(int nPersonasTorno){this.nPersonasTorno = nPersonasTorno;}

    synchronized static private void aumentar()
    {
        numPersonasParque++;
    }

    @Override
    public void run()
    {
        while ((aux--) > 0)
        {
            try
            {
                sleep(50);
                aumentar();
            } catch (InterruptedException ex)
            {
                Logger.getLogger(Torno.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Pasa la " + (nPersonasTorno-aux)+" persona del torno: "+Thread.currentThread().getName());
            //System.out.println("aux: "+aux+" "+Thread.currentThread().getName());
        }
    }
}
