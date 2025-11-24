package setPriority;

public class MiHilo implements Runnable{
    @Override
    public void run() {
        for (int i = 0;i<5;i++){
            System.out.println("Hilo: "+Thread.currentThread().getName()+
                    " Prioridad = "+Thread.currentThread().getPriority()+
                    " i = "+i);
        }
    }
}
