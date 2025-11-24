package hilos;

public class EjemploRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println("Ejecutando tarea en: "+Thread.currentThread().getName());
    }
}
