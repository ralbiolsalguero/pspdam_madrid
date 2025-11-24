package Ejercicio13;

public class Main {
    public static void main(String[] args) throws Exception {

        Thread h1 = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                System.out.println("H1");
                try {
                    Thread.sleep(200);
                } catch (Exception e) {
                }
            }
        });

        Thread h2 = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                System.out.println("H2");
                try {
                    Thread.sleep(200);
                } catch (Exception e) {
                }
            }
        });

        h1.start();
        h2.start();

        for (int i = 0; i < 5; i++) {
            h1.setPriority((i % 2 == 0) ? Thread.MAX_PRIORITY : Thread.MIN_PRIORITY);
            h2.setPriority((i % 2 == 0) ? Thread.MIN_PRIORITY : Thread.MAX_PRIORITY);
            Thread.sleep(1000);
        }
    }
}
