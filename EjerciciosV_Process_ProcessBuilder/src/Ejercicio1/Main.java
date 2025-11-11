package Ejercicio1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        try {
            // Detectamos el sistema operativo
            String so = System.getProperty("os.name").toLowerCase();
            String comando = so.contains("win") ? "dir" : "ls";

            // reamos el proceso
            ProcessBuilder pb = so.contains("win")
                    ? new ProcessBuilder("cmd", "/c", comando)
                    : new ProcessBuilder("bash", "-c", comando);

            // Ejecutamos el comando
            Process p = pb.start();

            // Leemos la salida del proceso (stdout)
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String linea;

            System.out.println("Contenido del directorio actual:\n");
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }

            p.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
