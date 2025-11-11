package Ejercicio16;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Creamos un ProcessBuilder
            ProcessBuilder pb = new ProcessBuilder();

            // Obtenemos el mapa de variables de entorno
            Map<String, String> env = pb.environment();

            // Añadimos una nueva variable personalizada
            env.put("MI_NOMBRE", "Raul_Albiol");

            // Comando para mostrarla según el SO
            String so = System.getProperty("os.name").toLowerCase();
            String comando = so.contains("win")
                    ? "echo %MI_NOMBRE%"
                    : "echo $MI_NOMBRE";

            pb.command(so.contains("win") ? "cmd" : "bash", so.contains("win") ? "/c" : "-c", comando);

            // Ejecutamos el comando
            Process p = pb.start();

            // Leemos la salida
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }

            p.waitFor();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

