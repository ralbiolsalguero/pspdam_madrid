package repasometodos;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Object monitor = new Object();

        Thread t1 = new Thread(new HiloEsperador(monitor), "Hilo-1");
        Thread t2 = new Thread(new HiloEsperador(monitor), "Hilo-2");
        Thread t3 = new Thread(new HiloEsperador(monitor), "Hilo-3");

        t1.start();
        t2.start();
        t3.start();

        Thread.sleep(2000);

        //new Thread(new HiloNotificador(monitor)).start();
        new Thread(new HiloNotificadorTodos(monitor)).start();

    }
}
