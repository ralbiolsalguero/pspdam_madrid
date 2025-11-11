package ejercicios1;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        /*
        *
        * Ejecutar ipconfig y guardar su salida, redirigir la salida a un archivo
        * llamado red.txt
        * */
        try {
            String comando = System.getProperty("os.name").toLowerCase().contains("win") ? "ipconfig" : "ifconfig";

            ProcessBuilder pb = new ProcessBuilder(comando);

            pb.redirectOutput(new File("red.txt"));

            Process p = pb.start();

            p.waitFor();

            System.out.println("Salida guardada en red.txt");

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
