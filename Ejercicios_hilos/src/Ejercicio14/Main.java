package Ejercicio14;

public class Main {
    public static void main(String[] args) throws Exception {
        Thread proceso = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                System.out.println("Paso " + i);
                try {
                    Thread.sleep(400);
                } catch (Exception e) {
                }
            }
        });

        proceso.start();

        while (proceso.isAlive()) {
            System.out.println("Proceso sigue…");
            Thread.sleep(300);
        }

        System.out.println("Proceso acabado");
    }
}
