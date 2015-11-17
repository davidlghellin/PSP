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
public class Principal
{
    public static void main(String[] args)
    {
        MonitorAsignacionRecursos m=new MonitorAsignacionRecursos(5, 6);
        for (int i = 0; i < 10; i++)
        {
           new Thread(new Usuarios(m),"Usuario "+(i+1)).start();
        }
    }
}
