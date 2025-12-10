package ejercicio1;

public class Consumidor implements Runnable {

    private Buffer buffer;

    public Consumidor(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        int total = 0;
        while (total < 20) {
            buffer.consumir();
            total++;
        }
    }
}
