package ej3;

import ej2.MiHilo;

public class Main {
    public static void main(String[] args) {
        //1º Creamos un runnable
        MiHilo miHilo = new MiHilo();
        //2º Asociamos el runnable a un hilo
        Thread hilo = new Thread(miHilo);
        //3º Ejecutamos al hilo
        hilo.start();


    }
}
