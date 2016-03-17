package unidad02.ejercicios.hoja3.puente2;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David López González
 */
public class CochesSur implements Runnable
{

    /*
    El pueblo de Puente Duero debe su nombre a un viejo puente romano sobre el conocido
    río. El puente es estrecho y no permite el cruce de dos coches que circulen en sentidos
    contrarios. Por su peculiar forma, alto en el centro y bajo en los extremos, no hay
    visibilidad, y desde un lado no podemos saber si hay un coche empezando a cruzar el
    puente por el otro.
    Se han instalado cuatro sensores y dos barreras. Dos sensores indican la llegada de
    coches en cada dirección, emitiendo una señal por cada coche que pasa, y los otros dos
    indican salidas respectivas. Las barreras bloquean las entradas al puente, y cuando
    reciben una señal se levantan para permitir el paso de un coche (y uno sólo); acto
    seguido se vuelven a cerrar.
    Diseñe un programa concurrente, libre de bloqueos e inaniciones, que simule el sistema
    descrito, incluyendo un proceso que emplee las señales recibidas de los sensores para
    emitir señales apropiadas a las barreras, de manera que:
    Nunca crucen simultáneamente el puente vehículos en sentidos opuestos.
    Nadie espere eternamente para pasar
    Todos pasen lo más pronto posible salvo violación de las normas 1 o 2.
    Las señales de los sensores han de atenderse inmediatamente (o al menos lo más
    pronto posible), para no “perder” el paso de ningún coche.
     */
    int nCoches;
    MonitorPuente monitor;

    public CochesSur(int nCoches, MonitorPuente monitor)
    {
        this.nCoches = nCoches;
        this.monitor = monitor;
    }

    @Override
    public void run()
    {
        try
        {
            monitor.entrarCocheDelSur();
            Thread.sleep((long) (Math.random() * 3000));//tiempo en recorrer el puente
            monitor.salirCocheSur();
        } catch (InterruptedException ex)
        {
            Logger.getLogger(CochesSur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
