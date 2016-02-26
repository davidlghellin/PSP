package unidad05;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 *
 * @author David López González
 * @author fran
 */
public class Ejemplo02
{

    public static void main(String[] args)
    {
        try
        {
            BufferedWriter fichero = new BufferedWriter(new FileWriter("/home/wizord/prueba.txt"));
            fichero.write("Prueba de escritura");
            fichero.close();
            System.out.println("Fin de programa");
            
            BufferedReader leeer=new BufferedReader(new FileReader("/home/wizord/prueba.txt"));
            System.out.println(leeer.readLine());
            leeer.close();
           

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
