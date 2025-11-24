package isAlive;

import javax.swing.plaf.TableHeaderUI;

public class Main {
    public static void main(String[] args) {

        MiHilo mh = new MiHilo();

        Thread h = new Thread(mh);

        System.out.println("Antes de start(), isAlive() = "+h.isAlive()); //false

        h.start();
        System.out.println("Después de start(), isAlive() = "+h.isAlive()); //true

        try {
            h.join();
        } catch (InterruptedException e) {
            System.out.println("Main interrumpido");
        }

        System.out.println("Después de join(), isAlive() = "+h.isAlive()); //false

    }
}
