package ej2;

public class MiHilo extends Thread{
    @Override
    public void run(){
        for(int i = 1;i<=10;i++){
            System.out.println("Hola, soy un hilo ("+i+")");
        }
    }
}
