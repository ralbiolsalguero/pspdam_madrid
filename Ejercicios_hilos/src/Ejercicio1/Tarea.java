package Ejercicio1;

class Tarea implements Runnable {
    @Override
    public void run() {
        System.out.println("RUN ejecutado por: " + Thread.currentThread().getName());
    }
}
