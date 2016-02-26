package unidad04.ejemplosProfesor;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author David López González
 */
public class SwingConsole
{
	public static void run(final JFrame f, final int width, final int height)
	{
		SwingUtilities.invokeLater(new Runnable()
		{

			@Override
			public void run()
			{
				f.setTitle(f.getClass().getSimpleName());
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.setSize(width, height);
				f.setVisible(true);
			}
		});
	}
	public static void run(final JFrame f)
	{
		SwingUtilities.invokeLater(new Runnable()
		{

			@Override
			public void run()
			{
				f.setTitle(f.getClass().getSimpleName());
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				//f.setSize(300, 300);
				f.setBounds(600, 300, 500, 300);
				f.setVisible(true);
			}
		});
	}
}