package ej3;

public class MiHilo implements Runnable{

    @Override
    public void run() {
        for(int i = 1;i<=5;i++){
            System.out.println("Número: "+i);
        }
    }
}
