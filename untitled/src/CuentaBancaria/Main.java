package CuentaBancaria;

public class Main {
    public static void main(String[] args) {
        /*
        * Enunciado
        *
        * Una cuenta bancaria tiene un saldo inicial de 1000€
        * 3 hilos representan cajeros automáticos donde diferentes usuarios intentan
        * sacar dinero.
        *
        * Cada hilo realizará 5 intentos de retirada, con cantidades aleatorias
        * entre 100€ y 500€
        *
        * Requisitos:
        * 1. Crea un clase CuentaBancaria con:
        * - atributo private int saldo
        * - constructos con saldo inicial
        * - método retirar
        *      - si hay saldo suficiente -> Retirar
        *      - si no -> saldo insuficente
        * 2. Clase cajero
        * - implementa Runnable
        * - REcibir uan referencia a la misma CuentaBancaria
        * - en su run() -> 5 retiradas
        *   - generar una cantidad aleatoria entre 100 y 500
        *   - llamar a retirar()
        *   - dormir el hilo 300 y 800ms
        *
        * 3. En el main:
        *   - Crear una sola cuenta (saldo 1000€)
        *   - Crear 3 hilos cajeros (Threads)
        *   - Lanzarlos simultaneamente
        *
        * */

        CuentaBancaria cuenta = new CuentaBancaria(1000);

        Thread t1 = new Thread(new Cajero(cuenta),"Cajero-1");
        Thread t2 = new Thread(new Cajero(cuenta),"Cajero-2");
        Thread t3 = new Thread(new Cajero(cuenta),"Cajero-3");

        t1.start();
        t2.start();
        t3.start();


    }
}
