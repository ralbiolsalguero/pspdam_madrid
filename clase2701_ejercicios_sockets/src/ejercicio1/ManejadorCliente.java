package ejercicio1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ManejadorCliente implements Runnable{

    private final Socket socketCliente;

    public ManejadorCliente(Socket socketCliente){
        this.socketCliente = socketCliente;
    }


    @Override
    public void run() {
        boolean conectado = true;

        try(
                Socket sc = socketCliente;
                DataInputStream in = new DataInputStream(sc.getInputStream());
                DataOutputStream out = new DataOutputStream(sc.getOutputStream());
                ){

            out.writeUTF("Servidor: Bienvenido. Escribe 'salir' para cerrar");

            while(conectado){
                //Espera mensaje del cliente
                String mensaje = in.readUTF();
                System.out.println("["+sc.getInetAddress()+"] "+mensaje);

                if (mensaje.equalsIgnoreCase("salir")){
                    out.writeUTF("Servidor: conexión cerrada. ¡Adiós!");
                    conectado = false;
                }else{
                    out.writeUTF("Servidor (eco): "+mensaje);
                }

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Cliente desconectado: "+socketCliente.getInetAddress()+" : "+socketCliente.getPort());


    }
}
