package ejercicio2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        final int PUERTO = 5000;
        boolean activo = true;

        System.out.println("=== SERVIDOR CHAT TCP ===");

        try (
                ServerSocket serverSocket = new ServerSocket(PUERTO);
                Socket socket = serverSocket.accept();
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(
                        socket.getOutputStream(), true);
                Scanner teclado = new Scanner(System.in)
        ) {

            System.out.println("Cliente conectado.");

            // Hilo receptor (lee mensajes del cliente)
            Thread receptor = new Thread(() -> {
                boolean escuchando = true;
                try {
                    String msg;
                    while (escuchando && (msg = in.readLine()) != null) {
                        System.out.println("\nCliente: " + msg);
                        System.out.print("Tú: ");

                        if (msg.equalsIgnoreCase("/exit")) {
                            escuchando = false;
                        }
                    }
                } catch (IOException e) {
                    System.out.println("[Servidor] Error de lectura.");
                }
            });

            receptor.start();

            // Envío de mensajes al cliente
            while (activo) {
                System.out.print("Tú: ");
                String msg = teclado.nextLine();
                out.println(msg);

                if (msg.equalsIgnoreCase("/exit")) {
                    activo = false;
                }
            }

            System.out.println("[Servidor] Chat finalizado.");

        } catch (IOException e) {
            System.out.println("[Servidor] Error: " + e.getMessage());
        }
    }
}
