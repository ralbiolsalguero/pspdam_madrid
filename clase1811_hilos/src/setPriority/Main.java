package setPriority;

public class Main {
    public static void main(String[] args) {

        //Creamos la misma tarea
        MiHilo mh = new MiHilo();

        //Creamos los hilos
        Thread hiloBajo = new Thread(mh, "HiloBajo");
        Thread hiloAlto = new Thread(mh, "HiloAlto");

        //Establecer prioridades
        hiloBajo.setPriority(Thread.MIN_PRIORITY);
        hiloAlto.setPriority(Thread.MAX_PRIORITY);

        //Arrancar los hilos

        hiloBajo.start();
        hiloAlto.start();


    }
}
