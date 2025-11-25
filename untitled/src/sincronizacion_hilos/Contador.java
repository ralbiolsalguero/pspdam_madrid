package sincronizacion_hilos;

public class Contador implements Runnable{

    private int contador = 0;

    @Override
    public void run() {
        for(int i = 0;i<5;i++){
            incrementar();
        }
    }


    public synchronized void incrementar() {
        int v = contador;
        System.out.println(Thread.currentThread().getName() + " leyendo " + v);
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        contador = v + 1;
        System.out.println(Thread.currentThread().getName() + " escribiendo: " + contador);
    }

    public int getValor() {
        return contador;
    }


}
