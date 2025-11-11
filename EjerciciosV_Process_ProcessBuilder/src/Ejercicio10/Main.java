package Ejercicio10;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            String so = System.getProperty("os.name").toLowerCase();
            String comando = so.contains("win") ? "dir /xyz" : "ls /xyz";

            ProcessBuilder pb = so.contains("win")
                    ? new ProcessBuilder("cmd", "/c", comando)
                    : new ProcessBuilder("bash", "-c", comando);

            // Redirigimos la salida y los errores a archivos distintos
            pb.redirectOutput(new File("salida.txt"));
            pb.redirectError(new File("errores.txt"));

            // Ejecutamos el proceso
            Process p = pb.start();
            p.waitFor();

            System.out.println("Salida guardada en salida.txt");
            System.out.println("⚠Errores guardados en errores.txt");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
