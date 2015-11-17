/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidad02.productorConsumidor;

/**
 *
 * @author David López González
 */
public class PruebaColaCircular
{

    public static void main(String[] args)
    {
        ClaseColaCircular cola = new ClaseColaCircular(5);
        cola.insertar(1);
        cola.insertar(2);
        cola.insertar(3);
        cola.insertar(4);
        cola.insertar(5);
        cola.mostrar();
        cola.eliminar();
        cola.eliminar();
        cola.eliminar();
        cola.insertar(6);
        cola.mostrar();
    }

}
