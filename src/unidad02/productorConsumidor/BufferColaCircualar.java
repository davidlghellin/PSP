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
public class BufferColaCircualar implements Buffer
{
    int max, frente,fin;
    int nElementos;
    int[] v;

    public BufferColaCircualar(int max)
    {
        this.max = max;
        this.frente = 0;
        this.fin = 0;
        this.v = new int[max];
        nElementos=0;
    }
    synchronized public int extraer()
    {
        while (frente==fin)
        {
            try
            {
                wait();
            } catch (InterruptedException e){}
        }
        nElementos--;
        notifyAll();
        return v[--fin];
    }

    synchronized public void insertar(int elem)
    {
        while (nElementos==max)
        {
            try
            {
                wait();
            } catch (InterruptedException e){}
        }
        nElementos++;
        v[fin++] = elem;
        notifyAll();
    }
}
