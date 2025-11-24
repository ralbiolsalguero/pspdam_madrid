package Ejercicio9;

public class Main {
    public static void main(String[] args) {

        Thread semaforo = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                try {
                    System.out.println("VERDE");
                    Thread.sleep(2000);
                    System.out.println("AMARILLO");
                    Thread.sleep(1000);
                    System.out.println("ROJO");
                    Thread.sleep(3000);
                } catch (Exception e) {
                }
            }
        });

        semaforo.start();

        for (int i = 0; i < 8; i++) {
            System.out.println("Semáforo funcionando...");
            try {
                Thread.sleep(500);
            } catch (Exception e) {
            }
        }
    }
}

