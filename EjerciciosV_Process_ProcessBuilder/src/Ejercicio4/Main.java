package Ejercicio4;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce un comando del sistema: ");
        String comando = sc.nextLine();

        try {
            String so = System.getProperty("os.name").toLowerCase();

            ProcessBuilder pb = so.contains("win")
                    ? new ProcessBuilder("cmd", "/c", comando)
                    : new ProcessBuilder("bash", "-c", comando);

            // Mezclamos salida normal y errores en el mismo archivo
            pb.redirectErrorStream(true);
            pb.redirectOutput(new File("resultado.txt"));

            Process p = pb.start();
            p.waitFor();

            System.out.println("Resultado guardado en resultado.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

