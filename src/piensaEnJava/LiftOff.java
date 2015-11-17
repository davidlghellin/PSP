/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piensaEnJava;

/**
 *
 * @author David López González
 */
public class LiftOff implements Runnable
{

    protected int cuentaAtras = 10;   //predeterminado
    private static int taskCount = 0;
    private final int id = taskCount++;

    public LiftOff(){}
    public LiftOff(int c)
    {
        cuentaAtras = c;
    }

    public String status()
    {
        return "#" + id + "(" + (cuentaAtras > 0 ? cuentaAtras : "LiftOff!") + "), ";
    }

    public void run()
    {
        while (cuentaAtras-- > 0)
        {
            System.out.println(status());
            Thread.yield();
        }
    }
    
    public static void main(String[] args)
    {
        LiftOff launch=new LiftOff();
        launch.run();
    }
}
