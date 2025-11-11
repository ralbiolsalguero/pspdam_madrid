package Ejercicio7;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Nombre del script a ejecutar
            String script = System.getProperty("os.name").toLowerCase().contains("win")
                    ? "prueba.bat"
                    : "prueba.sh";

            // Crear el proceso
            ProcessBuilder pb = System.getProperty("os.name").toLowerCase().contains("win")
                    ? new ProcessBuilder("cmd", "/c", script)
                    : new ProcessBuilder("bash", script);

            pb.redirectErrorStream(true);
            Process p = pb.start();

            // Leer salida del script
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }

            // Esperar que finalice
            int codigo = p.waitFor();
            System.out.println("\nScript finalizado con código: " + codigo);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
