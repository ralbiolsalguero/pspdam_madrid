package repasometodos;

public class HiloEsperador implements Runnable {
    private final Object monitor;

    public HiloEsperador(Object monitor) {
        this.monitor = monitor;
    }

    @Override
    public void run() {
        synchronized (monitor) {
            try {
                System.out.println(Thread.currentThread().getName() + " -> esperando...");
                monitor.wait();
                System.out.println(Thread.currentThread().getName() + " -> REANUDADO");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
