package ejemplo4;

import java.io.*;

public class Main {
    public static void main(String[] args) {


        try {
            ProcessBuilder pb = new ProcessBuilder("cmd");
            Process p = pb.start();

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));

            writer.write("echo Hola 2º de DAM\n");
            writer.flush();
            writer.write("exit\n");
            writer.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String linea;
            while((linea = reader.readLine()) != null){
                System.out.println(linea);
            }

            p.waitFor();


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
