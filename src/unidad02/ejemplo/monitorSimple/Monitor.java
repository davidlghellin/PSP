/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidad02.ejemplo.monitorSimple;

/**
 *
 * @author David López González
 */
public class Monitor
{
    private static int dato;

    public Monitor(int dato)
    {
        this.dato=dato;
    }
    
    public synchronized void INC() throws InterruptedException
    {
        while(!(dato<=0))
            wait();
        dato++;
        notifyAll();
    }
    public synchronized void DEC() throws InterruptedException
    {
        while(!(dato>0))
            wait();
        dato--;
        notifyAll();
    }

    @Override
    public synchronized String toString()
    {
        return (new Integer(dato).toString());
    }
    
}
