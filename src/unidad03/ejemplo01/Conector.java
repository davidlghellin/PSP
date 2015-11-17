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

    ServerSocket server;        // Socket server
    Socket socket;              // Socket de entrada
    int puerto = 9000;          // Puerto con el que trabajaremos
    DataOutputStream salida;    // Flujo que enviaremos al cliente
    BufferedReader entrada;     // Flujo que leeremos del cliente

    public void iniar()
    {
        try
        {
            server = new ServerSocket(puerto);  // Servidor con puerto
            socket = new Socket();              // Creamos la instancia del socket
            socket = server.accept();           // Establecemos la conexión
            
            // Creamos el flujo de entrada en un buffer
            entrada= new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String mensaje=entrada.readLine();
            System.out.println(mensaje);
            
            // Creamos el flujo que pasaremos por el socket de salda
            salida=new DataOutputStream(socket.getOutputStream());
            salida.writeUTF("Adios mundo\n");
            
            
            socket.close();
            entrada.close();
            salida.close();
        } catch (IOException ex)
        {
            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
