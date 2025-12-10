package ejercicio1;

public class Buffer {
    private Integer dato = null;

    public synchronized void producir(int valor){
        while (dato != null) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        dato = valor;
        System.out.println("Productor produce -> "+valor);

        notifyAll();

    }

    public synchronized int consumir(){
        while (dato == null){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        int valor = dato;
        dato = null;

        System.out.println("Consumidor consume -> "+valor);

        notifyAll();
        return valor;
    }

}
