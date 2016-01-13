package unidad03.ejemplos.ejemploChat2;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import unidad02.ejercicios.hoja2.ejer09.Entrada;

/**
 *
 * @author David López González
 */
public class Escribir extends Thread
{//hilo que envia los mensajes que se escriben

    private Socket socket;
    private String name;
    private Scanner teclado;

    public Escribir(Socket socket, String name)
    {//constructor del hilo
        super("Hilo escribir");
        this.socket = socket;
        this.name = name;
        start();
        teclado=new Scanner(System.in);
    }

    public void run()
    {
        try
        {
            boolean terminar = false;
            while (!terminar)
            {//bucle que escribe mientras se cumple la condicion
                OutputStream os = socket.getOutputStream();// flujo de datos del socket
                DataOutputStream flujoDOS = new DataOutputStream(os);//se asocia el flujo de datos a un flujo de escritura
                String mensaje = teclado.nextLine();//se escribe el mensaje
                if (mensaje.equals("salir"))
                {//si el mensaje es salir terminara el bucle
                    terminar = true;
                }
                else
                {
                    flujoDOS.writeUTF(name + " dice: " + mensaje);
                }
            }
            socket.close();
        } catch (Exception e)
        {
            System.out.println("Error");
        }
    }
}
