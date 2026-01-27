package ejercicio2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Servidor {
    public static void main(String[] args) {
        final String HOST = "127.0.0.1";
        final int PUERTO = 5000;
        boolean activo = true;

        System.out.println("=== CLIENTE CHAT TCP ===");

        try (
                Socket socket = new Socket(HOST, PUERTO);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(
                        socket.getOutputStream(), true);
                Scanner teclado = new Scanner(System.in)
        ) {

            System.out.println("Conectado al servidor.");

            // Hilo receptor (lee mensajes del servidor)
            Thread receptor = new Thread(() -> {
                boolean escuchando = true;
                try {
                    String msg;
                    while (escuchando && (msg = in.readLine()) != null) {
                        System.out.println("\nServidor: " + msg);
                        System.out.print("Tú: ");

                        if (msg.equalsIgnoreCase("/exit")) {
                            escuchando = false;
                        }
                    }
                } catch (IOException e) {
                    System.out.println("[Cliente] Error de lectura.");
                }
            });

            receptor.start();

            // Envío de mensajes al servidor
            while (activo) {
                System.out.print("Tú: ");
                String msg = teclado.nextLine();
                out.println(msg);

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
