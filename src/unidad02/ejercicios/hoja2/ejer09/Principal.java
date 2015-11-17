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
public class Principal
{
    public static void main(String[] args)
    {
        MonitorParking parking= new MonitorParking(5);
        new Thread(new Salida(parking)).start();
        for (int i = 0; i < 10; i++)
        {
            
        new Thread(new Entrada(parking)).run();
    
        }

        
    }
}
