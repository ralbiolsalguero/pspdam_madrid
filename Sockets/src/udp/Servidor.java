package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Servidor {
    public static void main(String[] args) {

        final int PUERTO = 5000;
        byte [] buffer = new byte[1024];

        try {
            System.out.println("Servidor UDP iniciado");
            DatagramSocket socketUDP = new DatagramSocket(PUERTO);

            while(true){
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);

                socketUDP.receive(peticion);
                System.out.println("Recibo información del cliente");

                String mensaje = new String(peticion.getData());
                System.out.println(mensaje);

                int puertoCliente = peticion.getPort();
                InetAddress direccion = peticion.getAddress();

                mensaje = "¡Hola mundo desde el servidor!";

                buffer = mensaje.getBytes();

                DatagramPacket respuesta = new DatagramPacket(
                        buffer,
                        buffer.length,
                        direccion,
                        puertoCliente
                );

                System.out.println("Envío la respuesta al cliente");
                socketUDP.send(respuesta);



            }

        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
