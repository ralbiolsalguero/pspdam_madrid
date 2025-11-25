package CuentaBancaria;

import java.util.Random;

public class Cajero implements Runnable{
    private CuentaBancaria cuenta;
    private Random random = new Random();

    public Cajero(CuentaBancaria cuenta){
        this.cuenta = cuenta;
    }

    @Override
    public void run() {
        for(int i = 0;i<5;i++){
            int cantidad = random.nextInt(401) + 100; // 100 - 500
            cuenta.retirar(cantidad);
            try {
                Thread.sleep(random.nextInt(500)+300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
