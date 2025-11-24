package Ejercicio11;

public class Main {
    public static void main(String[] args) throws Exception {
        Thread timer = new Thread(() -> {
            int s = 0;
            while (true) {
                System.out.println("Segundos: " + (++s));
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                }
            }
        });

        timer.start();

        Thread.sleep(5000);
        timer.interrupt();
        System.out.println("Timer interrumpido");
    }
}
