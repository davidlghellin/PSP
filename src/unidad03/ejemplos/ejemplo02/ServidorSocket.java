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
package unidad03.ejemplos.ejemplo02;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David López González
 */
public class ServidorSocket
{

    public static void main(String[] args) throws ClassNotFoundException
    {
        try
        {
            ClaseSerializada c;

            // Creamos el socket del servidor
            ServerSocket server = new ServerSocket();
            // Hacemos el bind(asociación) de la IP y del puerto al server
            InetSocketAddress direccion = new InetSocketAddress("localhost", 6666);
            server.bind(direccion);

            // Creamos el socket con la conexión extablecida
            Socket socket = server.accept();

            ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
            DataOutputStream os = new DataOutputStream(socket.getOutputStream());

            while (socket.isConnected())
            {
                Object obj = is.readObject();
                if (obj.getClass().equals((ClaseSerializada.class)))
                {
                    c = (ClaseSerializada) obj;
                    System.out.println("Objeto con: " + c);
                }
            }
            server.close();
            socket.close();
            is.close();
            os.close();
        } catch (IOException ex)
        {
            Logger.getLogger(ServidorSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Acabamos programa");
    }
}
