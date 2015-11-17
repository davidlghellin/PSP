package unidad01;


import java.io.File;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author David López González
 */
public class ejemplo01b
{

    public static void main(String[] args)
    {

        ProcessBuilder pb;
        pb = new ProcessBuilder("terminator");
        pb.directory(new File("/home/wizord/Descargas"));
        try
        {
            Process process = pb.start();
            process.waitFor();
        } catch (IOException ex)
        {
        } catch (InterruptedException ex)
        {
        }

    }
}
