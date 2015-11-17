/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidad02;

/**
 *
 * @author David López González
 */
public class Ecuacion2GradoSecuecial
{

    public static void main(String[] args)
    {
        double a = 2, b = -7, c = 3;
        double x;

        x = (Math.sqrt((b * b) - 4.d * a * c));
        System.out.println(x);
        System.out.println(-b + x);
        System.out.println((-b + x) / (2.d * a));
        System.out.println((-b - x) / (2.d * a));
    }
}
