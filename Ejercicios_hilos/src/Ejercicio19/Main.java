package Ejercicio19;

public class Main {
    static int numero = -1;

    public static void main(String[] args) {

        Thread productor = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                numero = i;
                System.out.println("Productor genera: " + i);
                try {
                    Thread.sleep(700);
                } catch (Exception e) {
                }
            }
        });

        Thread consumidor = new Thread(() -> {
            for (int i = 0; i < 8; i++) {
                System.out.println("Consumidor ve: " + numero);
                try {
                    Thread.sleep(300);
                } catch (Exception e) {
                }
            }
        });

        productor.start();
        consumidor.start();
    }
}
