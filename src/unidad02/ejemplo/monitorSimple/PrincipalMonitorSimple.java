/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidad02.ejemplo.monitorSimple;

/**
 *
 * @author David López González
 */
public class PrincipalMonitorSimple
{
    public static void main(String[] args)
    {
        Monitor m= new Monitor(100);
        
        HiloSumador hs1=new HiloSumador(m);
        HiloSumador hs2=new HiloSumador(m);
        HiloRestador hr1=new HiloRestador(m);
        HiloRestador hr2=new HiloRestador(m);
        
        Thread h1=new Thread(hs1,"hilo sumador 1");        
        Thread h2=new Thread(hs2,"hilo sumador 2");        
        Thread h3=new Thread(hr1,"hilo restador 1");        
        Thread h4=new Thread(hr2,"hilo restador 2");
        
        h1.start();h2.start();h3.start();h4.start();
        while(true)
            m.toString();
    }
}
