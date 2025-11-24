package ej2_semaforo;

public class Main {
    public static void main(String[] args) {

        //Crear un hilo que ejecute tres veces verde (2000ms), amarillo (1000ms), rojo (3000ms),
        Semaforo sm1 = new Semaforo();
        Thread h1 = new Thread(sm1);

        h1.start();

        for(int i = 0;i<=8;i++){
            System.out.println("Semáforo funcionando");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Se ha interrumpido el muestreo del mensaje semaforo funcionando");
            }
        }

    }
}
