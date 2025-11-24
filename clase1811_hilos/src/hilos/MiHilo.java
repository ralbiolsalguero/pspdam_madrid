package hilos;

public class MiHilo extends Thread{
    @Override
    public void run() {
        System.out.println("Estoy en el hilo "+Thread.currentThread().getName());
    }
}
