package productorconsumidor;

import javax.swing.plaf.TableHeaderUI;

public class Main {
    public static void main(String[] args) {

        Buffer b = new Buffer(10);

        Productor p = new Productor(b);
        Consumidor c = new Consumidor(b);

        Thread hiloProductor = new Thread(p);
        Thread hiloProductor2 = new Thread(p);
        Thread hiloConsumidor = new Thread(c);

        hiloProductor.start();
        hiloProductor2.start();
        hiloConsumidor.start();

    }
}
