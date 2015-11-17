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
public class Consumidor extends Thread
{
    int elem;
    Buffer buffer;

    public Consumidor(int elem, Buffer buffer)
    {
        this.elem = elem;
        this.buffer = buffer;
    }

    @Override
    public void run()
    {
        try
        {
            elem=buffer.extraer();
            System.out.println("Consumo " +elem);
        } catch (Exception e)
        {
        }
       
    }

   
    
}
