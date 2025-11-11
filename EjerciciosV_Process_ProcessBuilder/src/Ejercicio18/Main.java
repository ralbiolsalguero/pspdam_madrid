package Ejercicio18;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Primer proceso: Bloc de notas
            ProcessBuilder pb1 = new ProcessBuilder("notepad");
            Process p1 = pb1.start();
            System.out.println("Notepad abierto. Espera 3 segundos...");

            // Esperamos 3 segundos antes del siguiente proceso
            Thread.sleep(3000);

            // Segundo proceso: Calculadora
            ProcessBuilder pb2 = new ProcessBuilder("calc");
            Process p2 = pb2.start();
            System.out.println("Calculadora lanzada después de 3 segundos.");

            // Esperamos que ambos terminen
            p1.waitFor();
            p2.waitFor();

            System.out.println("Ambos procesos finalizados correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
