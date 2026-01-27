package chatTCP;// Paquete para entrada y salida de datos (lectura y escritura)
import java.io.*;

// Paquete para trabajar con sockets y red
import java.net.*;

// Scanner para leer datos desde teclado
import java.util.Scanner;

public class Servidor {

    public static void main(String[] args) {

        // Puerto por el que el servidor va a escuchar conexiones
        final int PUERTO = 5000;

        // Variable de control del bucle principal
        // Se usa en lugar de break (PROHIBIDO en la práctica)
        boolean activo = true;

        System.out.println("=== SERVIDOR CHAT TCP ===");

        try (
                // ServerSocket: crea el servidor y se queda escuchando en el puerto indicado
                ServerSocket serverSocket = new ServerSocket(PUERTO);

                // accept(): el servidor se BLOQUEA aquí hasta que un cliente se conecta
                Socket socket = serverSocket.accept();

                // Flujo de entrada: permite LEER mensajes que envía el cliente
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream())
                );

                // Flujo de salida: permite ENVIAR mensajes al cliente
                // true => autoFlush (envía el mensaje inmediatamente)
                PrintWriter out = new PrintWriter(
                        socket.getOutputStream(), true
                );

                // Scanner para leer mensajes escritos por el servidor desde consola
                Scanner teclado = new Scanner(System.in)
        ) {

            System.out.println("Cliente conectado correctamente.");

            // =============================
            // HILO RECEPTOR (MENSAJES CLIENTE)
            // =============================

            // Creamos un hilo para escuchar los mensajes que envía el cliente
            Thread receptor = new Thread(() -> {

                // Variable de control del bucle del hilo
                boolean escuchando = true;

                try {
                    String msg;

                    // Mientras el hilo esté activo Y el cliente siga enviando datos
                    while (escuchando && (msg = in.readLine()) != null) {

                        // Mostramos el mensaje recibido
                        System.out.println("\nCliente: " + msg);
                        System.out.print("Tú: ");

                        // Si el cliente escribe /exit, se termina el hilo
                        if (msg.equalsIgnoreCase("/exit")) {
                            escuchando = false;
                        }
                    }

                } catch (IOException e) {
                    System.out.println("[Servidor] Error leyendo del cliente.");
                }
            });

            // Arrancamos el hilo receptor
            receptor.start();

            // =============================
            // BUCLE PRINCIPAL (ENVIAR MENSAJES)
            // =============================

            // Mientras el servidor esté activo
            while (activo) {

                // Pedimos mensaje al usuario del servidor
                System.out.print("Tú: ");
                String msg = teclado.nextLine();

                // Enviamos el mensaje al cliente
                out.println(msg);

                // Si el servidor escribe /exit, se cierra el chat
                if (msg.equalsIgnoreCase("/exit")) {
                    activo = false;
                }
            }

            System.out.println("[Servidor] Chat finalizado.");

        } catch (IOException e) {
            System.out.println("[Servidor] Error general: " + e.getMessage());
        }
    }
}
