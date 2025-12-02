package Ejercicio1;

import javax.swing.plaf.TableHeaderUI;

public class Main {
    public static void main(String[] args) {
        /* Venta de 20 tickets con 5 clientes (hilos)
        *
        * Hay 20 entradas para un concierto.
        * Cada cliente intentará comprar 1 entrada por turno, hasta que ya no queden.
        * El recurso compartido es la cantidad de tickets.
        * Se debe usar un lock personalizado para controlar el acceso.
        *
        * */

        TicketSystem sistema = new TicketSystem();

        Cliente cli1 = new Cliente(sistema, "Cliente-1");
        Cliente cli2 = new Cliente(sistema, "Cliente-2");
        Cliente cli3 = new Cliente(sistema, "Cliente-3");
        Cliente cli4 = new Cliente(sistema, "Cliente-4");
        Cliente cli5 = new Cliente(sistema, "Cliente-5");

        Thread t1 = new Thread(cli1);
        Thread t2 = new Thread(cli2);
        Thread t3 = new Thread(cli3);
        Thread t4 = new Thread(cli4);
        Thread t5 = new Thread(cli5);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();



    }
}
