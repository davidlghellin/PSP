package unidad05;

/**
 *
 * @author David López González
 * @author Fran
 */
public class Ejemplo01
{

    public static void main(String[] args)
    {
        String t[] =
        {
            "java.class.path", 
            "java.class.version",
            "java.home", 
            "java.vendor", 
            "java.vendor",
            "java.version",
            "os.name", 
            "os.vesion", 
            "user.dir", 
            "user.home", 
            "user.name",
            "line.separator"
        };
        System.setSecurityManager(new SecurityManager());

        for (int i = 0; i < t.length; i++)
        {
            System.out.println("Propiedad: " + t[i]);
            try
            {
                System.out.println("\t==>" + System.getProperty(t[i]));
            } catch (Exception e)
            {
                System.out.println("" + e);
            }

        }
    }

}
