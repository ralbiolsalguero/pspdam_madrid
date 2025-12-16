package restaurante;

public class Main {
    public static void main(String[] args) {
        Cocina cocina = new Cocina();

        Camarero c1 = new Camarero(1, cocina);
        Camarero c2 = new Camarero(2, cocina);

        Cocinero k1 = new Cocinero(1, cocina);
        Cocinero k2 = new Cocinero(2, cocina);

        Thread tc1 = new Thread(c1);
        Thread tc2 = new Thread(c2);
        Thread tk1 = new Thread(k1);
        Thread tk2 = new Thread(k2);

        tc1.start();
        tc2.start();
        tk1.start();
        tk2.start();


    }
}
