package productorconsumidor;

public class Buffer {

    private char buffer[];
    private int siguiente;

    private boolean estaLlena; //estaLlena = true = no se puede producir
    private boolean estaVacia; //estaVacia = true = no se peude consumir

    public Buffer(int tamanio){
        buffer = new char[tamanio];
        siguiente = 0;
        estaLlena = false;
        estaVacia = true;
    }

    public synchronized char consumir(){
        while (estaVacia){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        siguiente--;

        if(siguiente == 0){
            estaVacia = true;
        }

        estaLlena = false;
        notifyAll();

        return buffer[siguiente];

    }

    public synchronized void producir(char c){
        while (estaLlena){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        buffer[siguiente] = c;

        siguiente++;

        estaVacia = false;

        if(siguiente == this.buffer.length){
            estaLlena = true;
        }

        notifyAll();

    }

}
