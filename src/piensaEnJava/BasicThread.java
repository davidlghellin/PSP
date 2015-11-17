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
public class BasicThread
{
    public static void main(String[] args)
    {
        Thread t=new Thread(new LiftOff());
        t.start();
        System.out.println("Esperando a LiftOff");
    }
}
