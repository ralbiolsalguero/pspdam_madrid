package ejercicios3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        try {
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "ipconfig");
            pb.redirectErrorStream(true);
            Process p = pb.start();

            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }

            int codigo = p.waitFor();
            System.out.println("Script finalizado con código " + codigo);

            if (codigo == 0) {
                System.out.println("Copia completada correctamente");
            } else {
                System.out.println("Error en la copia");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
