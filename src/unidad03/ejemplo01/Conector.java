/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidad03.ejemplo01;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David López González
 */
public class Conector
{

    ServerSocket server;
    Socket socket;
    int puerto = 9000;
    DataOutputStream salida;
    BufferedReader entrada;

    public void iniar()
    {
        try
        {
            server = new ServerSocket(puerto);
            socket = new Socket();
            socket = server.accept();
            
            
            entrada= new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String mensaje=entrada.readLine();
            System.out.println(mensaje);
            salida=new DataOutputStream(socket.getOutputStream());
            salida.writeUTF("Adios mundo");
            socket.close();
        } catch (IOException ex)
        {
            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
