package repasometodos;

public class HiloNotificador implements Runnable {

    private final Object monitor;

    public HiloNotificador(Object monitor) {
        this.monitor = monitor;
    }

    @Override
    public void run() {
        synchronized (monitor) {
            System.out.println("Notificador -> notify()");
            monitor.notify();
        }
    }
}
