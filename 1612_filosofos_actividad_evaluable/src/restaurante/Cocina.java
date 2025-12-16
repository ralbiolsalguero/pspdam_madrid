package restaurante;

import java.util.LinkedList;
import java.util.Queue;

public class Cocina {

    //Cola de pedidos (recurso compartido)
    private Queue<String> pedidos = new LinkedList<>();

    private final int CAPACIDAD_MAX = 10;

    public synchronized void aniadirPedido(String pedido){

        while(pedidos.size() == CAPACIDAD_MAX){
            System.out.println("Cocina llena. Camarero esperando...");
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        pedidos.add(pedido);
        System.out.println("Pedido añadido: "+pedido+ "| Pedidos en cocina: "+pedidos.size());

        notifyAll();

    }

    public synchronized String retirarPedido(){

        while (pedidos.isEmpty()){
            System.out.println("No hay pedidos. Cocinero esperando...");
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        String pedido = pedidos.poll();
        System.out.println("Cocinando pedido: "+pedido+" | Pedidos restantes: "+pedidos.size());

        notifyAll();

        return pedido;

    }

}
