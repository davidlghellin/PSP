package unidad01;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
public class ComunicationBetweenProcess
{

    public static void main(String args[]) throws IOException
    {
        String comando="ls";
        Process process = new ProcessBuilder(comando).start();
        InputStream is = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line;
        System.out.println("Salida del proceso " + comando+ ":");

        while ((line = br.readLine()) != null)
        {
            System.out.println(line);
        }
    }
}
