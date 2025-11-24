package Ejercicio6;

public class Main {
    public static void main(String[] args) throws Exception {

        class Corredor extends Thread {
            int distancia = 0;

            public Corredor(String nombre, int prioridad) {
                super(nombre);
                setPriority(prioridad);
            }

            @Override
            public void run() {
                while (distancia < 100) {
                    distancia += (int) (Math.random() * 5 + 1);
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                    }
                }
                System.out.println(getName() + " ha llegado!");
            }
        }

        Corredor c1 = new Corredor("Tortuga", Thread.MIN_PRIORITY);
        Corredor c2 = new Corredor("Normal", Thread.NORM_PRIORITY);
        Corredor c3 = new Corredor("Liebre", Thread.MAX_PRIORITY);

        c1.start();
        c2.start();
        c3.start();

        c1.join();
        c2.join();
        c3.join();

        System.out.println("Carrera terminada");
    }
}
