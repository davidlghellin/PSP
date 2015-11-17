/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidad00;

/**
 *
 * @author David López González
 * @author Fran
 */
public class ejemplo01
{
    public static void main(String[] args)
    {
        Runtime r = Runtime.getRuntime();
        String comando = "gnome-calculator";
        Process p, p1;
        try
        {
            p = r.exec(comando);
            p1 = r.exec("gedit");

            p.waitFor();
            p1.waitFor();
        } catch (Exception e)
        {
            System.out.println("Error en:" + comando);
            e.printStackTrace();
        }

    }
}
