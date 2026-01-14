package udp;

import java.io.IOException;
import java.net.*;

public class Cliente {
    public static void main(String[] args) {

        final int PUERTO_SERVIDOR = 5000;

        byte[] buffer = new byte[1024];

        try {
            InetAddress direccionServidor = InetAddress.getByName("localhost"); //127.0.0.1

            DatagramSocket socketUDP = new DatagramSocket();

            String mensaje = "¡Hola mundo desde el cliente!";

            buffer = mensaje.getBytes();

            /*Creamos el paquete (datagrama):
             *  - los datos (buffer)
             *  - el tamaño de los datos
             *  - la dirección del servidor
             *  - el puerto del servidor
             * */

            DatagramPacket pregunta = new DatagramPacket(
                    buffer,
                    buffer.length,
                    direccionServidor,
                    PUERTO_SERVIDOR
            );

            System.out.println("Envío el datagrama al servidor");
            socketUDP.send(pregunta);

            DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);

            socketUDP.receive(peticion);
            System.out.println("Recibo la respuesta del servidor");

            mensaje = new String(peticion.getData());

            System.out.println(mensaje);

            socketUDP.close();


        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
