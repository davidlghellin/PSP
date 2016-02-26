package unidad05.internet;

import java.io.FileInputStream;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 *@author http://www.planetacodigo.com/recortex/recorte/119
 * @author David López González
 */
public class EjemploDES
{

    /*  Ejemplo de uso de funciones de resumen Hash
    *    carga el fichero que recibe como parametro y genera el resumen
     */
    public static void main(String[] args) throws Exception
    {
        // Comprobar argumentos
        if (args.length != 1)
        {
            mensajeAyuda();
            System.exit(1);
        }

        /* Cargar "provider" (sólo si no se usa el que viene por defecto) */
        // Security.addProvider(new BouncyCastleProvider());  // Usa provider BC
        // 
        /* PASO 1: Crear e inicializar clave */
        KeyGenerator keyGen = KeyGenerator.getInstance("DES");
        keyGen.init(56);
        SecretKey clave = keyGen.generateKey();

        System.out.println("CLAVE:" + new String(clave.getEncoded()) + "\n");

        /* PASO 2: Crear cifrador */
        Cipher cifrador = Cipher.getInstance("DES/ECB/PKCS5Padding");
        // Algoritmo DES
        // Modo : ECB (Electronic Code Book)
        // Relleno : PKCS5Padding
        // 
        /* PASO 3a: Inicializar cifrador en modo CIFRADO */
        cifrador.init(Cipher.ENCRYPT_MODE, clave);

        /* Leer fichero de 1k en 1k y pasar fragmentos leidos al cifrador */
        byte[] bufferPlano = new byte[1000];
        byte[] bufferCifrado;

        String textoCifradoTotal = new String();
        FileInputStream in = new FileInputStream(args[0]);
        int bytesLeidos = in.read(bufferPlano, 0, 1000);
        while (bytesLeidos != -1)
        {  // Mientras no se llegue al final del fichero
            bufferCifrado = cifrador.update(bufferPlano, 0, bytesLeidos);  // Pasa texto claro leido al cifrador
            textoCifradoTotal = textoCifradoTotal + new String(bufferCifrado); // Acumular texto cifrado
            bytesLeidos = in.read(bufferPlano, 0, 1000);
        }
        in.close();

        bufferCifrado = cifrador.doFinal(); // Completar cifrado (puede devolver texto)
        textoCifradoTotal = textoCifradoTotal + new String(bufferCifrado);

        System.out.println("--------------- TEXTO CIFRADO ---------------");
        System.out.println(textoCifradoTotal);   // Mostrar texto cifrado
        System.out.println("---------------------------------------------");

        System.out.println("--------------- TEXTO DESCIFRADO -------------");
        /* PASO 3b: Poner cifrador en modo DESCIFRADO */
        cifrador.init(Cipher.DECRYPT_MODE, clave);
        byte[] textoDescifrado = cifrador.update(textoCifradoTotal.getBytes()); // Pasar texto al descifrador
        System.out.print(new String(textoDescifrado));
        textoDescifrado = cifrador.doFinal(); // Completar descifrado (puede devolver texto)
        System.out.print(new String(textoDescifrado));
        System.out.println("----------------------------------------------");
    }

    public static void mensajeAyuda()
    {
        System.out.println("Ejemplo cifrado DES");
        System.out.println("\tSintaxis:   java EjemploDES fichero");
        System.out.println();
    }

}
