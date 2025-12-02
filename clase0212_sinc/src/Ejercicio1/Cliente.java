package Ejercicio1;

public class Cliente implements Runnable{

    private TicketSystem sistema;
    private String nombre;

    public Cliente(TicketSystem sistema, String nombre){
        this.sistema = sistema;
        this.nombre = nombre;
    }

    @Override
    public void run() {

        boolean comprado = true;

        while(comprado){
            comprado = sistema.comprarTicket(nombre);

            if(!comprado){
                System.out.println(nombre+" -> No quedan tickets. Se retira");
            }

            try {
                Thread.sleep((int) (Math.random() * 200) +100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }



    }
}
