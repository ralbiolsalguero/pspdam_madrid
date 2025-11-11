package Ejercicio14;

import java.io.*;

public class Main {

    // Método utilitario para ejecutar un comando y devolver su salida
    public static String ejecutar(String comando) {
        StringBuilder salida = new StringBuilder();

        try {
            String so = System.getProperty("os.name").toLowerCase();
            ProcessBuilder pb = so.contains("win")
                    ? new ProcessBuilder("cmd", "/c", comando)
                    : new ProcessBuilder("bash", "-c", comando);

            Process p = pb.start();

            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String linea;
            while ((linea = br.readLine()) != null) {
                salida.append(linea).append("\n");
            }
            p.waitFor();
        } catch (Exception e) {
            salida.append("Error: ").append(e.getMessage());
        }

        return salida.toString();
    }

    // Programa de prueba
    public static void main(String[] args) {
        String resultado = ejecutar("dir"); // o "ls" en Linux
        System.out.println("Resultado del comando:\n" + resultado);
    }
}

