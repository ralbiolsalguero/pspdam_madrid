package sleep;

public class Main {
    public static void main(String[] args) {
        //MÉTODO SLEEP(ms)
        MiHilo mh = new MiHilo();
        Thread hilo = new Thread(mh);

        hilo.start();
        System.out.println("Hilo main sigue ejecutandose");


    }
}
