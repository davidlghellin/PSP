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
public class Productor extends Thread
{
    int elem;
    Buffer buffer;

    public Productor(int i, Buffer buffer)
    {
        this.elem = i;
        this.buffer = buffer;
        //System.out.println("Entra el productor "+i);
    }

    @Override
    public void run()
    {
        try
        {
            
            buffer.insertar(elem);
        } catch (Exception e)
        {
        }
        System.out.println("he puesto el elemento "  + elem);
        
    } 
    
    
}
