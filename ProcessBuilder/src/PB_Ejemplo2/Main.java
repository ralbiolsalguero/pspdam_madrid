package PB_Ejemplo2;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        /*Redirigir la salida a un archivo*/
        try {
            ProcessBuilder pb = new ProcessBuilder("cmd","/c","dir");
            pb.redirectOutput(new File("resultado.txt"));
            pb.redirectError(new File("errores.txt"));
            pb.start();
            System.out.println("Comando ejecutado. Revisa resultados en el .txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
