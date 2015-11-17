/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problemasInternet.eje28;

/**
 *
 * @author David López González
 */
public class MonitorAsignacionRecursos
{

    // cantidad de recursos de dos productos
    int cantNumero1, cantNumero2;

    public MonitorAsignacionRecursos(int cantNumero1, int cantNumero2)
    {
        this.cantNumero1 = cantNumero1;
        this.cantNumero2 = cantNumero2;
        System.out.println("Hay:\nrecurso1: "+cantNumero1+", recurso2: "+cantNumero2);
    }

    public synchronized void pedirRecurso(int tipo) throws InterruptedException
    {
        System.out.println("El " + Thread.currentThread().getName() + " pide el recurso " + tipo);
        if (tipo == 1)        //pide recurso 1
        {
            if (cantNumero1 == 0)
            {
                wait();
            }
            cantNumero1--;
            System.out.println("Hay:\nrecurso1: "+cantNumero1+", recurso2: "+cantNumero2);
        }
        else if (tipo == 2) //pide recurso 2
        {
            if (cantNumero2 == 0)
            {
                wait();
            }
            cantNumero2--;
            System.out.println("Hay:\nrecurso1: "+cantNumero1+", recurso2: "+cantNumero2);
        }
        else if (tipo == 3) //pide recurso 1 y 2
        {
            if (cantNumero1 == 0 || cantNumero2 == 0)
            {
                wait();
            }
            cantNumero1--;
            cantNumero2--;
            System.out.println("Hay:\nrecurso1: "+cantNumero1+", recurso2: "+cantNumero2);
        }
    }

    public synchronized void liberarRecurso(int tipo)
    {
        {
            System.out.println("El " + Thread.currentThread().getName() + " libera el recurso " + tipo);
            if (tipo == 1)        //libera recurso 1
            {
                cantNumero1++;
                System.out.println("Hay:\nrecurso1: "+cantNumero1+", recurso2: "+cantNumero2);
                notifyAll();
            }
            else if (tipo == 2) //libera recurso 2
            {
                cantNumero2++;
                System.out.println("Hay:\nrecurso1: "+cantNumero1+", recurso2: "+cantNumero2);
                notifyAll();
            }
            else if (tipo == 3) //libera recurso 1 y 2
            {
                cantNumero1++;
                cantNumero2++;
                System.out.println("Hay:\nrecurso1: "+cantNumero1+", recurso2: "+cantNumero2);
                notifyAll();
            }
        }
    }
}
