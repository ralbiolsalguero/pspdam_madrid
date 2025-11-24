package Ejercicio1;


public class Main {
    public static void main(String[] args) {
        Tarea t = new Tarea();

        System.out.println("Llamo a run() DIRECTO (SIN hilo nuevo)");
        t.run(); // se ejecuta en el hilo main

        System.out.println("Llamo a start() (CREA hilo nuevo)");
        Thread h = new Thread(t);
        h.start();

        System.out.println("Fin del main");
    }
}
