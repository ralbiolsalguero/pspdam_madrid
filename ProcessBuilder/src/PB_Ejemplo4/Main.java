package PB_Ejemplo4;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            ProcessBuilder pb = new ProcessBuilder("cmd","/c","dir");
            pb.directory(new File("C:\\Windows"));
            pb.inheritIO();
            pb.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
