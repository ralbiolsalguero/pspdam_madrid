package Ejercicio15;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Ejecutamos el script externo
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "backup.bat");
            pb.redirectErrorStream(true); // mezcla salida y error

            Process p = pb.start();

            // Leemos lo que muestra el script
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }

            // Esperamos a que termine
            int codigo = p.waitFor();
            System.out.println("\nCódigo de salida: " + codigo);

            // Evaluamos el resultado
            if (codigo == 0) {
                System.out.println("Copia de seguridad completada con éxito.");
            } else {
                System.out.println("Error durante la copia de seguridad.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

