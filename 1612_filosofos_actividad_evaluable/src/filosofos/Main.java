package filosofos;

public class Main {
    public static void main(String[] args) {
        Mesa m = new Mesa(5);

        for(int i = 1; i <=5; i++){
            Filosofo filosofo = new Filosofo(m, i);
            Thread hilo = new Thread(filosofo);

            hilo.start();

        }
    }
}
