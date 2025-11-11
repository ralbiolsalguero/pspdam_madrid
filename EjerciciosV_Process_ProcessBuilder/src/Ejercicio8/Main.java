package Ejercicio8;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Abrimos tres aplicaciones comunes de Windows
            ProcessBuilder pb1 = new ProcessBuilder("notepad");
            ProcessBuilder pb2 = new ProcessBuilder("calc");
            ProcessBuilder pb3 = new ProcessBuilder("mspaint");

            // Lanzamos los tres procesos
            Process p1 = pb1.start();
            Process p2 = pb2.start();
            Process p3 = pb3.start();

            System.out.println("Esperando a que terminen las aplicaciones...");

            // Esperamos a que todos terminen
            p1.waitFor();
            p2.waitFor();
            p3.waitFor();

            System.out.println("Todas las aplicaciones se han cerrado.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

