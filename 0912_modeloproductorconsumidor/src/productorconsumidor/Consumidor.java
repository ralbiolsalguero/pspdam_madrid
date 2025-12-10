package productorconsumidor;

public class Consumidor implements Runnable{

    private Buffer buffer;

    public Consumidor(Buffer buffer){
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true){
            char valor = buffer.consumir();
            System.out.println("Recogido el caracter "+valor+" del buffer");

            try {
                Thread.sleep((int) (Math.random() * 4000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
