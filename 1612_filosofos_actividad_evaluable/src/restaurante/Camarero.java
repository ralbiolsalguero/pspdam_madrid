package restaurante;

public class Camarero implements Runnable {

    private Cocina cocina;
    private int id;

    public Camarero(int id, Cocina cocina) {
        this.id = id;
        this.cocina = cocina;
    }

    @Override
    public void run() {
        int contadorPedidos = 1;

        while (true) {
            String pedido = "Pedido " + contadorPedidos + " (Camarero " + id + " )";
            cocina.aniadirPedido(pedido);
            contadorPedidos++;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }


    }
}
