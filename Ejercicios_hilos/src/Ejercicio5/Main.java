package Ejercicio5;

public class Main {
    public static void main(String[] args) {
        Runnable tarea = () -> {
            for (int i = 0; i < 10; i++)
                System.out.println(Thread.currentThread().getName() + " " + i);
        };

        Thread bajo = new Thread(tarea, "BAJO");
        Thread normal = new Thread(tarea, "NORMAL");
        Thread alto = new Thread(tarea, "ALTO");

        bajo.setPriority(Thread.MIN_PRIORITY);
        normal.setPriority(Thread.NORM_PRIORITY);
        alto.setPriority(Thread.MAX_PRIORITY);

        bajo.start();
        normal.start();
        alto.start();
    }
}
