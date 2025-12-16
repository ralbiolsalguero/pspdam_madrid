package filosofos;

public class Mesa {

    private boolean[] tenedores; //true = ocupado, false = disponibles

    public Mesa(int numTenedores) {
        this.tenedores = new boolean[numTenedores];
    }

    public int tenedorIzquierda(int i){
        return i;
    }

    public int tenedorDerecha(int i){
        int resultado;
        if(i == 0){
            resultado = tenedores.length - 1;
        }else{
            resultado = i - 1;
        }
        return resultado;
    }

    public synchronized void cogerTenedores(int comensal){
        while(tenedores[tenedorIzquierda(comensal)] || tenedores[tenedorDerecha(comensal)]){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        tenedores[tenedorIzquierda(comensal)] = true;
        tenedores[tenedorDerecha(comensal)] = true;
    }

    public synchronized void dejarTenedores(int comensal){
        tenedores[tenedorIzquierda(comensal)] = false;
        tenedores[tenedorDerecha(comensal)] = false;

        notifyAll();
    }

}
