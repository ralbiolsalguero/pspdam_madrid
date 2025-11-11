package Ejercicio11;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Determinar comando según sistema
            String so = System.getProperty("os.name").toLowerCase();
            String comando = so.contains("win") ? "ipconfig" : "ifconfig";

            ProcessBuilder pb = so.contains("win")
                    ? new ProcessBuilder("cmd", "/c", comando)
                    : new ProcessBuilder("bash", "-c", comando);

            Process p = pb.start();

            // Leer la salida del proceso
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));

            // Mostramos solo la primera línea
            String primeraLinea = br.readLine();
            if (primeraLinea != null) {
                System.out.println("Primera línea de salida:");
                System.out.println(primeraLinea);
            } else {
                System.out.println("No se ha producido ninguna salida.");
            }

            p.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

