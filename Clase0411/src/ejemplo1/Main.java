package ejemplo1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {


        try {
            ProcessBuilder pb = new ProcessBuilder("cmd","/c","dir");
            //En linux: new ProcessBuilder("ls,"-l");
            Process proceso = pb.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));

            String linea;
            while((linea = reader.readLine()) != null){
                System.out.println(linea);
            }

            int exitCode = proceso.waitFor();
            System.out.println("El proceso terminó con código "+exitCode);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
