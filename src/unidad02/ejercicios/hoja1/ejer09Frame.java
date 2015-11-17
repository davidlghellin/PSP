/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidad02.ejercicios.hoja1;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author David López González
 */
public class ejer09Frame
{

    public static void main(String[] args)
    {
        FrameEjer09Thread frame = new FrameEjer09Thread();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(300, 300, 300, 300);

        Thread h = new Thread(frame);
        h.start();
    }
}

class FrameEjer09 extends JFrame
{

    Calendar calendario;
    JLabel reloj, relojCanarias;

    int hora, minutos, segundos;

    FrameEjer09()
    {
        calendario = new GregorianCalendar();
        hora = calendario.get(Calendar.HOUR_OF_DAY);
        minutos = calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND);

        reloj = new JLabel(hora + ":" + minutos + ":" + segundos);
        relojCanarias = new JLabel(hora - 1 + ":" + minutos + ":" + segundos);

        reloj.setBounds(10, 50, 500, 100);
        add(reloj);

        relojCanarias.setBounds(10, 100, 100, 100);
        add(relojCanarias);

    }

}

class FrameEjer09Thread extends JFrame implements Runnable
{

    Calendar calendario;
    JLabel reloj, relojCanarias;

    int hora, minutos, segundos;

    FrameEjer09Thread()
    {
        calendario = new GregorianCalendar();
        hora = calendario.get(Calendar.HOUR_OF_DAY);
        minutos = calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND);

        reloj = new JLabel(hora + ":" + minutos + ":" + segundos);
        relojCanarias = new JLabel((hora +23 )%24+ ":" + minutos + ":" + segundos);

        reloj.setBounds(10, 50, 500, 100);
        add(reloj);

        relojCanarias.setBounds(10, 100, 100, 100);
        add(relojCanarias);
    }

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                Thread.sleep(1000);
                calendario = new GregorianCalendar();
                hora = calendario.get(Calendar.HOUR_OF_DAY);
                minutos = calendario.get(Calendar.MINUTE);
                segundos = calendario.get(Calendar.SECOND);

                reloj.setText(hora + ":" + minutos + ":" + segundos);
                relojCanarias.setText((hora +23 )%24 + ":" + minutos + ":" + segundos);
                
                System.out.println(hora + ":" + minutos + ":" + segundos);
                System.out.println((hora +23 )%24+ ":" + minutos + ":" + segundos);
                
                this.repaint();
            } catch (InterruptedException ex)
            {
                Logger.getLogger(FrameEjer09.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
