package unidad01;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Fran
 * @author David López González
 */
public class UnSaludo
{

    public static void main(String[] args)
    {
        if (args.length < 1)
        {
            System.out.println("SE NECESITA UN PARAMETRO");
            System.exit(1);
        }
        for (int i = 0; i < 5; i++)
        {
            System.out.println(i + 1 + ". " + args[0]);

        }
    }
}
