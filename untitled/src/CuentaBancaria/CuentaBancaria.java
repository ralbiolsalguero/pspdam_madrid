package CuentaBancaria;

public class CuentaBancaria {
    private int saldo;

    public CuentaBancaria(int saldoInicial){
        this.saldo = saldoInicial;
    }

    public void retirar(int cantidad){
        String hilo = Thread.currentThread().getName();
        if(saldo >= cantidad){
            System.out.println(hilo+" va a retirar "+cantidad+" €");
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            saldo -= cantidad;
            System.out.println(hilo+" ha retirado "+cantidad+"€. Saldo restante: "+saldo+"€");
        }else{
            System.out.println("NO PUEDE RETIRAR "+cantidad+"€. Saldo insuficiente ("+saldo+"€)");
        }
    }

}
