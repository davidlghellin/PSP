/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidad03.ejemplo02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 *
 * @author David López González
 */

class ClaseSerializada implements Serializable
{
    String nombre;
    int id;

    public ClaseSerializada(String nombre, int id)
    {
        this.nombre = nombre;
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "Soy: "+nombre+".";
    }
}

public class ClienteSocket
{
    public static void main(String[] args) throws IOException
    {
        ClaseSerializada c= new ClaseSerializada("David", 1);
        
        // Preparo el socket 
        Socket clienteSocket= new Socket();
        String ip=saberIP();
        
        
        InetSocketAddress direccion = new InetSocketAddress("localhost",6666);
       // InetSocketAddress direccion = new InetSocketAddress(ip,6666);
           
    }
    public static String saberIP() throws IOException
    {
       // Obtenemos el S.O. para saber que IP ponerle
        String so = System.getProperty("os.name");
        String comando;
        // Comando para Linux
        if (so.equals("Linux"))
        {
            comando = "ifconfig";
        }
        // Comando para Windows
        else
        {
            comando = "cmd /c ipconfig";
        }
        Process p = Runtime.getRuntime().exec(comando);
        BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
        

        // falta tomar solamente la ip y no todo la info del comando
        return stdInput.toString();
    }
}
