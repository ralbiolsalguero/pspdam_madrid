package ejercicio2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        final String HOST = "127.0.0.1";
        final int PUERTO = 5050;

        try (Scanner scn = new Scanner(System.in);
             Socket sc = new Socket(HOST, PUERTO);
             DataInputStream in = new DataInputStream(sc.getInputStream());
             DataOutputStream out = new DataOutputStream(sc.getOutputStream())
        ) {
            System.out.println(in.readUTF());
            String user = scn.nextLine();
            out.writeUTF(user);

            System.out.println(in.readUTF());
            String pass = scn.nextLine();
            out.writeUTF(pass);

            String estado = in.readUTF(); //Ok o Denegado
            System.out.println("Servidor: " + estado);

            boolean seguir = estado.equals("OK");

            while (seguir) {

                String menu = in.readUTF();
                System.out.println(menu);

                String opcion = scn.nextLine();
                out.writeUTF(opcion);

                String respuesta = in.readUTF();
                System.out.println(respuesta);

                if (opcion.equals("3")) {
                    seguir = false;
                }

            }


        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
