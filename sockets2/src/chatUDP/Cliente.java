package chatUDP;

import java.net.*;      // DatagramSocket, DatagramPacket, InetAddress
import java.io.*;       // IOException
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {

        // IP del servidor (localhost si es en el mismo PC)
        final String HOST_SERVIDOR = "127.0.0.1";

        // Puerto del servidor al que vamos a enviar datagramas
        final int PUERTO_SERVIDOR = 5000;

        // Buffer para recibir
        byte[] buffer = new byte[1024];

        // Control del bucle (sin break)
        boolean activo = true;

        System.out.println("=== CLIENTE CHAT UDP ===");

        Scanner scn = new Scanner(System.in);

        try {
            // Resolvemos el nombre/IP del servidor a InetAddress
            InetAddress ipServidor = InetAddress.getByName(HOST_SERVIDOR);

            // Creamos socket UDP del cliente
            // Sin puerto => el SO le asigna uno libre automáticamente
            DatagramSocket socketUDP = new DatagramSocket();

            System.out.println("Cliente UDP listo. Escribe mensajes. /exit para salir.");

            // =============================
            // HILO RECEPTOR (recibe mensajes del servidor)
            // =============================
            Thread receptor = new Thread(() -> {

                boolean escuchando = true;
                byte[] bufferRecepcion = new byte[1024];

                while (escuchando) {
                    try {
                        DatagramPacket paqueteRecibido =
                                new DatagramPacket(bufferRecepcion, bufferRecepcion.length);

                        // Espera a recibir un datagrama
                        socketUDP.receive(paqueteRecibido);

                        String msg = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());

                        System.out.println("\nServidor: " + msg);
                        System.out.print("Tú: ");

                        if (msg.equalsIgnoreCase("/exit")) {
                            escuchando = false;
                        }

                    } catch (IOException e) {
                        escuchando = false;
                        System.out.println("\n[Cliente UDP] Error recibiendo.");
                    }
                }
            });

            receptor.start();

            // =============================
            // BUCLE PRINCIPAL (envía mensajes al servidor)
            // =============================
            while (activo) {

                System.out.print("Tú: ");
                String msg = scn.nextLine();

                byte[] datos = msg.getBytes();

                // Creamos datagrama con destino IP:PUERTO del servidor
                DatagramPacket paqueteEnvio =
                        new DatagramPacket(datos, datos.length, ipServidor, PUERTO_SERVIDOR);

                // Enviamos
                socketUDP.send(paqueteEnvio);

                // Si escribimos /exit, terminamos el bucle
                if (msg.equalsIgnoreCase("/exit")) {
                    activo = false;
                }
            }

            // Cerramos socket
            socketUDP.close();

            System.out.println("[Cliente UDP] Chat finalizado.");

        } catch (IOException e) {
            System.out.println("[Cliente UDP] Error: " + e.getMessage());
        } finally {
            scn.close();
        }
    }
}
