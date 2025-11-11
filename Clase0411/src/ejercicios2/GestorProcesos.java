package ejercicios2;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class GestorProcesos {

    public void ejecutarComando(String comando) {
        try {
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", comando);
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

    public void redirigirSalida(String comando, String archivo) {

        try {
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", comando);
            pb.redirectOutput(new File(archivo));
            Process p = pb.start();
            p.waitFor();
            System.out.println("Salida guardada en " + archivo);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void mostrarErrores(String comando) {
        try {
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", comando);
            Process p = pb.start();

            BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String linea;
            while ((linea = br.readLine()) != null) {
                System.err.println(linea);
            }
            p.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
