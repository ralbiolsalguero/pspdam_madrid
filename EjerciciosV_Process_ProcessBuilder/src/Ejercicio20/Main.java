package Ejercicio20;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String so = System.getProperty("os.name").toLowerCase();

        while (true) {
            System.out.println("\n===== MENU DE COMANDOS =====");
            System.out.println("1. Ver IP del equipo");
            System.out.println("2. Ver variables de entorno");
            System.out.println("3. Probar conexión con Google");
            System.out.println("4. Salir");
            System.out.print("👉 Elige una opción: ");
            int opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            try {
                ProcessBuilder pb = null;

                switch (opcion) {
                    case 1 -> {
                        String comando = so.contains("win") ? "ipconfig" : "ifconfig";
                        pb = so.contains("win") ? new ProcessBuilder("cmd", "/c", comando)
                                : new ProcessBuilder("bash", "-c", comando);
                    }
                    case 2 -> {
                        pb = new ProcessBuilder();
                        Map<String, String> env = pb.environment();
                        System.out.println("\nVariables de entorno:");
                        for (String clave : env.keySet()) {
                            System.out.println(clave + " = " + env.get(clave));
                        }
                    }
                    case 3 -> {
                        String comando = so.contains("win") ? "ping -n 2 google.com" : "ping -c 2 google.com";
                        pb = so.contains("win") ? new ProcessBuilder("cmd", "/c", comando)
                                : new ProcessBuilder("bash", "-c", comando);
                    }
                    case 4 -> {
                        System.out.println("Saliendo del programa...");
                        return;
                    }
                    default -> System.out.println("Opción no válida");
                }

                if (pb != null) {
                    pb.redirectErrorStream(true);
                    Process p = pb.start();
                    BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    String linea;
                    while ((linea = br.readLine()) != null) {
                        System.out.println(linea);
                    }
                    p.waitFor();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

