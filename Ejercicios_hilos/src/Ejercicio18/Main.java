package Ejercicio18;

public class Main {
    public static void main(String[] args) throws Exception {

        Thread registrar = new Thread(() -> {
            System.out.println("Registrando usuario...");
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
            }
        });

        Thread validar = new Thread(() -> {
            System.out.println("Validando email...");
            try {
                Thread.sleep(1500);
            } catch (Exception e) {
            }
        });

        Thread carpeta = new Thread(() -> {
            System.out.println("Creando carpeta personal...");
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
        });

        Thread email = new Thread(() -> {
            System.out.println("Enviando email de bienvenida...");
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
        });

        registrar.start();
        registrar.join();
        validar.start();
        validar.join();
        carpeta.start();
        carpeta.join();
        email.start();
        email.join();

        System.out.println("Registro completado");
    }
}
