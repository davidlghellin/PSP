/*
 * Copyright (C) 2016 David López González
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package unidad04.ejemplosProfesor;

import java.net.URL;

/**
 *
 * @author David López González
 */
public class Ejemplo1URL
{

    public static void main(String[] args)
    {
        URL url;
        try
        {
            System.out.println("Constructor simple:");
            url = new URL("http://docs.oracle.com/");
            visualizar(url);

            System.out.println("Constructor con protocolo + URL+directorio");
            url = new URL("http", "docs.oracle.com", "/javase/7");
            visualizar(url);

            System.out.println("Constructor con protocolo + URL+ puerto +directorio");
            url = new URL("http", "docs.oracle.com", 80, "/javase/7");
            visualizar(url);

            System.out.println("Constructor con URLBase");
            URL urlbase = new URL("http://docs.oracle.com/");
            url = new URL(urlbase, "/javase/7/docs/api/java/net/URL.html");
            visualizar(url);

        } catch (Exception e){}
    }

    public static void visualizar(URL url)
    {
        System.out.println("\tURL completa: " + url.toString());
        System.out.println("\tProtocolo: " + url.getProtocol());
        System.out.println("\tHost: " + url.getHost());
        System.out.println("\tPuerto: " + url.getPort());
        System.out.println("\tPuerto por defecto: " + url.getDefaultPort());
        System.out.println("\tFichero: " + url.getFile());
        System.out.println("\tAutoridad: " + url.getAuthority());
    }

}
