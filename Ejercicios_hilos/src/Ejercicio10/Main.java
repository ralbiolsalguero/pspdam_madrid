package Ejercicio10;

public class Main {
    static int contador = 0;

    public static void main(String[] args) throws Exception {

        Runnable tarea = () -> {
            for (int i = 0; i < 1000; i++) {
                contador++;
                try {
                    Thread.sleep(1);
                } catch (Exception e) {
                }
            }
        };

        Thread t1 = new Thread(tarea);
        Thread t2 = new Thread(tarea);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Valor final: " + contador);
        // No será 2000 → condición de carrera
    }
}
