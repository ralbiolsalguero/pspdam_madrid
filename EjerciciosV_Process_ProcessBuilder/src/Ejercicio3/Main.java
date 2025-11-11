package Ejercicio3;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            String so = System.getProperty("os.name").toLowerCase();
            String comando = "comandoInexistente"; // Error a propósito

            ProcessBuilder pb = so.contains("win")
                    ? new ProcessBuilder("cmd", "/c", comando)
                    : new ProcessBuilder("bash", "-c", comando);

            // Redirigimos la salida de error al archivo errores.txt
            pb.redirectError(new File("errores.txt"));

            Process p = pb.start();
            p.waitFor();

            System.out.println("Error redirigido al archivo errores.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
