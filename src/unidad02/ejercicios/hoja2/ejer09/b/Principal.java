/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidad02.ejercicios.hoja2.ejer09.b;

import unidad02.ejercicios.hoja2.ejer09.*;

/**
 *
 * @author David López González
 */
public class Principal
{
    public static void main(String[] args)
    {
        MonitorParking parking= new MonitorParking(3);
        
        int numCoches=10;
        for (int i = 1; i <= numCoches; i++)
        {
            new Thread(new Coche(i,parking)).start();
        }
    }
}
