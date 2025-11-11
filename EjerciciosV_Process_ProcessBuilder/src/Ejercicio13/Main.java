package Ejercicio13;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            String so = System.getProperty("os.name").toLowerCase();
            String comando = so.contains("win") ? "dir" : "ls";

            ProcessBuilder pb = so.contains("win")
                    ? new ProcessBuilder("cmd", "/c", comando)
                    : new ProcessBuilder("bash", "-c", comando);

            Process p = pb.start();

            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String linea;
            int contador = 0;

            while ((linea = br.readLine()) != null) {
                contador++;
            }

            p.waitFor();
            System.out.println("Total de líneas de salida: " + contador);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

