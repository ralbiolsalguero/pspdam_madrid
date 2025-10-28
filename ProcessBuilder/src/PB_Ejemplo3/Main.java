package PB_Ejemplo3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        try {
            ProcessBuilder pb = new ProcessBuilder("cmd","/c","comando_inexistente");
            Process p = pb.start();

            BufferedReader err = new BufferedReader(
              new InputStreamReader(p.getErrorStream())
            );
            String linea;
            while((linea = err.readLine()) != null){
                System.out.println("ERROR:"+linea);
            }

            p.waitFor();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
