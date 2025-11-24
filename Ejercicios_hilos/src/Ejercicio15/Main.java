package Ejercicio15;

public class Main {
    static boolean turnoPing = true;

    public static void main(String[] args) {

        Thread ping = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                while (!turnoPing) {
                }
                System.out.println("PING");
                turnoPing = false;
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                }
            }
        });

        Thread pong = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                while (turnoPing) {
                }
                System.out.println("PONG");
                turnoPing = true;
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                }
            }
        });

        ping.start();
        pong.start();
    }
}
