package Ejercicio19;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            String so = System.getProperty("os.name").toLowerCase();
            String comando = so.contains("win") ? "tasklist" : "ps -e";

            ProcessBuilder pb = so.contains("win")
                    ? new ProcessBuilder("cmd", "/c", comando)
                    : new ProcessBuilder("bash", "-c", comando);

            // Redirigimos la salida al archivo procesos.txt
            pb.redirectOutput(new File("procesos.txt"));

            Process p = pb.start();
            p.waitFor();

            System.out.println("Lista de procesos guardada en procesos.txt");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

