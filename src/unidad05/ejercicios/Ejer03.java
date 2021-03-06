package unidad05.ejercicios;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author David López González
 */
public class Ejer03 extends javax.swing.JFrame
{

    private FileInputStream in;
    private byte[] buffer;

    /**
     * Crea un programa en Java que verifique si un archivo conserva la
     * integridad comparándolo con su fichero MD5. Haced la prueba con un
     * archivo descargado de Internet, por ejemplo.
     */
    public Ejer03()
    {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jPanel1 = new javax.swing.JPanel();
        btnComprobar = new javax.swing.JButton();
        lblResultado = new javax.swing.JLabel();
        jtfSum = new javax.swing.JTextField();
        btnOpen = new javax.swing.JButton();
        lblRuta = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnAbrirSumVerificacion = new javax.swing.JButton();
        labelNombre = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnComprobar.setText("Comprobar");
        btnComprobar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnComprobarActionPerformed(evt);
            }
        });

        btnOpen.setText("Abrir fichero");
        btnOpen.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnOpenActionPerformed(evt);
            }
        });

        jLabel1.setText("Valor de verificación:");

        btnAbrirSumVerificacion.setText("Abrir");
        btnAbrirSumVerificacion.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAbrirSumVerificacionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfSum)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnComprobar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnOpen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addComponent(lblRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(labelNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(btnAbrirSumVerificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOpen)
                    .addComponent(lblRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(btnAbrirSumVerificacion))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(labelNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfSum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnComprobar)
                    .addComponent(lblResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(94, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOpenActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnOpenActionPerformed
    {//GEN-HEADEREND:event_btnOpenActionPerformed
        final JFileChooser fc = new JFileChooser();

        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
        {
            try
            {
                File file = fc.getSelectedFile();
                System.out.println(file.toPath() + "  <== Ruta");
                labelNombre.setText(file.getPath());
                in = new FileInputStream(file.toPath().toString());
                buffer = new byte[in.available()];
                in.read(buffer);
                in.close();
                /// versión más corta
                buffer = Files.readAllBytes(file.toPath());
                System.out.println(buffer + "  <== Byte[]");
            } catch (FileNotFoundException ex)
            {
                Logger.getLogger(Ejer03.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex)
            {
                Logger.getLogger(Ejer03.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnOpenActionPerformed

    private void btnComprobarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnComprobarActionPerformed
    {//GEN-HEADEREND:event_btnComprobarActionPerformed
        MessageDigest md = null;
        try
        {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex)
        {
            Logger.getLogger(Ejer03.class.getName()).log(Level.SEVERE, null, ex);
        }
        md.update(buffer);
        byte[] resume = md.digest();

        //if(md.isEqual(resume, jtfSum.getText().getBytes()))
        if (jtfSum.getText().equalsIgnoreCase(Hexadecimal(resume)))
        {
            jtfSum.setBackground(Color.GREEN);
        }
        else
        {
            jtfSum.setBackground(Color.RED);
        }

        System.out.println("Número de Bytes: " + md.getDigestLength());
        System.out.println("Algoritmo: " + md.getAlgorithm());
        System.out.println("Mensaje resumen: " + new String(resume));
        System.out.println("Hexadecimal: " + Hexadecimal(resume));
    }//GEN-LAST:event_btnComprobarActionPerformed

    private void btnAbrirSumVerificacionActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnAbrirSumVerificacionActionPerformed
    {//GEN-HEADEREND:event_btnAbrirSumVerificacionActionPerformed
        final JFileChooser fc = new JFileChooser();
        String cadena = null;
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
        {

            FileReader f = null;
            try
            {
                File file = fc.getSelectedFile();
                f = new FileReader(file);

                BufferedReader b = new BufferedReader(f);
                //leemos linea
                cadena = b.readLine();
                // nos aseguramos de obtener el md5 (debe de ser el primero)
                cadena = (cadena.split(" "))[0];
                jtfSum.setText(cadena.toUpperCase());
            } catch (FileNotFoundException ex)
            {
                Logger.getLogger(Ejer03.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex)
            {
                Logger.getLogger(Ejer03.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally
            {
                try
                {
                    f.close();
                } catch (IOException ex)
                {
                    Logger.getLogger(Ejer03.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_btnAbrirSumVerificacionActionPerformed

    static String Hexadecimal(byte[] resumen)
    {
        String hex = "";
        for (int i = 0; i < resumen.length; i++)
        {
            String h = Integer.toHexString(resumen[i] & 0xFF);
            if (h.length() == 1)
            {
                hex += "0";
            }
            hex += h;
        }
        return hex.toUpperCase();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(Ejer03.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(Ejer03.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(Ejer03.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(Ejer03.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new Ejer03().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrirSumVerificacion;
    private javax.swing.JButton btnComprobar;
    private javax.swing.JButton btnOpen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jtfSum;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JLabel lblResultado;
    private javax.swing.JLabel lblRuta;
    // End of variables declaration//GEN-END:variables
}
