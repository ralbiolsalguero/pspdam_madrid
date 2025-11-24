package isAlive;

public class MiHilo implements Runnable{

    @Override
    public void run() {
        System.out.println("Hilo: empiezo");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Hilo interrumpido");
        }
        System.out.println("Hilo: termino");
    }
}
