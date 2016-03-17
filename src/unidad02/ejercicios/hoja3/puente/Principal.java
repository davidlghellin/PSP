/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidad02.ejercicios.hoja3.puente;

/**
 *
 * @author David López González
 */
public class Principal
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
    public static void main(String[] args) throws InterruptedException
    {
        MonitorPuente monitor = new MonitorPuente();
        int nNorte = 6;
        int nSur = 8;
        for (int i = 0; i < nNorte; i++)
        {
            Thread.sleep((long)Math.random()*5000);
            new Thread(new CochesNorte(i, monitor), "chocheNorte" + (i + 1)).start();
        }
        for (int i = 0; i < nSur; i++)
        {
            Thread.sleep((long)Math.random()*5000);
            new Thread(new CochesSur(i, monitor), "chocheSur" + (i + 1)).start();
        }
        
    }
}
