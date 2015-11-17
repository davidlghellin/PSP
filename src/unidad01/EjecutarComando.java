package unidad01;

/**
 *
 * @author David López González
 */
import java.io.*;

public class EjecutarComando
{

    public static void main(String args[])
    {

        String s = null;

        try
        {

            // Determinar en qué SO estamos
            String so = System.getProperty("os.name");

            String comando;

            // Comando para Linux
            if (so.equals("Linux"))
            {
                comando = "ifconfig";
            }

            // Comando para Windows
            else
            {
                comando = "cmd /c ipconfig";
            }

            // Ejcutamos el comando
            Process p = Runtime.getRuntime().exec(comando);

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(
                    p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new InputStreamReader(
                    p.getErrorStream()));

            // Leemos la salida del comando
            System.out.println("Ésta es la salida standard del comando:\n");
            while ((s = stdInput.readLine()) != null)
            {
                System.out.println(s);
            }

            // Leemos los errores si los hubiera
            System.out
                    .println("Ésta es la salida standard de error del comando (si la hay):\n");
            while ((s = stdError.readLine()) != null)
            {
                System.out.println(s);
            }

            System.exit(0);
        } catch (IOException e)
        {
            System.out.println("Excepción: ");
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
