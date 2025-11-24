package Ejercicio4;

public class Main {
    public static void main(String[] args) throws Exception {
        Thread A = new Thread(() -> {
            System.out.println("Hilo A empieza");
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
            }
            System.out.println("Hilo A termina");
        });

        Thread B = new Thread(() -> System.out.println("Hilo B se ejecuta tras A"));

        A.start();
        A.join(); // esperamos a A

        B.start();
    }
}
