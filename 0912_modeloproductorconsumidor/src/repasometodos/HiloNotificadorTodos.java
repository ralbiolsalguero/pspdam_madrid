package repasometodos;

public class HiloNotificadorTodos implements Runnable {

    private final Object monitor;

    public HiloNotificadorTodos(Object monitor) {
        this.monitor = monitor;
    }

    @Override
    public void run() {
        synchronized (monitor) {
            System.out.println("Notificador -> notifyAll()");
            monitor.notifyAll();
        }
    }
}
