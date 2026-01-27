package ejercicio1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {

        final int PUERTO = 5002;

        try(ServerSocket servidor = new ServerSocket(PUERTO)){
            System.out.println("Servidor MULTICLIENTE iniciado en puerto "+PUERTO);

            while(true){
                //Esperando a que llegue el cliente
                Socket sc = servidor.accept();
                System.out.println("Cliente conectado: "+sc.getInetAddress()+":"+sc.getPort());

                //Creamos un hilo por cliente
                Thread hilo = new Thread();
                hilo.start();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
