package ejercicio1;

public class Productor implements Runnable {

    private Buffer buffer;

    public Productor(Buffer buffer) {
        this.buffer = buffer;
    }


    @Override
    public void run() {
        for (int i = 1; i <= 20; i++) {
            buffer.producir(i);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
