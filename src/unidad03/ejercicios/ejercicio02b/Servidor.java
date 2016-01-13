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
package unidad03.ejercicios.ejercicio02b;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.auth.callback.TextOutputCallback;

/**
 *
 * @author David López González
 */
public class Servidor
{

    public static void main(String[] args) throws IOException
    {
        Servidor s = new Servidor();
    }

    private ServerSocket socketEcho, socketHora;
    private InetSocketAddress dirEcho, dirHora;
    ObjectOutputStream osEcho, osHora;
    ObjectInputStream isEcho, isHora;

    Servidor() throws IOException
    {
        new ServerHora().start();
        new ServerEcho().start();
    }

    class ServerEcho extends Thread
    {

        Socket socket;
        String texto;

        ServerEcho() throws IOException
        {
            socketEcho = new ServerSocket();
            dirEcho = new InetSocketAddress("127.0.0.1", 9998);
            socketEcho.bind(dirEcho);
            socket = socketEcho.accept();
            System.out.println("Conexion extablecidoa");
            isEcho = new ObjectInputStream(socket.getInputStream());
            osEcho = new ObjectOutputStream(socket.getOutputStream());
        }

        @Override
        public void run()
        {
            do
            {
                try
                {
                    texto = isEcho.readUTF();
                    osEcho.writeObject(texto);
                } catch (IOException ex)
                {
                    Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                }
            } while (socket.isConnected() && !texto.equalsIgnoreCase("fin"));
        }

    }

    class ServerHora extends Thread
    {

        Socket socket;

        ServerHora() throws IOException
        {
            socketHora = new ServerSocket();
            dirHora = new InetSocketAddress("127.0.0.1", 9999);
            socketHora.bind(dirHora);
            socket = socketHora.accept();
            System.out.println("Conexion extablecida con el cliente");
            isHora = new ObjectInputStream(socket.getInputStream());
            osHora = new ObjectOutputStream(socket.getOutputStream());
        }

        @Override
        public void run()
        {
            String texto = null;
            do
            {
                try
                {
                    texto = isHora.readUTF();
                    System.out.println(texto);
                    osHora.writeUTF(texto);
                } catch (IOException ex)
                {
                    Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                }
            } while (!texto.equalsIgnoreCase("Bye"));
            System.out.println("cerrar");
        }

    }
}
