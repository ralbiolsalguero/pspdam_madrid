package productorconsumidor;

public class Productor implements Runnable {

    private Buffer buffer;
    private final String letras = "abcdefghijklmnopqrstuvwxyz";

    public Productor(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true){
            char c = letras.charAt((int) (Math.random() * letras.length()));

            buffer.producir(c);
            System.out.println("Despositado el caracter "+c+" en el buffer");

            try {
                Thread.sleep((int) (Math.random() * 4000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

    }
}
