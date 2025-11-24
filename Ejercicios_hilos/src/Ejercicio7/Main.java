package Ejercicio7;

public class Main {
    public static void main(String[] args) throws Exception {
        Thread h1 = new Thread(() -> System.out.println("Hilo 1"));
        Thread h2 = new Thread(() -> System.out.println("Hilo 2"));
        Thread h3 = new Thread(() -> System.out.println("Hilo 3"));

        h1.start();
        h1.join();

        h2.start();
        h2.join();

        h3.start();
        h3.join();
    }
}
