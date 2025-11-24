package join;

public class Main {
    public static void main(String[] args) {
        //1. Crear la tarea
        MiHilo mh = new MiHilo();

        //2. Crear el hilo que ejecutará esa tarea
        Thread th = new Thread(mh);

        //3. Arrancar el hilo
        th.start();

        System.out.println("Main: espero que al trabador termine con join");

        try {
            th.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Main: yo he terminado");
    }
}
