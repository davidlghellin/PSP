/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidad02.ejercicios.hoja2.ejer08;

import java.io.IOException;

/**
 *
 * @author David López González
 */
public class Principal
{

    public static void main(String[] args) throws IOException
    {
//        FileWriter fw = new FileWriter(new File("Prueba.txt"), true);
//        PrintWriter salida = new PrintWriter(fw);
//        salida.print("ssss");
//        salida.close();
//        fw.close();

        Fichero f = new Fichero();

        Lector l1 = new Lector(f);
        l1.setName("Lector 1");
        Lector l2 = new Lector(f);
        l2.setName("Lector 2");
        
        Escritor e1 = new Escritor(f);
        e1.setName("Escritor 1");
        Escritor e2 = new Escritor(f);
        e2.setName("Escritor 2");

        l1.start();
        l2.start();
        e1.start();
        e2.start();
    }
}
