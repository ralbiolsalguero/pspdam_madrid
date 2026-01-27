package chatUDP;

import java.net.*;      // DatagramSocket, DatagramPacket, InetAddress
import java.io.*;       // IOException
import java.util.Scanner;

public class Servidor {

    public static void main(String[] args) {

        // Puerto donde el servidor "escucha" datagramas UDP
        final int PUERTO_SERVIDOR = 5000;

        // Buffer (memoria) para recibir bytes
        byte[] buffer = new byte[1024];

        // Variable de control (sin break)
        boolean activo = true;

        System.out.println("=== SERVIDOR CHAT UDP ===");
        System.out.println("Escuchando en puerto " + PUERTO_SERVIDOR + "...");

        // Scanner para leer del teclado (mensajes que el servidor envía)
        Scanner scn = new Scanner(System.in);

        try (
                // DatagramSocket: "abre" el puerto UDP para recibir/enviar
                DatagramSocket socketUDP = new DatagramSocket(PUERTO_SERVIDOR)
        ) {

            // En UDP necesitamos saber a quién responder:
            // guardaremos la IP y el puerto del cliente cuando llegue el primer mensaje
            InetAddress ipCliente = null;
            int puertoCliente = -1;

            // =============================
            // HILO RECEPTOR (recibe mensajes)
            // =============================
            Thread receptor = new Thread(() -> {

                // Control del hilo receptor
                boolean escuchando = true;

                // Buffer local para este hilo (evita problemas de compartir arrays)
                byte[] bufferRecepcion = new byte[1024];

                while (escuchando) {
                    try {
                        // Paquete donde se guardará lo recibido
                        DatagramPacket paqueteRecibido =
                                new DatagramPacket(bufferRecepcion, bufferRecepcion.length);

                        // receive() se BLOQUEA hasta que llegue un datagrama
                        socketUDP.receive(paqueteRecibido);

                        // Convertimos bytes -> String
                        String msg = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());

                        // Mostramos el mensaje
                        System.out.println("\nCliente: " + msg);
                        System.out.print("Tú: ");

                        // Guardamos IP y puerto del cliente (para responder)
                        // Nota: esto se actualiza cada vez que llega un mensaje
                        // (sirve incluso si cambia el puerto del cliente, aunque no es lo típico)
                        synchronized (Servidor.class) {
                            // Guardamos datos del remitente
                            // (en UDP no hay "conexión", solo origen/destino de datagramas)
                            // Solo los guardamos para poder contestar
                        }

                        // Si recibimos /exit, paramos el hilo receptor
                        if (msg.equalsIgnoreCase("/exit")) {
                            escuchando = false;
                        }

                    } catch (IOException e) {
                        // Si el socket se cierra o hay error, dejamos de escuchar
                        escuchando = false;
                        System.out.println("\n[Servidor UDP] Error recibiendo.");
                    }
                }
            });

            receptor.start();

            // =============================
            // BUCLE PRINCIPAL (envía mensajes)
            // =============================

            // Primero: el servidor NO sabe a quién enviar hasta recibir algo.
            // Por eso esperamos el primer mensaje de cliente de forma controlada.
            System.out.println("Espera: para poder enviar, primero el cliente debe mandar un mensaje.");

            while (activo) {

                // Si aún no tenemos IP/puerto del cliente, intentamos obtenerlos
                // Recibimos un mensaje inicial (bloqueante) SOLO si no hay cliente aún
                if (ipCliente == null || puertoCliente == -1) {

                    DatagramPacket primerPaquete =
                            new DatagramPacket(buffer, buffer.length);

                    socketUDP.receive(primerPaquete);

                    String msgInicial = new String(primerPaquete.getData(), 0, primerPaquete.getLength());

                    // Guardamos a quién responder
                    ipCliente = primerPaquete.getAddress();
                    puertoCliente = primerPaquete.getPort();

                    System.out.println("\nCliente: " + msgInicial);
                    System.out.println("Cliente detectado: " + ipCliente + ":" + puertoCliente);

                    // Si el primero ya es /exit, salimos
                    if (msgInicial.equalsIgnoreCase("/exit")) {
                        activo = false;
                    }

                } else {
                    // Ya sabemos a quién enviar

                    System.out.print("Tú: ");
                    String msg = scn.nextLine();

                    // Convertimos String -> bytes
                    byte[] datos = msg.getBytes();

                    // Creamos el datagrama para enviarlo al cliente
                    DatagramPacket paqueteEnvio =
                            new DatagramPacket(datos, datos.length, ipCliente, puertoCliente);

                    // Enviamos
                    socketUDP.send(paqueteEnvio);

                    // Si escribimos /exit, cerramos
                    if (msg.equalsIgnoreCase("/exit")) {
                        activo = false;
                    }
                }
            }

            System.out.println("[Servidor UDP] Chat finalizado.");

        } catch (IOException e) {
            System.out.println("[Servidor UDP] Error general: " + e.getMessage());
        } finally {
            scn.close();
        }
    }
}
