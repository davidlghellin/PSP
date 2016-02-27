package unidad05;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/**
 *
 * @author David López González
 * @author Fran
 */
public class ClienteSSL
{

    public static void main(String[] args) throws IOException
    {
        //Establecemos propiedades de los certificados
        System.setProperty("javax.net.ssl.trustStore", "/home/wizord/AlmacenClaves");
        System.setProperty("javax.net.ssl.trustStorePassword", "123456");
        String host = "localhost";
        int puerto = 6000;
        System.out.println("Programa cliente");
        SSLSocketFactory sfact = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket cliente = (SSLSocket) sfact.createSocket(host, puerto);
        DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());
        //Envio mensaje
        flujoSalida.writeUTF("Saludos desde el cliente");
        DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());
        //El servidor envia mensaje
        System.out.println("Recibiendo del servidor: " + flujoEntrada.readUTF());

        //Información de las sesion
        SSLSession sesion = (SSLSession) cliente.getSession();
        System.out.println("Host: " + sesion.getPeerHost());
        System.out.println("Cifrado: " + sesion.getCipherSuite());
        System.out.println("Protocolo: " + sesion.getProtocol());
        System.out.println("Identificador: " + new BigInteger(sesion.getId()));
        System.out.println("Creación de la sesion:" + sesion.getCreationTime());

        //Información del certificado
        X509Certificate certificado = (X509Certificate) sesion.getPeerCertificates()[0];
        System.out.println("Propietario: " + certificado.getSubjectDN());
        System.out.println("Algoritmo: " + certificado.getSigAlgName());
        System.out.println("Tipo: " + certificado.getType());
        System.out.println("Emisor: " + certificado.getIssuerDN());
        System.out.println("Número de serie: " + certificado.getSerialNumber());

        //Cerrando flujos y sockets
        flujoSalida.close();
        flujoEntrada.close();
        cliente.close();
    }
}
