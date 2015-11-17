/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidad02.ejercicios.hoja2.ejer09;

/**
 *
 * @author David López González
 */
public class MonitorParking
{
    private int numCeldas;
    private int[] buffer;
    private int cochesEntrados = 0, cochesSalidos = 0;
    private int contador = 0;

    public MonitorParking(int numCeldas)
    {
        this.numCeldas = numCeldas;
        this.buffer= new int [numCeldas];
        
    }
    
    public synchronized void entrar(int valor) throws InterruptedException
    {
        while (contador == numCeldas)  //condicion buffer lleno
            wait();

        buffer[cochesEntrados] = valor;
        cochesEntrados=(cochesEntrados+1)%numCeldas;
        contador++;
        notifyAll();
    }
    
    public synchronized int salir() throws InterruptedException
    {
        int valor;
        while (contador == 0)     //buffer vacio 
            wait();

        valor = buffer[cochesSalidos];
        buffer[cochesSalidos]=0;
        cochesSalidos=(cochesSalidos+1)%numCeldas;
        contador--;
        notifyAll();
        return valor;
    }

    public void imp()
    {
        System.out.println("");
        for (int i = 0; i < buffer.length; i++)
        {
            System.out.print(" "+buffer[i]);
        }
        System.out.println("");
    }
    
}
