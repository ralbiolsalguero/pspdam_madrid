package Ejercicio9;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Configuramos los procesos
            ProcessBuilder pb1 = new ProcessBuilder("notepad");
            ProcessBuilder pb2 = new ProcessBuilder("calc");

            // Los lanzamos
            Process p1 = pb1.start();
            Process p2 = pb2.start();

            System.out.println("Esperando a que se cierren los programas...");

            // Esperamos a ambos
            p1.waitFor();
            p2.waitFor();

            System.out.println("Todos los procesos han finalizado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
