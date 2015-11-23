/*
 * Copyright (C) 2015 David López González
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package unidad03.ejercicios.ejemplo03;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David López González
 */
public class Server
{
    //Socket que solamente acepta una conexión
        
    private ServerSocket server;        // Socket server
    private Socket socket;              // Socket de entrada
    private DataInputStream entrada;    // Flujo que leeremos del cliente
    private int puerto = 9000;          // Puerto con el que trabajaremos
    private String ip = "127.0.0.1";      // Direción ip con la que escucharemos
    InetSocketAddress direccion;

    public void servicio()
    {
        try
        {
            server = new ServerSocket();        // Servidor con puerto
            direccion = new InetSocketAddress(ip, puerto);
            server.bind(direccion);             // Asociamos la inet al servidor

            socket = new Socket();              // Creamos la instancia del socket
            socket = server.accept();           // Establecemos la conexión

            // Creamos el flujo de entrada en un buffer
            entrada = new DataInputStream(socket.getInputStream());
            String mensaje = null;
            do
            {
                System.out.println("Soy server");
                mensaje = entrada.readUTF();
                System.out.println("responde: " + mensaje);
            } while (socket.isConnected() && !mensaje.equals("FIN"));

            System.out.println("Socket cerrado");
            socket.close();
            entrada.close();
        } catch (IOException ex)
        {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
