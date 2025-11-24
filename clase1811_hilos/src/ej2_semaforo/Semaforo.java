package ej2_semaforo;

public class Semaforo implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            try {
                System.out.println("VERDE");
                Thread.sleep(2000);
                System.out.println("AMARILLO");
                Thread.sleep(1000);
                System.out.println("ROJO");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("Hilo semáforo interrumpido");
            }
        }
    }
}
