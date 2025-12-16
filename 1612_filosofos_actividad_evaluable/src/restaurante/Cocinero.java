package restaurante;

public class Cocinero implements Runnable{

    private Cocina cocina;
    private int id;

    public Cocinero(int id, Cocina cocina){
        this.id = id;
        this.cocina = cocina;
    }

    @Override
    public void run() {

        while (true){
            String pedido = cocina.retirarPedido();
            try {
                System.out.println("Cocinero "+id+" preparando "+pedido);
                Thread.sleep(1000);

                System.out.println("Cocinero: "+id+" ha terminado "+pedido);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

    }
}
