package Ejercicio2;

public class Main {
    public static void main(String[] args) {
        Thread tic = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("tic");
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                }
            }
        });

        tic.start();

        // Main sigue haciendo cosas
        for (int i = 0; i < 7; i++) {
            System.out.println("MAIN sigue...");
            try {
                Thread.sleep(300);
            } catch (Exception e) {
            }
        }
    }
}

