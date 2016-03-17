package unidad02.ejercicios.hoja2.ejer08;

import java.io.IOException;

/**
 *
 * @author David López González
 */
public class Principal
{

    /*
    Dos tipos de procesos (lectores y escritores) para acceder a un fichero:
    
    Lectores: consultan el fichero.
    Escritores: consultan y modifican el fichero.
    Cuando un escritor accede al fichero, es el único proceso que la puede usar.
    Varios lectores pueden acceder simultáneamente
    */
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
