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
public class BufferPila implements Buffer
{
    private int cima, capacidad, vector[];

    public BufferPila(int i)
    {
        this.cima = 0;
        capacidad = i;
        vector = new int[i];
    }

    synchronized public int extraer()
    {

        while (cima == 0)
        {
            try
            {
                wait();
            } catch (InterruptedException e)
            {
            }
        }
        notifyAll();
        return vector[--cima];
    }

    synchronized public void insertar(int elem)
    {
       
        while (cima == capacidad - 1)
        {
            try
            {
                wait();
            } catch (InterruptedException e)
            {
            }
        }
        vector[cima++] = elem;
        notifyAll();
    }
}
