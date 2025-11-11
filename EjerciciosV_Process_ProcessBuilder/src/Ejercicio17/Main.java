package Ejercicio17;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            String so = System.getProperty("os.name").toLowerCase();
            String[] comando = so.contains("win")
                    ? new String[]{"cmd", "/c", "echo Hola Mundo"}
                    : new String[]{"bash", "-c", "echo Hola Mundo"};

            for (int i = 1; i <= 3; i++) {
                System.out.println("Ejecución nº " + i);

                ProcessBuilder pb = new ProcessBuilder(comando);
                Process p = pb.start();

                BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String linea;
                while ((linea = br.readLine()) != null) {
                    System.out.println(linea);
                }

                p.waitFor();
                System.out.println("----------------------------");
            }

            System.out.println("Comando ejecutado 3 veces con éxito.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
