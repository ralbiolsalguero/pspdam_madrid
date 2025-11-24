package Ejercicio16;

public class Main {
    public static void main(String[] args) throws Exception {

        class HiloCocinero extends Thread {
            @Override
            public void run() {
                int t = (int) (Math.random() * 2000 + 1000);
                try {
                    Thread.sleep(t);
                } catch (Exception e) {
                }
                System.out.println("Pedido preparado (" + t + " ms)");
            }
        }

        class HiloRepartidor extends Thread {
            @Override
            public void run() {
                int t = (int) (Math.random() * 3000 + 2000);
                try {
                    Thread.sleep(t);
                } catch (Exception e) {
                }
                System.out.println("Pedido entregado (" + t + " ms)");
            }
        }

        for (int i = 1; i <= 5; i++) {
            System.out.println("=== Pedido " + i + " ===");

            HiloCocinero c = new HiloCocinero();
            HiloRepartidor r = new HiloRepartidor();

            c.start();
            c.join(); // hasta que termine de cocinar

            r.start();
            r.join(); // hasta que entregue
        }
    }
}

