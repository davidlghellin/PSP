/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidad02.ejercicios.hoja3.puente;

/**
 *
 * @author David López González
 */
public class Principal
{

    public static void main(String[] args) throws InterruptedException
    {
        MonitorPuente monitor = new MonitorPuente();
        int nNorte = 6;
        int nSur = 8;
        for (int i = 0; i < nNorte; i++)
        {
            Thread.sleep((long)Math.random()*5000);
            new Thread(new CochesNorte(i, monitor), "chocheNorte" + (i + 1)).start();
        }
        for (int i = 0; i < nSur; i++)
        {
            Thread.sleep((long)Math.random()*5000);
            new Thread(new CochesSur(i, monitor), "chocheSur" + (i + 1)).start();
        }
        
    }
}
