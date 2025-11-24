package ej1_carrerahilos;

import javax.swing.plaf.TableHeaderUI;

public class CarreraHilos implements Runnable{
    private int distancia = 0;
    private String nombre;

    public CarreraHilos(String nombre){
        this.nombre = nombre;
    }

    @Override
    public void run() {
        while(distancia < 100){
            distancia += (int) ((Math.random() * 5) + 1);
            System.out.println(nombre+" ha avanzado: "+distancia+" m");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e){
                System.out.println("Se ha interrumpido el hilo");
            }

        }
        System.out.println(nombre+" ha llegado");
    }
}
