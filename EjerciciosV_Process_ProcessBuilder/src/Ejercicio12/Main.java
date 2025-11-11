package Ejercicio12;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            String so = System.getProperty("os.name").toLowerCase();
            String comando;

            if (so.contains("win")) {
                comando = "ipconfig";
                System.out.println("Sistema operativo: Windows");
            } else {
                comando = "ifconfig";
                System.out.println("Sistema operativo: Linux");
            }

            ProcessBuilder pb = so.contains("win")
                    ? new ProcessBuilder("cmd", "/c", comando)
                    : new ProcessBuilder("bash", "-c", comando);

            Process p = pb.start();

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

