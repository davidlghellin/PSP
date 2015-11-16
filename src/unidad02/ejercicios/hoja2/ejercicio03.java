/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidad02.ejercicios.hoja2;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author David López González
 */
public class ejercicio03
{

    public static void main(String[] args)
    {   
        MiFrame frame = new MiFrame();
        MiLamina lamina = new MiLamina();
        Thread hiloLamina = new Thread(lamina);
        
        frame.add(lamina);
        hiloLamina.setDaemon(true);
        hiloLamina.start();      
    }
}

class MiFrame extends JFrame
{

    public MiFrame() throws HeadlessException
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setBounds(300, 300, 300, 300);
    }
}

class MiLamina extends JPanel implements Runnable
{

    JLabel letra;
    int x;
    int y;
    String signo = null;
    JButton btnPararRenaudar;
    boolean pause;    

    MiLamina()
    {
        signo = "+";
        x = 0;
        y = 22;
        letra = new JLabel("X");
        btnPararRenaudar= new JButton("Pause");
        btnPararRenaudar.setBounds(0, 50,100,40);
        btnPararRenaudar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                if(btnPararRenaudar.getText().equalsIgnoreCase("Pause"))
                    btnPararRenaudar.setText("Continue");
                else
                    btnPararRenaudar.setText("Continue");
                pause=!pause;
            }
        });
        
        add(btnPararRenaudar);
        setLayout(null);
        letra.setBounds(x, y, 30, 30);
        add(letra);
    }

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                Thread.sleep(500);
            } catch (InterruptedException ex)
            {
                Logger.getLogger(MiLamina.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(!pause)
            {
                if (0 >= x)            
                    signo = "+";            
                else if (x >= this.getWidth())            
                    signo = "-";

                if (signo.equals("+"))            
                    x += 5;           
                else
                    x -= 5;

                this.letra.setLocation(x, y);
                //System.out.println(x);
                this.repaint();
            }
        }
    }
}
