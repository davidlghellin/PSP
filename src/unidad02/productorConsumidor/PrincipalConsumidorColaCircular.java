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
public class PrincipalConsumidorColaCircular
{
    static Buffer buf= new BufferColaCircualar(5);
    
    public static void main(String[] args)
    {
        for (int i = 0; i < 5; i++)
        {
            new Productor(i,  buf).start();
        }for (int i = 0; i < 5; i++)
        {
            new Consumidor(i,  buf).start();
        }
    }
}
