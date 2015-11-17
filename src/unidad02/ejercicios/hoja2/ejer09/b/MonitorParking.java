/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidad02.ejercicios.hoja2.ejer09.b;

import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;

/**
 *
 * @author David López González
 */
public class MonitorParking
{

    private int numCeldas;
    private Coche[] buffer;
    private int cochesEntrados = 0, cochesSalidos = 0;
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

        buffer[cochesEntrados] = c;
        cochesEntrados = (cochesEntrados + 1) % numCeldas;
        contador++;
        notifyAll();
    }

    public synchronized Coche salir() throws InterruptedException
    {
        Coche coche;
        while (contador == 0)     //buffer vacio 
            wait();

        coche = buffer[cochesSalidos];
        buffer[cochesSalidos] = new Coche();
        cochesSalidos = (cochesSalidos + 1) % numCeldas;
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
