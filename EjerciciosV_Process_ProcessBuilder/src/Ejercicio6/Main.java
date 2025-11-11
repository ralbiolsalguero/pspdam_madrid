package Ejercicio6;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Dirección a comprobar
            String destino = "google.com";

            // Diferente sintaxis en Windows y Linux
            String[] comando = System.getProperty("os.name").toLowerCase().contains("win")
                    ? new String[]{"cmd", "/c", "ping -n 3 " + destino}
                    : new String[]{"bash", "-c", "ping -c 3 " + destino};

            ProcessBuilder pb = new ProcessBuilder(comando);
            pb.redirectErrorStream(true); // mezcla salida y errores
            Process p = pb.start();

            // Mostrar salida
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }

            // Esperar resultado
            int codigo = p.waitFor();

            System.out.println("\nCódigo de salida: " + codigo);
            if (codigo == 0) {
                System.out.println("Conexión exitosa a " + destino);
            } else {
                System.out.println("No se pudo hacer ping a " + destino);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
