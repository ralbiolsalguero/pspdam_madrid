package join;

public class MiHilo implements Runnable{
    @Override
    public void run() {
        try {
            System.out.println("Trabajador: empiezo tarea...");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
