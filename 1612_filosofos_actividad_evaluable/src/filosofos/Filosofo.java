package filosofos;

public class Filosofo implements Runnable{

    private Mesa mesa;

    private int comensal; //1 , 2 , 3 , 4

    private int indiceComensal; //0, 1, 2, 3

    public Filosofo(Mesa m, int comensal){
        this.mesa = m;
        this.comensal = comensal;
        this.indiceComensal = comensal - 1;
    }

    private void pensando(){
        System.out.println("Filosofo "+comensal+ " está pensando");
        try {
            Thread.sleep((int) (Math.random() * 4000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void comiendo(){
        System.out.println("Filosofo "+comensal+" está comiendo");
        try {
            Thread.sleep((int) (Math.random() * 4000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        while(true) {
            pensando();

            mesa.cogerTenedores(indiceComensal);

            comiendo();

            System.out.println("Filosofo "+comensal+" deja de comer, tenedores libres "+(mesa.tenedorIzquierda(indiceComensal) +1)+", "+(mesa.tenedorDerecha(indiceComensal)+1));

            mesa.dejarTenedores(indiceComensal);

        }
    }
}
