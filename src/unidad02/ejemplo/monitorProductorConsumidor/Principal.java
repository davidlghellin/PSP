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
public class Principal
{

    public static void main(String[] args)
    {
        int ranuras = 10;
        MonitorBuffer mBuffer = new MonitorBuffer(ranuras);

        new Thread(new Productor(mBuffer)).start();
        new Thread(new Consumidor(mBuffer)).start();
    }
}
