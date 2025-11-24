package ej1_carrerahilos;

public class Main {
    public static void main(String[] args) {
        //Distancia máxim 100 metros
        //Distancia de inicio 0 metros
        //Avanzar mínimo 1 metro y máximo 4 metros
        //3 animales: Tortuga, liebre, serpiente
        //Hacer la carrera

        CarreraHilos r1 = new CarreraHilos("Tortuga");
        CarreraHilos r2 = new CarreraHilos("Liebre");
        CarreraHilos r3 = new CarreraHilos("Serpiente");

        Thread h1 = new Thread(r1);
        Thread h2 = new Thread(r2);
        Thread h3 = new Thread(r3);

        /*h1.setPriority(Thread.MIN_PRIORITY);
        h2.setPriority(Thread.MAX_PRIORITY);
        h3.setPriority(Thread.NORM_PRIORITY);*/

        h1.start();
        h2.start();
        h3.start();

        try {
            h1.join();
            h2.join();
            h3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Carrera terminada");

    }
}
