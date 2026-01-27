package chatTCP;// Paquete para entrada y salida de datos

import java.io.*;

// Paquete para sockets y red
import java.net.*;

// Scanner para leer desde teclado
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {

        // Dirección IP del servidor
        // 127.0.0.1 = localhost (misma máquina)
        final String HOST = "127.0.0.1";

        // Puerto al que nos conectamos (debe coincidir con el servidor)
        final int PUERTO = 5000;

        // Variable de control del bucle principal (sin usar break)
        boolean activo = true;

        System.out.println("=== CLIENTE CHAT TCP ===");

        try (
                // Socket del cliente: se conecta al servidor
                Socket socket = new Socket(HOST, PUERTO);

                // Flujo de entrada: permite LEER mensajes del servidor
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream())
                );

                // Flujo de salida: permite ENVIAR mensajes al servidor
                PrintWriter out = new PrintWriter(
                        socket.getOutputStream(), true
                );

                // Scanner para leer mensajes del usuario
                Scanner teclado = new Scanner(System.in)
        ) {

            System.out.println("Conectado al servidor.");

            // =============================
            // HILO RECEPTOR (MENSAJES SERVIDOR)
            // =============================

            // Hilo encargado de escuchar mensajes del servidor
            Thread receptor = new Thread(() -> {

                // Control del bucle del hilo
                boolean escuchando = true;

                try {
                    String msg;

                    // Mientras el hilo esté activo y lleguen mensajes
                    while (escuchando && (msg = in.readLine()) != null) {

                        // Mostramos el mensaje recibido
                        System.out.println("\nServidor: " + msg);
                        System.out.print("Tú: ");

                        // Si el servidor escribe /exit, se cierra el hilo
                        if (msg.equalsIgnoreCase("/exit")) {
                            escuchando = false;
                        }
                    }

                } catch (IOException e) {
                    System.out.println("[Cliente] Error leyendo del servidor.");
                }
            });

            // Arrancamos el hilo receptor
            receptor.start();

            // =============================
            // BUCLE PRINCIPAL (ENVIAR MENSAJES)
            // =============================

            while (activo) {

                // Leemos mensaje del usuario
                System.out.print("Tú: ");
                String msg = teclado.nextLine();

                // Enviamos el mensaje al servidor
                out.println(msg);

                // Si el usuario escribe /exit, termina el cliente
                if (msg.equalsIgnoreCase("/exit")) {
                    activo = false;
                }
            }

            System.out.println("[Cliente] Chat finalizado.");

        } catch (IOException e) {
            System.out.println("[Cliente] Error: " + e.getMessage());
        }
    }
}
