package unidad01;


import java.io.IOException;
import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author David López González
 */
public class RunProcess
{

    public static void main(String[] args) throws IOException
    {
        if (args.length <= 0)
        {
            System.err.println("Se necesita un programa a ejecutar");
            System.exit(-1);
        }
        ProcessBuilder pb = new ProcessBuilder(args);
        try
        {
            Process process = pb.start();

            int retorno = process.waitFor();

            System.out.println("La ejecución de " + Arrays.toString(args) + " devuelve  " + retorno);

        } catch (IOException ex)
        {
            System.err.println("Excepción de E / S!!");
            System.exit(-1);
        } catch (InterruptedException ex)
        {
            System.err.println("El proceso hijo finalizó de forma incorrecta");
            System.exit(-1);
        }
    }
}
