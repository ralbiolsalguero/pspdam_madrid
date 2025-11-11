package Ejercicio2;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            String so = System.getProperty("os.name").toLowerCase();
            String comando = "date";

            ProcessBuilder pb = so.contains("win") ? new ProcessBuilder("cmd", "/c", comando) : new ProcessBuilder("bash", "-c", comando);

            // Redirigimos la salida al archivo fecha.txt
            pb.redirectOutput(new File("fecha.txt"));

            Process p = pb.start();
            p.waitFor();

            System.out.println("Fecha guardada correctamente en fecha.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
