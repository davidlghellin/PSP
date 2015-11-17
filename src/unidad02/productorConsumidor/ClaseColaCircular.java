/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidad02.productorConsumidor;

/**
 *
 * @author David López González
 */
public class ClaseColaCircular
{  // Declaracion de la clase de Colas

    static int max;  // Tamano maximo de la Cola
    static int cola[]; // Declaracion del arreglo
    static int frente, fin; // Indicadores de inicio y fin de la Cola

    ClaseColaCircular(int max)
    { // Constructor que inicializa el frente y el final de la Cola
        frente = -1;
        fin = -1;
        this.max = max;
        cola = new int[max];
        System.out.println("Cola inicializada !!!");
    }

    public static void insertar(int dato)
    {
        if (fin == max - 1 && frente != 0)
        {
            fin = 0;
        }
        else
        {
            fin++;
        }
        cola[fin] = dato;
        if (frente == -1)
        {
            frente = 0;
        }
    }

    public static void eliminar()
    {
        System.out.println("\n\n<<< ELIMINAR >>>");
        if (frente == -1)
        {  // Esta vacia la Cola?
            System.out.println("\nCola vacia !!!");
            return;
        }
        System.out.println("Dato eliminado = " + cola[frente]);
        if (frente == fin)
        {                                    //el primer fuente
            frente = -1;
            fin = -1; //si frente igual a fin vacios mostrar -1
            return;
        }
        if (frente == max)
        {
            frente = 0;
        }
        else
        {
            frente++;
        }
    }

    public static void mostrar()
    {
        int i = 0;
        System.out.println("\n\n<<< MOSTRAR COLA CIRCULAR >>>");
        if (frente == -1)
        {
            System.out.println("\nCola Circular vacia !!!");
        }
        else
        {
            i = frente;
            do
            {
                System.out.println("colacircular[" + i + "]=" + cola[i]);
                i++;
                if (i == max && frente > fin)
                {
                    i = 0; // Reiniciar en cero (dar la vuelta)
                }
            } while (i != fin + 1);
        }

        System.out.println("frente=" + frente);
        System.out.println("fin=" + fin);

        System.out.println("max=" + max);
    }
}
