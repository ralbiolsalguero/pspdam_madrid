package Ejercicio20;

public class Main {
    public static void main(String[] args) throws Exception {

        Thread backup = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                System.out.println("Paso " + i + "/10");
                try {
                    Thread.sleep((int) (Math.random() * 400 + 300));
                } catch (Exception e) {
                }
            }
        });

        backup.start();

        long inicio = System.currentTimeMillis();

        while (backup.isAlive()) {
            long tiempo = System.currentTimeMillis() - inicio;
            if (tiempo > 3000)
                System.out.println("AVISO: Backup tardando demasiado...");

            Thread.sleep(200);
        }

        backup.join();
        System.out.println("Backup completado");
    }
}
