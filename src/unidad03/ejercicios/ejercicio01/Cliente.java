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
package unidad03.ejercicios.ejercicio01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author David López González
 */
public class Cliente
{

    Socket clienteSocket;
    private int puerto = 9000;              // Puerto con el que trabajaremos
    private String ip = "127.0.0.1";        // IP con la que conectaremos
    private InetSocketAddress direccion;

    DataOutputStream salida;

    String texto = null;

    public void conectar() throws IOException
    {
        clienteSocket = new Socket();       // Creamos la instancia del socket
        direccion = new InetSocketAddress(ip, puerto);
        clienteSocket.connect(direccion);   // Asociamos la direccion y creamos la conexión

        // Stream por el que enviaremos los datos
        salida = new DataOutputStream(clienteSocket.getOutputStream());

        Scanner teclado = new Scanner(System.in);
        do
        {
            System.out.println("Soy cliente mandando, introduzca FIN para terminar");
            texto=teclado.nextLine();
            salida.writeUTF(texto);
        } while (clienteSocket.isConnected() && !texto.equals("FIN"));
        System.out.println("Socket con el server cerrado");
    }
}
