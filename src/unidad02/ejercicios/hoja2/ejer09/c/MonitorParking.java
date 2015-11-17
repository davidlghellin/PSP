/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidad02.ejercicios.hoja2.ejer09.c;

/**
 *
 * @author David López González
 */
public class MonitorParking
{
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
            wait();

        //comprobar que la posicion de la plaza esta en 0      
        int posEntra=-1;
        for(int i=0;i<buffer.length && posEntra<0;i++)
            if(buffer[i].getId()==0)
            {
                posEntra=i;
            }
        buffer[posEntra] = c;        
        contador++;
        notifyAll();
    }

    public synchronized Coche salir(Coche c) throws InterruptedException
    {
        Coche coche;
        while (contador == 0)     //buffer vacio 
            wait();

        // tenemos que saber que posicio es la que deja
        int posDeja=0;
        for(int i=0;i<buffer.length;i++)
            if(buffer[i].getId()==c.getId())
                posDeja=i;
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
