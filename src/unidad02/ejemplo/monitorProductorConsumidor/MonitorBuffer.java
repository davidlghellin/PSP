/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidad02.ejemplo.monitorProductorConsumidor;

/**
 *
 * @author David López González
 */
public class MonitorBuffer
{

    private int numCeldas;
    private double[] buffer;
    private int puestas = 0, sacadas = 0;
    private int cont = 0;

    public MonitorBuffer(int numCeldas)
    {
        this.numCeldas = numCeldas;
        buffer = new double[numCeldas];
    }

    public synchronized void insertar(double valor) throws InterruptedException
    {
        while (cont == numCeldas)  //condicion buffer lleno
        {
            wait();
        }
        buffer[puestas] = valor;
        puestas = (puestas + 1) % numCeldas;
        cont++;
        notifyAll();
    }

    public synchronized double extraer() throws InterruptedException
    {
        double valor;
        while (cont == 0)     //buffer vacio 
        {
            wait();
        }

        valor = buffer[sacadas];
        sacadas = (sacadas + 1) % numCeldas;
        cont--;
        notifyAll();
        return valor;
    }
}
