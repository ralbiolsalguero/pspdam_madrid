package sincronizacion_hilos;

public class Main {
    public static void main(String[] args) {
        Contador c = new Contador();

        Thread h1 = new Thread(c, "Hilo-1");
        Thread h2 = new Thread(c, "Hilo-2");

        h1.start();
        h2.start();

        try {
            h1.join();
            h2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Valor final = "+c.getValor());

    }
}
