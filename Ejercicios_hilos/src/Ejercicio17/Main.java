package Ejercicio17;

public class Main {
    public static void main(String[] args) throws Exception {

        Thread A = new Thread(() -> {
            System.out.println("Fabricando base...");
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
            }
        });

        Thread B = new Thread(() -> {
            System.out.println("Puliendo...");
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
        });

        Thread C = new Thread(() -> {
            System.out.println("Pintando...");
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
            }
        });

        A.start();
        A.join();

        B.start();
        B.join();

        C.start();
        C.join();

        System.out.println("Pieza final lista");
    }
}
