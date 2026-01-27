package ejercicio1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {

        final String HOST = "127.0.0.1";
        final int PUERTO = 5002;

        boolean activo = true;

        try (Scanner scn = new Scanner(System.in);
             Socket sc = new Socket(HOST, PUERTO);
             DataInputStream in = new DataInputStream(sc.getInputStream());
             DataOutputStream out = new DataOutputStream(sc.getOutputStream());) {

            System.out.println(in.readUTF());

            while (activo) {
                System.out.println("> ");
                String mensaje = scn.nextLine();

                out.writeUTF(mensaje);

                String respusta = in.readUTF();
                System.out.println(respusta);

                if (mensaje.equalsIgnoreCase("salir")) {
                    activo = false;
                }


            }


        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
