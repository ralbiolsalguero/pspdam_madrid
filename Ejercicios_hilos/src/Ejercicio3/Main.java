package Ejercicio3;

public class Main {
    public static void main(String[] args) {
        Thread h = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
            }
        });

        System.out.println("Antes de start(): " + h.isAlive()); // false

        h.start();
        System.out.println("Después de start(): " + h.isAlive()); // true normalmente

        try {
            h.join();
        } catch (Exception e) {
        }

        System.out.println("Después de join(): " + h.isAlive()); // false
    }
}
