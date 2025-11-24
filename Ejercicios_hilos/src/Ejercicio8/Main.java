package Ejercicio8;

public class Main {
    public static void main(String[] args) throws Exception {

        Thread pares = new Thread(() -> {
            for (int i = 0; i <= 20; i += 2) {
                System.out.println("PAR: " + i);
                try {
                    Thread.sleep(200);
                } catch (Exception e) {
                }
            }
        });

        Thread impares = new Thread(() -> {
            for (int i = 1; i < 20; i += 2) {
                System.out.println("IMPAR: " + i);
                try {
                    Thread.sleep(200);
                } catch (Exception e) {
                }
            }
        });

        pares.start();
        impares.start();

        pares.join();
        impares.join();

        System.out.println("Fin");
    }
}
