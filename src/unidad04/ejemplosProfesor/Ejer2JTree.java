package unidad04.ejemplosProfesor;

import java.io.File;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

class RecusivoFichero
{	// RECORDAR recursivoFicheros

    public static void mostrarRecursivo(File f)
    {
        if (f == null)
        {
            return;
        }
        else if (f.isDirectory())
        {
            // system.out.println("D " + f.getName());
            new DefaultMutableTreeNode(f.getName());
            File[] list = f.listFiles();
            for (File fi : list)
            {
                mostrarRecursivo(fi);
            }
        }
        else
        {
            System.out.println(" A " + f.getName());
        }
    }
}

public class Ejer2JTree extends JFrame
{

    JPanel panel;
    DefaultMutableTreeNode abuelo;
    DefaultTreeModel modelo;

    Ejer2JTree(String dir)
    {
        panel = new JPanel();
        add(panel);
        String dirArbol = dir;
        abuelo = new DefaultMutableTreeNode(dirArbol);
        modelo = new DefaultTreeModel(abuelo);
        JTree arbol = new JTree(modelo);
        JScrollPane scroll = new JScrollPane(arbol);
        add(scroll);

        CargaEstructuraDirectorios(modelo, abuelo, dirArbol);
    }

    Ejer2JTree()
    {
        panel = new JPanel();
        add(panel);
        String dirArbol = "/mnt/Multimedia";
        abuelo = new DefaultMutableTreeNode(dirArbol);
        modelo = new DefaultTreeModel(abuelo);
        JTree arbol = new JTree(modelo);
        JScrollPane scroll = new JScrollPane(arbol);
        add(scroll);

        CargaEstructuraDirectorios(modelo, abuelo, dirArbol);
    }

    public static void CargaEstructuraDirectorios(DefaultTreeModel modelo,
                                                  DefaultMutableTreeNode hijo, String ruta)
    {
        DefaultMutableTreeNode aux = null;

        File archivo = new File(ruta);
        File[] archivos = archivo.listFiles();

        if (archivos != null)
        {
            for (int i = 0; i < archivos.length; i++)
            {

                aux = new DefaultMutableTreeNode(archivos[i].getName());
                modelo.insertNodeInto(aux, hijo, i);

                if (archivos[i].isDirectory())
                {
                    CargaEstructuraDirectorios(modelo, aux, archivos[i].getAbsolutePath());
                }
            }
        }
    }

    public static void main(String[] args)
    {
        try
        {

            // nimbus
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            // gtk
            // UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            // motif
            // UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        JFileChooser jf = new JFileChooser();
        jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jf.showOpenDialog(null);
        String ruta = jf.getSelectedFile().toString();

        Ejer2JTree mi = new Ejer2JTree(ruta);
        SwingConsole.run(mi);
    }
}
