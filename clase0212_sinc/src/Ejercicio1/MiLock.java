package Ejercicio1;

public class MiLock {

    private boolean bloqueado = false;

    public synchronized void lock() throws InterruptedException {
        while (bloqueado){
            wait();
        }
        bloqueado = true;
    }

    public synchronized void unlock(){
        bloqueado = false;
        notifyAll();
    }

}
