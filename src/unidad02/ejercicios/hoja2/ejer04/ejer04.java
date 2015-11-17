/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidad02.ejercicios.hoja2.ejer04;

import java.util.Scanner;
import java.util.concurrent.Semaphore;
import org.omg.CosNaming.BindingType;

/**
 *
 * @author David López González
 */
public class ejer04
{

    public static void main(String[] args)
    {
        Scanner teclado = new Scanner(System.in);
        int nCajas;
        System.out.println("Cuántas cajas?");
        nCajas = teclado.nextInt();
        Semaphore semaforo = new Semaphore(nCajas);
        
        System.out.println("Cuántos clientes?");
        int nClientes = teclado.nextInt();
        Cliente c[] = new Cliente[nClientes];

        for (int i = 0; i < c.length; i++)
        {
            c[i] = new Cliente(semaforo);
        }
        for (int i = 0; i < c.length; i++)
        {
            new Thread(c[i], "cliente " + (i + 1)).start();
        }
    }
}
