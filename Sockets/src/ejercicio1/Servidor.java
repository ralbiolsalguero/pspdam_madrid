package ejercicio1;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        final int PUERTO = 5001;

        try {
            ServerSocket servidor = new ServerSocket(PUERTO);
            System.out.println("Servidor CHAT iniciado en puerto "+PUERTO);

            while(true){
                Socket sc = servidor.accept();
                System.out.println("Cliente conectado: "+sc.getInetAddress());
                DataInputStream in = new DataInputStream(sc.getInputStream());
                DataOutputStream out = new DataOutputStream(sc.getOutputStream());

                String msg = in.readUTF();
                System.out.println("Cliente dice: "+msg);

                if(msg.equalsIgnoreCase("salir")){
                    out.writeUTF("Servidor: conexión cerrada. ¡Adiós!");
                }

                out.writeUTF("Servidor recibió"+msg);

            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
