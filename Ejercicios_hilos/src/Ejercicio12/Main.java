package Ejercicio12;

public class Main {
    public static void main(String[] args) throws Exception {

        class Descarga extends Thread {
            public Descarga(String nombre) {
                super(nombre);
            }

            @Override
            public void run() {
                int t = (int) (Math.random() * 4000 + 1000);
                try {
                    Thread.sleep(t);
                } catch (Exception e) {
                }
                System.out.println(getName() + " completada en " + t + "ms");
            }
        }

        Descarga d1 = new Descarga("Archivo 1");
        Descarga d2 = new Descarga("Archivo 2");
        Descarga d3 = new Descarga("Archivo 3");

        d1.start();
        d2.start();
        d3.start();

        d1.join();
        d2.join();
        d3.join();

        System.out.println("Todas las descargas terminadas");
    }
}
