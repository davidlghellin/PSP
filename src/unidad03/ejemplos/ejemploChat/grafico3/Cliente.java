package unidad03.ejemplos.ejemploChat.grafico3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JTextArea;

/**
 *
 * @author David López González
 */
public class Cliente implements Runnable
{

    //Declaramos las variables necesarias para la conexion y comunicacion
    private Socket cliente;
    private DataInputStream in;
    private DataOutputStream out;
    //El puerto debe ser el mismo en el que escucha el servidor
    private int puerto = 2027;
    //Si estamos en nuestra misma maquina usamos localhost si no la ip de la maquina servidor
    private String host = "localhost";
    private String mensajes = "";
    JTextArea jtaTexto;

    //Constructor recibe como parametro el jtaTexto donde se mostraran los mensajes
    public Cliente(JTextArea texto)
    {
        this.jtaTexto = texto;
        try
        {
            cliente = new Socket(host, puerto);
            in = new DataInputStream(cliente.getInputStream());
            out = new DataOutputStream(cliente.getOutputStream());
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public void run()
    {
        try
        {
            //Ciclo infinito que escucha por mensajes del servidor y los muestra en el jtaTexto
            while (true)
            {
                mensajes += in.readUTF();
                jtaTexto.setText(mensajes);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //Funcion sirve para enviar mensajes al servidor
    public void enviarMsg(String msg)
    {
        try
        {
            out.writeUTF(msg);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
